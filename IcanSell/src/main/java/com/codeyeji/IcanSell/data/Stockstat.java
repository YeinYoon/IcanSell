package com.codeyeji.IcanSell.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="stockstat")
public class Stockstat {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int statId;
	
	private String statName;

	
	public Stockstat() {}
	public Stockstat(int stat) {
		this.statId = stat;
	}
	
	public int getStatId() {
		return statId;
	}

	public void setStatId(int statId) {
		this.statId = statId;
	}

	public String getStatName() {
		return statName;
	}

	public void setStatName(String statName) {
		this.statName = statName;
	}
	
	
}
