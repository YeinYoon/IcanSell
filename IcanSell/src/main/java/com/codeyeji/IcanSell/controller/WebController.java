package com.codeyeji.IcanSell.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codeyeji.IcanSell.service.AdminService;
import com.codeyeji.IcanSell.service.ClientService;

@Controller
public class WebController {
	
	@Autowired
	ClientService clientService;
	@Autowired
	AdminService adminService;

	// 인덱스 페이지 웹
	@GetMapping("/")
	public String getIndex() {
		return "index";
	}
	@GetMapping("/login")
	public String getLogin() {
		return "admin/login";
	}

	
	// 관리자(판매자) 웹
	@Controller
	@RequestMapping("/admin")
	public class AdminWeb {

		@GetMapping("/")
		public String getAdminIndex() {
			return "admin/admin";
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
		public String getAdminTest(Model model) {
			model.addAttribute("adminList",adminService.findAdminAll());
			return "test";
		}
		
		@GetMapping("/testCrud")
		public String getAdminTestCrud(Model model) {
			model.addAttribute("drinks", adminService.findDrinkAll());
			return "testCrud";
		}
		
	}
	// 테스트용 접근금지 html
	@GetMapping("/denied")
	public String denied() {
	 return "denied";
	}

	
	
	// 시스템 관리자 웹
	@Controller
	@RequestMapping("/sysadmin")
	public class SysAdminWeb {

		@GetMapping("/")
		public String getSysAdminSeller() {
			return "sysadmin/sysadmin";
		}
		
	}
	
	
	// 소비자 웹
	@Controller
	@RequestMapping("/main")
	public class ClientWeb {
		
		@GetMapping("/")
		public String getMain() {
			return "client/main";
		}
		
		@GetMapping("/cart")
		public String getMainKart() {
			return "client/cart";
		}
		
		// 테스트용(코드 테스트용)	
		@GetMapping("/testMain")
		public String getMainTest(Model model) {
			model.addAttribute("drinks", clientService.findDrinkAll());
			return "testMain";
		}
		
	}
	// 소비자 웹 주문 웹
	@Controller
	@RequestMapping("/order")
	public class ClientWebOrder {
		@GetMapping("/")
		public String getOrder(HttpSession session, Model model) {
			Object drinkList = session.getAttribute("drinkList");
			model.addAttribute("drinkList",drinkList);
			return "client/order";
		}
		@GetMapping("/ty")
		public String getOrderTy() {
			return "client/ty";
		}
	}
	
}
