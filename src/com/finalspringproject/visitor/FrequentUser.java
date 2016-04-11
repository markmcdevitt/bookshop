package com.finalspringproject.visitor;

import com.finalspringproject.entity.ShoppingCart;

public class FrequentUser implements Visitor{

	@Override
	public double charge(ShoppingCart cart) {
		return (cart.getTotalCost()*.02)+cart.getTotalCost();
	}

}
