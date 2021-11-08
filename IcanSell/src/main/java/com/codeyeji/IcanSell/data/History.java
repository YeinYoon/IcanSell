package com.codeyeji.IcanSell.data;

import java.time.LocalDateTime;

import javax.persistence.Column;
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

	private LocalDateTime hDate;
	private int hPay;
	
	
	public int getHistoryId() {
		return historyId;
	}
	public void setHistoryId(int historyId) {
		this.historyId = historyId;
	}
	public LocalDateTime gethDate() {
		return hDate;
	}
	public void sethDate(LocalDateTime hDate) {
		this.hDate = hDate;
	}
	public int gethPay() {
		return hPay;
	}
	public void sethPay(int hPay) {
		this.hPay = hPay;
	}
	

}
