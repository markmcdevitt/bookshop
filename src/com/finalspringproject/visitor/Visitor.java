package com.finalspringproject.visitor;

import com.finalspringproject.entity.Book;

public interface Visitor {

	public double charge(StockSmallCharge stockSmallCharge );

	public double charge(StockBigCharge stockBigCharge);
}
