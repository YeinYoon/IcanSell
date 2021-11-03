package com.codeyeji.IcanSell.data;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.codeyeji.IcanSell.data.Admin;


@Entity
@Table(name="drink")
public class Drink { // 음료 테이블
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int drinkId;
	
	@Column(nullable=false, length=20, unique=true)
	private String dName;
	
	private int dPrice;
	private int dStock;
	private String image;
	
	@ManyToOne(targetEntity=Admin.class)
	@JoinColumn(name="adminId")
	private Admin admin;
	
	@OneToMany
	@JoinColumn(name="historyId")
	private List<History> historys; 
	
	
	public int getDrinkId() {
		return drinkId;
	}

	public void setDrinkId(int drinkId) {
		this.drinkId = drinkId;
	}

	public String getdName() {
		return dName;
	}

	public void setdName(String dName) {
		this.dName = dName;
	}

	public int getdPrice() {
		return dPrice;
	}

	public void setdPrice(int dPrice) {
		this.dPrice = dPrice;
	}

	public int getdStock() {
		return dStock;
	}

	public void setdStock(int dStock) {
		this.dStock = dStock;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public List<History> getHistorys() {
		return historys;
	}

	public void setHistorys(List<History> historys) {
		this.historys = historys;
	}
}
