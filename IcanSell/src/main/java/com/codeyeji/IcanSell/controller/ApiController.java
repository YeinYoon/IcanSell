package com.codeyeji.IcanSell.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
	
	@PostMapping("/addAdmin")
	public Result addAdmin(@RequestBody Admin admin) {
		if(!adminService.findAdminId(admin.getAdminId()).isPresent()) { // 중복되는 관리자가 없으면 등록 가능
			adminService.addAdmin(admin);
			return new Result("ok");
		} else {
			return new Result("ng");
		}
		
	}

	@PostMapping("/addDrink")
	public Result addDrink(@RequestBody Drink drink, @RequestParam(name="dImage") MultipartFile files) {
		Stockstat statId1 = new Stockstat(1); // 판매중
		Stockstat statId2 = new Stockstat(2); // 재고소진(입고예정)
		
		if(adminService.findDrink(drink.getdName())==null) { // 중복되는 음료가 없으면 등록 가능
			
			try { // 이미지 경로를 DB에 셋팅
				String baseDir = "C:\\Spring\\workspace\\team18\\IcanSell\\src\\main\\resources\\static\\drinkImages";
				String filePath = baseDir + "\\" + files.getOriginalFilename();
				files.transferTo(new File(filePath));
				drink.setDimage(filePath);
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			if(drink.getdStock()>0) { // 할당한 재고가 0보다 큰가?
				drink.setStatId(statId1); // 판매중으로 변경
			} else {
				drink.setStatId(statId2); // 재고소진(입고예정)으로 변경
			}
			adminService.addDrink(drink);
			return new Result("ok");
		} else {
			return new Result("ng");
		}
	}

	
	
	
	
//로그인 코드인데 아직 수업 진도 못빼서 미완성
//	@PostMapping("/adminLogin")
//	public Result adminLogin(@RequestBody Admin admin) {
//		if(adminService.findAdminId(admin.getA_id())==null) { // 해당하는 관리자가 없으면 로그인 불가
//			return new Result("faild");
//		} else {
			
//		}
//	}
	
	
}
