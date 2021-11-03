package com.codeyeji.IcanSell.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sysadmin")
public class Sysadmin { // 시스템관리자 테이블
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int sysadminId;
	
	@Column(nullable=false, length=100)
	private String sysaId;
	@Column(nullable=false, length=100)
	private String sysaPw;
	
	private String sysaName;

	
	public int getSysadminId() {
		return sysadminId;
	}

	public void setSysadminId(int sysadminId) {
		this.sysadminId = sysadminId;
	}

	public String getSysaId() {
		return sysaId;
	}

	public void setSysaId(String sysaId) {
		this.sysaId = sysaId;
	}

	public String getSysaPw() {
		return sysaPw;
	}

	public void setSysaPw(String sysaPw) {
		this.sysaPw = sysaPw;
	}

	public String getSysaName() {
		return sysaName;
	}

	public void setSysaName(String sysaName) {
		this.sysaName = sysaName;
	}



	
}
