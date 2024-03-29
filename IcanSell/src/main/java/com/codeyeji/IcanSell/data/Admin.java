package com.codeyeji.IcanSell.data;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.codeyeji.IcanSell.data.Drink;

@Entity
@Table(name="admin")
public class Admin { // 관리자(판매자) 테이블
	@Id
	private String adminId;
	
	@Column(nullable=false, length=100)
	private String aPw;
	private String aName;
	
	@Column(nullable=false,length=100)
	private String role="admin";
	
	@Transient
	private String newadminName;
	@Transient 
	private int newaPw;
	
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getaPw() {
		return aPw;
	}

	public void setaPw(String aPw) {
		this.aPw = aPw;
	}

	public String getaName() {
		return aName;
	}

	public void setaName(String aName) {
		this.aName = aName;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	
	public String getNewadminName() {
		return newadminName;
	}

	public void setNewadminName(String newadminName) {
		this.newadminName = newadminName;
	}

	public int getNewaPw() {
		return newaPw;
	}

	public void setNewaPw(int newaPw) {
		this.newaPw = newaPw;
	}

}
