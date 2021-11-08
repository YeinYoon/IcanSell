package com.codeyeji.IcanSell.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.codeyeji.IcanSell.repository.AdminRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	@Autowired
	AdminRepository adminRepository;
	
	@Override
	public UserDetails loadUserByUsername(String adminId) throws UsernameNotFoundException {
		
	Optional<com.codeyeji.IcanSell.data.Admin> findAdmin = adminRepository.findById(adminId);
	if(findAdmin.isEmpty()) {
	throw new UsernameNotFoundException("해당하는 관리자를 찾지 못했습니다.");
	}
	List<GrantedAuthority> auths = new ArrayList<>();
	auths.add(new SimpleGrantedAuthority("QUERY"));
	if(findAdmin.get().getRole().equals("admin")||findAdmin.get().getRole().equals("sysadmin")) {
		auths.add(new SimpleGrantedAuthority("WRITE"));
	}
		
	UserDetails ud = User
		.withUsername(findAdmin.get().getaName())
		.password(findAdmin.get().getaPw())
		.authorities(auths)
		.roles(findAdmin.get().getRole())
		.build();
	return ud;
	
	}
	
}
