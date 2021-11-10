package com.codeyeji.IcanSell.controller;

import java.io.File;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.codeyeji.IcanSell.repository.AdminRepository;
import com.codeyeji.IcanSell.service.AdminService;


@RestController
@RequestMapping("/api")
public class ApiController {
	@Autowired
	AdminService adminService;
	@Autowired
	SecurityConfig securityConfig;
	
	@PostMapping("/addAdmin")
	public Result addAdmin(@RequestBody Admin admin) {
		if(!adminService.findAdminId(admin.getAdminId()).isPresent()) { // 중복되는 관리자가 없으면 등록 가능
			String pw = securityConfig.passwordEncoder().encode(admin.getaPw());
			admin.setaPw(pw);
			admin.setRole("admin");
			adminService.addAdmin(admin);
			return new Result("ok");
		} else {
			return new Result("ng");
		}
		
	}

	
	@PostMapping("/addDrink")
	public String addDrinkImage(
			@RequestParam(name="dName") String dName,
			@RequestParam(name="dPrice") int dPrice,
			@RequestParam(name="dStock") int dStock,
			@RequestParam(name="dImage") MultipartFile files) {

		Drink drink = new Drink(dName,dPrice,dStock);
		
		try { // 이미지 경로를 DB에 셋팅
			String baseDir = "C:\\Spring\\workspace\\team18\\IcanSell\\src\\main\\resources\\static\\drinkImages";
			String filePath = baseDir + "\\" + files.getOriginalFilename();
			files.transferTo(new File(filePath)); // 해당 경로에 이미지 파일 저장
			drink.setdImgSrc(filePath);
			drink.setdImgName(files.getOriginalFilename());
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		Stockstat statId1 = new Stockstat(1); // 판매중
		Stockstat statId2 = new Stockstat(2); // 재고소진(입고예정)
		
		if(adminService.findDrink(drink.getdName())==null) { // 중복되는 음료가 없으면 등록 가능
			
			if(drink.getdStock()>0) { // 할당한 재고가 0보다 큰가?
				drink.setStatId(statId1); // 판매중으로 변경
			} else {
				drink.setStatId(statId2); // 재고소진(입고예정)으로 변경
			}
			adminService.addDrink(drink);
			return "<h2>상품 등록이 완료되었습니다. </h2>"
					+ "<meta http-equiv=\"refresh\" content=\"2;url=/admin/testCrud\" />"; // 등록 성공한 후 이동할 페이지
		} else {
			return "<h2>상품 등록을 실패하였습니다. 중복된 음료입니다. </h2>"
					+ "<meta http-equiv=\"refresh\" content=\"2;url=/admin/test\" />"; // 등록 실패한 후 이동할 페이지
		}
		
	}
	
	
	@DeleteMapping("/deleteDrink")
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

	@PutMapping("/putDrinkName")
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
	
	@PutMapping("/putDrinkPrice")
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
	
	@PutMapping("/putDrinkStock")
	public Result putDrinkStock(@RequestBody Drink drink) {
		Stockstat statId1 = new Stockstat(1); // 판매중
		Stockstat statId2 = new Stockstat(2); // 재고소진(입고예정)
		
		Optional<Drink> findDrink = adminService.findDrinkId(drink.getDrinkId());
		if(findDrink.isPresent()) {
			
			if(drink.getNewdStock() == 0) { // 재고 갯수 검사후 상태 부여
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
	
	@PutMapping("/putDrinkImg")
	public Result putDrinkImg(@RequestParam(name="drinkId") int drinkId, @RequestParam(name="dImage") MultipartFile files) {
		if(adminService.findDrinkId(drinkId).isPresent()) {
			
			
			return new Result("ok");
		} else {
			return new Result("ng");
		}
	}
	
	
}
