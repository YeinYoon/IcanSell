package com.codeyeji.IcanSell.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sysadmin")
public class Sysadmin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int sysadminId;
	
	@Column(nullable=false, length=100)
	private String sysa_id;
	@Column(nullable=false, length=100)
	private String sysa_pw;
	
	private String sysa_name;
}
