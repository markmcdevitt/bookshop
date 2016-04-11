package com.finalspringproject.visitor;

public class StockBigCharge implements Visitable{

	private double charge;

	public StockBigCharge(double charge) {
		this.charge = charge;
	}



	@Override
	public double accept(Visitor visitor) {
		// TODO Auto-generated method stub
		return visitor.charge(this);
	}


	public double getCharge() {
		return charge;
	}
	
}
