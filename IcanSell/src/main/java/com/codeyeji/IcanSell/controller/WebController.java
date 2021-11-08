package com.codeyeji.IcanSell.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {

	// 인덱스 페이지 웹
	@GetMapping("/")
	public String getIndex() {
		return "index";
	}


	
	// 관리자(판매자) 웹
	@Controller
	@RequestMapping("/admin")
	public class AdminWeb {

		@GetMapping("/login")
		public String getLogin() {
			return "admin/login";
	}
		@GetMapping("/edit")
		public String getAdminEdit() {
			return "admin/edit";
		}
		
		@GetMapping("/add")
		public String getAdminAdd() {
			return "admin/add";
		}
		
		@GetMapping("/member")
		public String getAdminMember() {
			return "admin/member";
		}
	// 테스트용(코드 테스트용)	
		@GetMapping("/test")
		public String getAdminTest() {
			return "test";
		}
		
	}

	
	// 시스템 관리자 웹
	@Controller
	@RequestMapping("/sysadmin")
	public class SysAdminWeb {

		
		@GetMapping("/sysadmin")
		public String getSysAdminSeller() {
			return "sysadmin/sysadmin";
		}
		
	}
	
	
	// 소비자 웹
	@Controller
	@RequestMapping("/main")
	public class ConsumerWeb {
		
		@GetMapping("/")
		public String getMain() {
			return "client/main";
		}
		
		@GetMapping("/cart")
		public String getMainKart() {
			return "client/cart";
		}
		
		@RequestMapping("/order")
		@GetMapping("/order")
		public String getOrder() {
			return "client/order";
		}
		
		@GetMapping("/ty")
		public String getOrderTy() {
			return "client/ty";
		}
		
	}
	
}
