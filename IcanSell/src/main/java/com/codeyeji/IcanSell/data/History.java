package com.codeyeji.IcanSell.data;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="history")
public class History { // 음료 판매내역 테이블
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int historyId;
	
	private LocalDateTime h_date;
	private int h_price;
	
	@ManyToOne(targetEntity=Drink.class)
	@JoinColumn(name="drinkId")
	private Drink drink;
}
