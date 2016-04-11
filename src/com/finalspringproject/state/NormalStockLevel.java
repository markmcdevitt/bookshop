package com.finalspringproject.state;

import com.finalspringproject.entity.Book;

public class NormalStockLevel implements State {

	
	@Override
	public double changePrice(StateContext sc) {
		System.out.println("Normal Stock");
		sc.changeState(new LimitedStockLevel());
		return 0.0;
	}

}
