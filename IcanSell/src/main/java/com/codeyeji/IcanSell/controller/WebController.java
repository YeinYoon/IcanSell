package com.codeyeji.IcanSell.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {

	// 인덱스 페이지
	@GetMapping("/")
	public String getIndex() {
		return "admin";
	}
	@GetMapping("/login")
	public String getLogin() {
		return "admin";
	}

	
	// 관리자(판매자)
	@Controller
	@RequestMapping("/admin")
	public class AdminWeb {
		
		@GetMapping("/")
		public String getAdminIndex() {
			return "test";
		}
		
		@GetMapping("/sales")
		public String getAdminSales() {
			return "test";
		}
		
		@GetMapping("/stock")
		public String getAdminStock() {
			return "test";
		}
		
		@GetMapping("/add")
		public String getAdminAdd() {
			return "test";
		}
		
		@GetMapping("/member")
		public String getAdminMember() {
			return "test";
		}
		
	}

	
	// 시스템 관리자
	@Controller
	@RequestMapping("/sysadmin")
	public class SysAdminWeb {
		
		@GetMapping("/")
		public String getSysAdminIndex() {
			return "test";
		}
		
		@GetMapping("/seller")
		public String getSysAdminSeller() {
			return "test";
		}
		
	}
	
	
	// 소비자
	@Controller
	@RequestMapping("/main")
	public class ConsumerWeb {
		
		@GetMapping("/")
		public String getMain() {
			return "test";
		}
		
		@GetMapping("/kart")
		public String getMainKart() {
			return "test";
		}
		
		@RequestMapping("/order")
		@GetMapping("/")
		public String getOrder() {
			return "test";
		}
		
		@GetMapping("/ty")
		public String getOrderTy() {
			return "test";
		}
		
	}
	
}
