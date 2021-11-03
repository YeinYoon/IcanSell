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
import com.codeyeji.IcanSell.data.Drink;

@Entity
@Table(name="admin")
public class Admin { // 관리자(판매자) 테이블
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int adminId;
	
	@Column(nullable=false, length=100, unique=true)
	private String aId;
	@Column(nullable=false, length=100)
	private String aPw;

	private String aName;
	
	@OneToMany
	@JoinColumn(name="drinkId")
	private List<Drink> drinks;
	
	
	public String getaId() {
		return aId;
	}

	public void setaId(String aId) {
		this.aId = aId;
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

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public List<Drink> getDrinks() {
		return drinks;
	}

	public void setDrinks(List<Drink> drinks) {
		this.drinks = drinks;
	}
}
