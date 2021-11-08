package com.codeyeji.IcanSell.data;

import java.io.Serializable;

public class SalesId implements Serializable{	
	private Drink drink;
	private History history;
	
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
}
