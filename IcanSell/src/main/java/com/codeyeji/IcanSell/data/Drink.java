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
import javax.persistence.Transient;

import com.codeyeji.IcanSell.data.Admin;


@Entity
@Table(name="drink")
public class Drink { // 음료 테이블
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int drinkId;
	
	@Column(nullable=false, length=20)
	private String dName;
	
	private int dPrice;
	private int dStock;
	private String dImgSrc;
	private String dImgName;
	
	
	@Transient // 컬럼으로 생성되지 않는 숨겨진 값, 수정용
	private String newdName;
	@Transient 
	private int newdPrice;
	@Transient 
	private int newdStock;
	
	@Transient 
	private String newdImgSrc;

	@Transient 
	private String newdImgName;
	
	
	@ManyToOne(targetEntity=Stockstat.class)
	@JoinColumn(name="statId")
	private Stockstat statId;
	
	
	public Drink() {}
	public Drink(String dName, int dPrice, int dStock) {
		this.dName = dName;
		this.dPrice = dPrice;
		this.dStock = dStock;
	}
	

	public Stockstat getStatId() {
		return statId;
	}

	public void setStatId(Stockstat statId) {
		this.statId = statId;
	}
	
	public void setStatId(int statId) {
		this.statId.setStatId(statId);
	}

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
	
	public String getdImgSrc() {
		return dImgSrc;
	}
	public void setdImgSrc(String dImgSrc) {
		this.dImgSrc = dImgSrc;
	}
	public String getdImgName() {
		return dImgName;
	}
	public void setdImgName(String dImgName) {
		this.dImgName = dImgName;
	}
	
	public String getNewdName() {
		return newdName;
	}
	public void setNewdName(String newdName) {
		this.newdName = newdName;
	}
	public int getNewdPrice() {
		return newdPrice;
	}
	public void setNewdPrice(int newdPrice) {
		this.newdPrice = newdPrice;
	}
	public int getNewdStock() {
		return newdStock;
	}
	public void setNewdStock(int newdStock) {
		this.newdStock = newdStock;
	}
	public String getNewdImgSrc() {
		return newdImgSrc;
	}
	public void setNewdImgSrc(String newdImgSrc) {
		this.newdImgSrc = newdImgSrc;
	}
	public String getNewdImgName() {
		return newdImgName;
	}
	public void setNewdImgName(String newdImgName) {
		this.newdImgName = newdImgName;
	}

}
