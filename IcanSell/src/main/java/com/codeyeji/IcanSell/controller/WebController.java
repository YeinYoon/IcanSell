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
	@GetMapping("/login")
	public String getLogin() {
		return "login";
	}

	
	// 관리자(판매자) 웹
	@Controller
	@RequestMapping("/admin")
	public class AdminWeb {
		
		@GetMapping("/")
		public String getAdminIndex() {
			return "admin";
		}
		
		@GetMapping("/sales")
		public String getAdminSales() {
			return "sales";
		}
		
		@GetMapping("/stock")
		public String getAdminStock() {
			return "stock";
		}
		
		@GetMapping("/add")
		public String getAdminAdd() {
			return "add";
		}
		
		@GetMapping("/member")
		public String getAdminMember() {
			return "member";
		}
	// 테스트용	
		@GetMapping("/test")
		public String getAdminTest() {
			return "test";
		}
		
	}

	
	// 시스템 관리자 웹
	@Controller
	@RequestMapping("/sysadmin")
	public class SysAdminWeb {
		
		@GetMapping("/")
		public String getSysAdminIndex() {
			return "sysadmin";
		}
		
		@GetMapping("/seller")
		public String getSysAdminSeller() {
			return "seller";
		}
		
	}
	
	
	// 소비자 웹
	@Controller
	@RequestMapping("/main")
	public class ConsumerWeb {
		
		@GetMapping("/")
		public String getMain() {
			return "main";
		}
		
		@GetMapping("/cart")
		public String getMainKart() {
			return "cart";
		}
		
		@RequestMapping("/order")
		@GetMapping("/")
		public String getOrder() {
			return "order";
		}
		
		@GetMapping("/ty")
		public String getOrderTy() {
			return "ty";
		}
		
	}
	
}
