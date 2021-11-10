package com.codeyeji.IcanSell.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeyeji.IcanSell.data.Drink;
import com.codeyeji.IcanSell.repository.DrinkRepository;

@Service
public class ClientService {
	@Autowired
	DrinkRepository drinkRepository;
	
	public List<Drink> findDrinkAll() {
		return drinkRepository.findAll();
	}
	
}
