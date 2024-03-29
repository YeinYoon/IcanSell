package com.codeyeji.IcanSell.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.codeyeji.IcanSell.data.Admin;

public interface AdminRepository extends JpaRepository<Admin, String>{
	List<Admin> findByRole(String string);
	
}
