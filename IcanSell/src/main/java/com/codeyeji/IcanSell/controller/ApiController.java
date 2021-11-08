package com.codeyeji.IcanSell.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codeyeji.IcanSell.data.Admin;
import com.codeyeji.IcanSell.data.Result;
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

	
	
	
//로그인 코드인데 아직 수업 진도 못빼서 미완성
//	@PostMapping("/adminLogin")
//	public Result adminLogin(@RequestBody Admin admin) {
//		if(adminService.findAdminId(admin.getA_id())==null) { // 해당하는 관리자가 없으면 로그인 불가
//			return new Result("faild");
//		} else {
			
//		}
//	}
	
	
}
