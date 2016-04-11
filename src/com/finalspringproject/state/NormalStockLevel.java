package com.finalspringproject.state;

public class NormalStockLevel implements State {

	
	@Override
	public double changePrice(StateContext sc) {
		System.out.println("Normal Stock");
		sc.changeState(new LimitedStockLevel());
		return 1.0;
	}

}
