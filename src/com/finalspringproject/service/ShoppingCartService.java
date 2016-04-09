package com.finalspringproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finalspringproject.dao.ShoppingCartDao;

@Service("shoppingCartService")
public class ShoppingCartService {

	private ShoppingCartDao shoppingCartDao;

	@Autowired
	public void setShoppingCartDao(ShoppingCartDao shoppingCartDao) {
		this.shoppingCartDao = shoppingCartDao;
	}
	
	
}
