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
	
	private String d_name;
	private int d_price;
	private int d_stock;
	
	@ManyToOne(targetEntity=Admin.class)
	@JoinColumn(name="adminId")
	private Admin admin;
	
	@OneToOne
	@JoinColumn(name="statId")
	private Statistics stat;
	
	@OneToMany
	@JoinColumn(name="historyId")
	private List<History> historys; 
}
