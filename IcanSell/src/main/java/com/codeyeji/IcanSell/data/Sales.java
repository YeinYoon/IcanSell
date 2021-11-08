package com.codeyeji.IcanSell.data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@IdClass(SalesId.class)
@Table(name="sales")
public class Sales {
	@Id
	@ManyToOne
	@JoinColumn(name="drinkId")
	private Drink drink;
	
	@Id
	@ManyToOne
	@JoinColumn(name="historyId")
	private History history;
	
	private int sCount;
	private int sPay;
	
	public Drink getDrink() {
		return drink;
	}
	public void setDrink(Drink drink) {
		this.drink = drink;
	}
	public History getHistory() {
		return history;
	}
	public void setHistory(History history) {
		this.history = history;
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
	
	
}
