package com.finalspringproject.visitor;

import com.finalspringproject.entity.ShoppingCart;

public class NewUserCharge implements Visitor {

	@Override
	public double charge(ShoppingCart cart) {
		return (cart.getTotalCost() * .10) + cart.getTotalCost();
	}
}
