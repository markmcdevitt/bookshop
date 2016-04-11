package com.finalspringproject.visitor;

public class StockSmallCharge implements Visitable {
	
	private double charge;

	public StockSmallCharge(double charge) {
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
