package com.codeyeji.IcanSell.data;

public class Search {
	String drinkName;
	String startDate;
	String endDate;

	public String getDrinkName() {
		return drinkName;
	}
	public void setDrinkName(String drinkName) {
		this.drinkName = drinkName;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public Search() {}
	public Search(String drinkName, String startDate, String endDate) {
		this.drinkName = drinkName;
		this.startDate = startDate;
		this.endDate = endDate;
	}

}
