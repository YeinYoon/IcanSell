package com.codeyeji.IcanSell.controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.codeyeji.IcanSell.config.SecurityConfig;
import com.codeyeji.IcanSell.data.Admin;
import com.codeyeji.IcanSell.data.Drink;
import com.codeyeji.IcanSell.data.Result;
import com.codeyeji.IcanSell.data.Stockstat;
import com.codeyeji.IcanSell.data.Sales;
import com.codeyeji.IcanSell.data.Search;
import com.codeyeji.IcanSell.repository.AdminRepository;
import com.codeyeji.IcanSell.service.AdminService;
import com.codeyeji.IcanSell.service.ClientService;

@RestController
@RequestMapping("/api")
public class ApiController {
	@Autowired
	AdminService adminService;
	@Autowired
	ClientService clientService;
	@Autowired
	SecurityConfig securityConfig;
	
	@PostMapping("/addAdmin") // 관리자 추가
	public Result addAdmin(@RequestBody Admin admin) {
		if(!adminService.findAdminId(admin.getAdminId()).isPresent()) { // 중복되는 관리자가 없으면 등록 가능
			
			String pw = securityConfig.passwordEncoder().encode(admin.getaPw()); // 비밀번호 암호화
			admin.setaPw(pw);
			admin.setRole("admin"); // 권한 지정
			adminService.addAdmin(admin);
			
			return new Result("ok");
		} else {
			return new Result("ng");
		}
		
	}

	
	@PostMapping("/addDrink") // 새로운 음료 등록
	public void addDrinkImage(
			@RequestParam(name="dName") String dName,
			@RequestParam(name="dPrice") int dPrice,
			@RequestParam(name="dStock") int dStock,
			@RequestParam(name="dImage") MultipartFile files, // form으로 전송했던 이미지 파일
			HttpServletRequest request, // 세션을 이용한 이미지 저장용 폴더 상대경로를 가져오기 위한 것
			HttpServletResponse response) throws IOException {
		
		if(files.isEmpty() || dName=="" || dPrice == 0) { // 업로드된 이미지가 없을 경우 또는 입력값이 누락된 경우
			response.sendRedirect("/admin/addFail"); // 등록 실패 페이지
		} else {
			
			Drink drink = new Drink(dName,dPrice,dStock);
			String path =  request.getSession().getServletContext().getRealPath("\\"); // webapp 폴더의 상대경로
			
			UUID uuid = UUID.randomUUID(); // 이미지에 고유 UUID 부여
			String saveImgName = uuid.toString()+"_"+files.getOriginalFilename(); // UUID값_이미지이름
			
			try { // 이미지 경로를 DB에 셋팅
				String baseDir = path;
				String filePath = baseDir + "\\static\\drinkImages\\" + saveImgName; // 생성된 경로 -> webapp/static/drinkImages/UUID값_이미지이름
				System.out.println(filePath);
				
				files.transferTo(new File(filePath)); // 해당 경로에 이미지 파일 저장
				
				drink.setdImgSrc(filePath);
				drink.setdImgName(saveImgName);
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			// 음료의 판매상태
			Stockstat statId1 = new Stockstat(1); // 판매중
			Stockstat statId2 = new Stockstat(2); // 재고소진(입고예정)
			
			if(adminService.findDrink(drink.getdName())==null) { // 중복되는 음료가 없으면 등록 가능
				
				if(drink.getdStock()>0) { // 할당한 재고가 0보다 큰가?
					drink.setStatId(statId1); // 판매중으로 변경
				} else {
					drink.setStatId(statId2); // 재고소진(입고예정)으로 변경
				}
				adminService.addDrink(drink);		
				response.sendRedirect("/admin/addOk"); // 등록 성공한 후 이동할 페이지
			} else {
				response.sendRedirect("/admin/addFail"); // 등록 실패한 후 이동할 페이지
			}
			
		}

		
	}
	
	
	@DeleteMapping("/deleteDrink") // 음료 삭제
	public Result deleteDrink(@RequestBody Drink drink) {
		if(adminService.findDrinkId(drink.getDrinkId()).isPresent()) {
			
			try { // 해당 음료의 이미지 파일 삭제
				String filePath = drink.getdImgSrc();
				System.out.println("상품이미지 삭제 : "+filePath);
				File file = new File(filePath);
				file.delete();
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			adminService.deleteDrink(drink.getDrinkId());
			return new Result("ok");
		} else {
			return new Result("ng");
		}
	}
	
	@PutMapping("/putHideDrink") // 상품 숨김처리
	public Result putHideDrink(@RequestBody Drink drink) {
		Drink findDrink = adminService.findDrinkId(drink.getDrinkId()).get();
		if(findDrink == null) {
			return new Result("ng");
		} else {
			Stockstat statId3 = new Stockstat(3); // 숨김상태
			findDrink.setStatId(statId3);
			adminService.addDrink(findDrink);
			return new Result("ok");
		}

	}
	
	@PutMapping("/putNonHideDrink") // 상품 숨김 해제
	public Result putNonHideDrink(@RequestBody Drink drink) {
		Drink findDrink = adminService.findDrinkId(drink.getDrinkId()).get();
		if(findDrink == null) {
			return new Result("ng");
		} else {
			Stockstat statId1 = new Stockstat(1); // 판매중으로 변경
			findDrink.setStatId(statId1);
			adminService.addDrink(findDrink);
			return new Result("ok");
		}

	}

	
	@PutMapping("/putDrinkName") // 음료 이름 변경
	public Result putDrinkName(@RequestBody Drink drink) {
		Optional<Drink> findDrink = adminService.findDrinkId(drink.getDrinkId());
		if(findDrink.isPresent()) {
			findDrink.get().setdName(drink.getNewdName());
			adminService.addDrink(findDrink.get());
			return new Result("ok");
		} else {
			return new Result("ng");
		}
	}
	
	@PutMapping("/putDrinkPrice") // 음료 가격 변경
	public Result putDrinkPrice(@RequestBody Drink drink) {
		Optional<Drink> findDrink = adminService.findDrinkId(drink.getDrinkId());
		if(findDrink.isPresent()) {
			findDrink.get().setdPrice(drink.getNewdPrice());
			adminService.addDrink(findDrink.get());
			return new Result("ok");
		} else {
			return new Result("ng");
		}
	}
	
	@PutMapping("/putDrinkStock") // 음료 재고 변경
	public Result putDrinkStock(@RequestBody Drink drink) {
		Stockstat statId1 = new Stockstat(1); // 판매중
		Stockstat statId2 = new Stockstat(2); // 재고소진(입고예정)
		Stockstat statId3 = new Stockstat(3); // 숨김상태
	
		Optional<Drink> findDrink = adminService.findDrinkId(drink.getDrinkId());
		if(findDrink.isPresent()) {
			
			if(findDrink.get().getStatId().getStatId()==3) { // 지금 수정하는 음료가 숨김처리 되어있는 상태라면?
				findDrink.get().setStatId(statId3); // 숨김 상태로 유지
			} else if(drink.getNewdStock() == 0) {
				findDrink.get().setStatId(statId2);
			} else {
				findDrink.get().setStatId(statId1);
			}
			
			findDrink.get().setdStock(drink.getNewdStock());
			adminService.addDrink(findDrink.get());
			return new Result("ok");
		} else {
			return new Result("ng");
		}		
	}
	
	@PostMapping("/putDrinkImg") // 음료 이미지 변경
	public void putDrinkImg(
			@RequestParam(name="drinkId") int drinkId,
			@RequestParam(name="newdImg") MultipartFile newFiles,
			@RequestParam(name="currentdImgSrc") String currentdImgSrc,
			HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		Optional<Drink> findDrink = adminService.findDrinkId(drinkId);
		if(findDrink.isPresent()) {
			
			if(newFiles.isEmpty()) { // 업로드된 이미지가 없을 경우
				response.sendRedirect("/admin/imageFail");
			} else { // 업로드 된 이미지가 있을 경우
				
				try { // 이미지 경로 및 이름 바꾸기
					String path =  request.getSession().getServletContext().getRealPath("\\"); // webapp 폴더의 상대경로
					String currnetdImgSrc = currentdImgSrc; // 기존 이미지 경로
					String baseDir = path;
					String newFilePath = baseDir + "\\static\\drinkImages\\" + newFiles.getOriginalFilename(); // 새로운 이미지 파일 경로
					
					File file = new File(currnetdImgSrc);
					file.delete(); // 기존 이미지 삭제
					System.out.println("기존 상품이미지 삭제 : "+currnetdImgSrc);
					
					newFiles.transferTo(new File(newFilePath)); // 해당 경로에 새로운 이미지 파일 저장
					findDrink.get().setdImgSrc(newFilePath); // 새로운 이미지 경로로 갱신
					findDrink.get().setdImgName(newFiles.getOriginalFilename()); // 새로운 이미지 이름으로 갱신
					adminService.addDrink(findDrink.get());
					System.out.println("새 상품이미지 추가 : "+newFilePath);
				} catch(Exception e) {
					e.printStackTrace();
				}
				
				response.sendRedirect("/admin/imageOk");
			}
			
		} else { // 업로드 된 이미지가 있으나 알 수 없는 오류일 경우
			response.sendError(0, "ERROR!!!");
		}	
		
	}
	
	
	@PostMapping("/order") // 장바구니에 물건을 담은 상태로 주문 페이지로 이동
	public Result drinkOrder(HttpServletRequest request, @RequestBody List<Map<String, Object>> drinkList){
		if(drinkList == null) {
			return new Result("ng");
		} else {
			HttpSession session = request.getSession(); // 새로운 세션 생성
			session.setAttribute("drinkList", drinkList); // 세션에 장바구니 객체배열 데이터 추가
			System.out.println(drinkList);
			return new Result("ok");
		}		
		
	}
	
	@PostMapping("/pay") // 결제
	public Result drinkPay(@RequestBody List<Map<String, Object>> drinkList) {
		if(drinkList == null) {
			return new Result("ng");
		} else {
			System.out.println(drinkList +" row:"+ drinkList.size()); // DB에 저장될 현재 판매내역 갯수
			
			for(int i=0; i<drinkList.size(); i++) {
				int drinkId = Integer.parseInt(drinkList.get(i).get("drinkId").toString());
				
				//new Sales(String sName, int sPay, int sCount, int sPaySum, LocalDateTime sDate)
				Sales sales = new Sales(
						drinkList.get(i).get("sName").toString(),
						Integer.parseInt(drinkList.get(i).get("sPay").toString()),
						Integer.parseInt(drinkList.get(i).get("sCount").toString()),
						Integer.parseInt(drinkList.get(i).get("sPaySum").toString()),
						LocalDateTime.now());
				clientService.addSales(sales); // 판매내역 저장
				
				Drink findDrink = clientService.findDrinkId(drinkId); // 판매된 음료를 찾는다
				findDrink.setdStock(findDrink.getdStock()-Integer.parseInt(drinkList.get(i).get("sCount").toString())); // 판매된 갯수만큼 재고값 수정
				
				Stockstat statId2 = new Stockstat(2); // 재고소진(입고예정)
				if(findDrink.getdStock() == 0) { // 재고가 전부 소진이 되었나?
					findDrink.setStatId(statId2);
				}		
				
				clientService.editStock(findDrink);	
			}
				
			return new Result("ok");
		}	
	}
	
	
	@PutMapping("/putAdminName") // 관리자 이름 수정
	public Result putAdminName(@RequestBody Admin admin) {
		Admin findAdmin = adminService.findAdminId(admin.getAdminId()).get();
		if(findAdmin == null) {
			return new Result("ng");
		} else {
			findAdmin.setaName(admin.getNewadminName());
			adminService.addAdmin(findAdmin);
			return new Result("ok");
		}
		
	}
	
	@DeleteMapping("/deleteAdmin") // 관리자 삭제
	public Result deleteAdmin(@RequestBody Admin admin) {
		Admin findAdmin = adminService.findAdminId(admin.getAdminId()).get();
		if(findAdmin==null) {
			return new Result("ng");
		} else {
			adminService.deleteAdmin(admin.getAdminId());
			return new Result("ok");
		}
	}
	
	@PostMapping("/search") // 판매내역 조회
	public List<Sales> searchSales(@RequestBody Search search) {
		if(search.getDrinkName()=="") { // 입력된 음료이름이 없다면 이름없이 날짜만으로 검색
			List<Sales> findSales = adminService.findSalesDate(search.getStartDate(), search.getEndDate());
			return findSales;
		} else {
			List<Sales> findSales = adminService.findSales(search.getDrinkName(), search.getStartDate(), search.getEndDate());
			return findSales;
		}
	}
	
	
	
	
}
