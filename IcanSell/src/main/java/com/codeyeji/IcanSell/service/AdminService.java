package com.codeyeji.IcanSell.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeyeji.IcanSell.data.Admin;
import com.codeyeji.IcanSell.data.Drink;
import com.codeyeji.IcanSell.repository.AdminRepository;
import com.codeyeji.IcanSell.repository.DrinkRepository;

@Service
public class AdminService {
	@Autowired
	private AdminRepository adminRepository;
	@Autowired
	private DrinkRepository drinkRepository;
	
	
	public void addAdmin(Admin admin) {adminRepository.save(admin);} // 관리자 등록
	public void deleteAdmin(int adminId) {adminRepository.deleteById(adminId);} // 관리자 삭제
	
	
	public Admin findAdminId(String aId) { // 아이디를 기반으로 해당하는 관리자 찾기
		Admin findAdmin = adminRepository.findByaId(aId);
		return findAdmin;
	}
	
	public void addDrink(Drink drink) {drinkRepository.save(drink);} // 상품(음료) 등록
	
	
	
	
	
}