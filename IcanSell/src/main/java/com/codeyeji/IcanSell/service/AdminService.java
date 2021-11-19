package com.codeyeji.IcanSell.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeyeji.IcanSell.data.Admin;
import com.codeyeji.IcanSell.data.Drink;
import com.codeyeji.IcanSell.data.Sales;
import com.codeyeji.IcanSell.repository.AdminRepository;
import com.codeyeji.IcanSell.repository.DrinkRepository;
import com.codeyeji.IcanSell.repository.SalesRepository;

@Service
public class AdminService {
	@Autowired
	private AdminRepository adminRepository;
	@Autowired
	private DrinkRepository drinkRepository;
	@Autowired
	private SalesRepository salesRepository;
	
	
	public void addAdmin(Admin admin) {adminRepository.save(admin);} // 관리자 등록
	public void deleteAdmin(String adminId) {adminRepository.deleteById(adminId);} // 관리자 삭제
	
	public Optional<Admin> findAdminId(String adminId) { // 아이디를 기반으로 해당하는 관리자 찾기
		Optional<Admin> findAdmin = adminRepository.findById(adminId);
		return findAdmin;
	}
	
	public List<Admin> findAdminAll() {return adminRepository.findByRole("admin");}
	
	public Drink findDrink(String dName) { // 이름을 기반으로 해당하는 음료상품 찾기
		Drink findDrink = drinkRepository.findBydName(dName);
		return findDrink;
	}
	
	public Optional<Drink> findDrinkId(int drinkId) { // PK(음료 고유아이디)를 기반으로 해당하는 음료상품 찾기
		Optional<Drink> findDrink = drinkRepository.findById(drinkId);
		return findDrink;
	}
	
	public List<Drink> findDrinkAll() { // 모든 음료상품 찾기
		return drinkRepository.findAll();
	}
	
	public void addDrink(Drink drink) {drinkRepository.save(drink); } // 상품(음료) 등록
	public void deleteDrink(int drinkId) {drinkRepository.deleteById(drinkId);} // 상품(음료)삭제
	
	public List<Sales> findSalesAll() {
		return salesRepository.findAll();
	}
	
	public List<Sales> findSales(String drinkName, String startDate, String endDate) {
		return salesRepository.findSales(drinkName, startDate, endDate);
	}
	
	public List<Sales> findSalesDate(String startDate, String endDate) {
		return salesRepository.findSalesDate(startDate, endDate);
	}
	
	
	
}