package com.finalspringproject.visitor;

import com.finalspringproject.entity.ShoppingCart;

public class AverageUser implements Visitor {

	@Override
	public double charge(ShoppingCart cart) {
		return (cart.getTotalCost() * .08) + cart.getTotalCost();
	}

}
