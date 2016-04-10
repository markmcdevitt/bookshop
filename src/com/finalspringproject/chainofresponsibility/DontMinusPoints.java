package com.finalspringproject.chainofresponsibility;

import com.finalspringproject.entity.User;

public class DontMinusPoints implements Chain {

	private Chain nextInChain;

	@Override
	public void setNextChain(Chain nextChain) {
		// TODO Auto-generated method stub
		nextInChain = nextChain;
	}

	@Override
	public void calculate(String request, User user) {
		// TODO Auto-generated method stub
		if(request.equals("no")){
			double newPoints = user.getShoppingCart().getTotalCost()+user.getLoyaltyPoints();
			user.setLoyaltyPoints((int) newPoints);
			System.out.println("You said no, points="+newPoints);
		}

	}

}
