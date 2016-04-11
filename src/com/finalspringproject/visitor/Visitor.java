package com.finalspringproject.visitor;

import com.finalspringproject.entity.ShoppingCart;

public interface Visitor {

	public double charge(ShoppingCart cart);
}
