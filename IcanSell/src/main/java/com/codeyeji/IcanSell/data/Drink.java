package com.codeyeji.IcanSell.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.codeyeji.IcanSell.data.Admin;


@Entity
@Table(name="drink")
public class Drink {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int drinkId;
	
	@Column(nullable=false, length=100)
	private String d_name;
	
	private int d_price;
	private int d_stock;
	
	@ManyToOne(targetEntity=Admin.class)
	@JoinColumn(name="adminId")
	private Admin admin;
	
	@OneToOne
	@JoinColumn(name="statId")
	private Statistics stat;
}
