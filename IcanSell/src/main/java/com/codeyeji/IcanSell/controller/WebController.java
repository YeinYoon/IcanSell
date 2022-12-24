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
		return "mainIndex";
	}

	@GetMapping("/login")
	public String getLogin() {
		return "admin/login";
	}
	
	@GetMapping("/admin")
	public String getAdminIndex(Model model) {
		model.addAttribute("salesList", adminService.findSalesAll());
		return "admin/admin";
	}
	
	// 관리자(판매자) 웹
	@Controller
	@RequestMapping("/admin")
	public class AdminWeb {

		@GetMapping("/edit")
		public String getAdminEdit(Model model) {
			model.addAttribute("drinks", adminService.findDrinkAll());
			return "admin/edit";
		}
		
		@GetMapping("/add")
		public String getAdminAdd(Model model) {
			model.addAttribute("adminList",adminService.findAdminAll());
			return "admin/add";
		}
		
		@GetMapping("/member")
		public String getAdminMember(Model model) {
			model.addAttribute("adminList",adminService.findAdminAll());
			return "admin/member";
		}
		
		//등록, 수정에 대한 응답결과 페이지
		@GetMapping("/addOk")
		public String getAdminOk() {
			return "admin/addOk";
		}
		@GetMapping("/addFail")
		public String getAdminFail() {
			return "admin/addFail";
		}
		
		@GetMapping("/imageOk")
		public String getAdminImgOk() {
			return "admin/imageOk";
		}
		@GetMapping("/imageFail")
		public String getAdminImgFail() {
			return "admin/imageFail";
		}
		
	}
	
	
	// 접근권한에 따른 접근금지 html
	@GetMapping("/denied")
	public String denied() {
	 return "denied";
	}

	
	// 시스템 관리자 웹
	@Controller
	@RequestMapping("/sysadmin")
	public class SysAdminWeb {

		@GetMapping("/")
		public String getSysAdminSeller(Model model) {
			model.addAttribute("adminList",adminService.findAdminAll());
			return "sysadmin/sysadmin";
		}
		
	}
	
	
	// 소비자 웹
	@Controller
	@RequestMapping("/main")
	public class ClientWeb {
		
		@GetMapping("/")
		public String getMain(Model model) {
			model.addAttribute("drinks", clientService.findDrinkAll());
			return "client/main";
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
