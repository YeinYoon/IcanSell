package com.codeyeji.IcanSell.data;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.codeyeji.IcanSell.data.Drink;

@Entity
@Table(name="admin")
public class Admin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int adminId;
	
	@Column(nullable=false, length=100)
	private String a_id;
	@Column(nullable=false, length=100)
	private String a_pw;
	
	private String a_name;
	
	@OneToMany
	@JoinColumn(name="drinkId")
	private List<Drink> drinks; 
}
