package com.finalspringproject.visitor;

import java.text.DecimalFormat;

public class NewUserCharge implements Visitor {

	DecimalFormat df = new DecimalFormat("#.##");
	
	public double charge(StockSmallCharge stockSmallCharge ){
		return 0;
		
	}

	public double charge(StockBigCharge stockBigCharge){
		return 0;
		
	}
}
