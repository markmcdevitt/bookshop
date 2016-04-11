package com.finalspringproject.state;

public class LimitedStockLevel implements State {

	@Override
	public double changePrice(StateContext sc) {
		System.out.println("Stock is below 10");
		double price = 1.5;
		sc.changeState(new NormalStockLevel());
		return price; 	
	}
}
