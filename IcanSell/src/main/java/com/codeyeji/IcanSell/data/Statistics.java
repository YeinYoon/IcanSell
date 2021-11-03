package com.codeyeji.IcanSell.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="statistics")
public class Statistics { // 음료 통계(총 판매액, 평균) 테이블
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int statId;
	
	private int stat_sum;
	private double stat_avg;
	
}
