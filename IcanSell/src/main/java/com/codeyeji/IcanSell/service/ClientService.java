package com.codeyeji.IcanSell.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeyeji.IcanSell.data.Drink;
import com.codeyeji.IcanSell.data.Sales;
import com.codeyeji.IcanSell.repository.DrinkRepository;
import com.codeyeji.IcanSell.repository.SalesRepository;

@Service
public class ClientService {
	@Autowired
	DrinkRepository drinkRepository;
	@Autowired
	SalesRepository salesRepository;
	
	public List<Drink> findDrinkAll() {return drinkRepository.findAll();}
	
	public void addSales(Sales sales) {salesRepository.save(sales);}
	
	
}
