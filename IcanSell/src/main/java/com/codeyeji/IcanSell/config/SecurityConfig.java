package com.codeyeji.IcanSell.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;

import com.codeyeji.IcanSell.repository.AdminRepository;
import com.codeyeji.IcanSell.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsServiceImpl userDetails;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		// 인증하지 않을 주소 추가.
		web.ignoring().antMatchers("/css/**", "/js/**", "/image/**","/banner/**");
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetails).passwordEncoder(passwordEncoder());
		System.out.println(passwordEncoder().encode("123"));
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests() 
		.antMatchers("/admin/**").hasAnyRole("admin","sysadmin") // 관리자와 시스템관리자만 접근 가능.
		.antMatchers("/sysadmin/**").hasRole("sysadmin") // 시스템 관리자만 접근 가능.
		.antMatchers("/**").permitAll() // 위 경우를 빼고 모든 권한을 줌=로그인 필요 없음.
		.anyRequest().authenticated() 
		.and()
		.formLogin()
		.loginPage("/login") //이 줄을 지우면 스프링이 제공하는 폼이 출력됨.
		.defaultSuccessUrl("/admin/") // 로그인 성공하면 갈 주소.
		.permitAll()
		.and()
		.logout()
		.logoutUrl("/logout")
		.logoutSuccessUrl("/") // 로그아웃 이후 갈 페이지.
		.invalidateHttpSession(true)
		.and()
		.exceptionHandling()
		.accessDeniedPage("/denied") // 403 에러 처리. 접근권한없음
		.and()
		.csrf()
		.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()); 
	}
}
