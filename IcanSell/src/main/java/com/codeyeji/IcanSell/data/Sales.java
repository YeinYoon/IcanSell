package com.codeyeji.IcanSell.data;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="sales")
public class Sales{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int salesId;
	
	private int sCount;
	private int sPay;
	private LocalDateTime sDate;
	private int sPaySum;
	
	
	@ManyToOne(targetEntity=Drink.class)
	@JoinColumn(name="drinkId")
	private Drink drink;
	
	
	public Sales() {}
	public Sales(int drinkId, int sPay, int sCount, int sPaySum, LocalDateTime sDate) {
		Drink drink = new Drink();
		drink.setDrinkId(drinkId);
		this.drink = drink;
		this.sPay = sPay;
		this.sCount = sCount;
		this.sPaySum = sPaySum;
		this.sDate = sDate;
	}
	
	public int getsCount() {
		return sCount;
	}
	public void setsCount(int sCount) {
		this.sCount = sCount;
	}
	public int getsPay() {
		return sPay;
	}
	public void setsPay(int sPay) {
		this.sPay = sPay;
	}
	public LocalDateTime getsDate() {
		return sDate;
	}
	public void setsDate(LocalDateTime sDate) {
		this.sDate = sDate;
	}
	public int getsPaySum() {
		return sPaySum;
	}
	public void setsPaySum(int sPaySum) {
		this.sPaySum = sPaySum;
	}
	public Drink getDrink() {
		return drink;
	}
	public void setDrink(Drink drink) {
		this.drink = drink;
	}
	public int getSalesId() {
		return salesId;
	}
	public void setSalesId(int salesId) {
		this.salesId = salesId;
	}
	
}
