package com.codeyeji.IcanSell.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.codeyeji.IcanSell.data.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer>{
	public Admin findByaId(String string);
}
