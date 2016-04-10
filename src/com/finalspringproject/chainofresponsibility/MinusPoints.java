package com.finalspringproject.chainofresponsibility;

import com.finalspringproject.entity.User;

public class MinusPoints implements Chain {

	private Chain nextInChain;

	@Override
	public void setNextChain(Chain nextChain) {
		nextInChain = nextChain;

	}

	@Override
	public void calculate(String request, User user) {
		if (request.equals("yes") && user.getLoyaltyPoints() > 10) {
			double discount = user.getLoyaltyPoints() * 0.10;
			double newPrice = user.getShoppingCart().getTotalCost() - discount;

			
			System.out.println("said yes,"+user.getLoyaltyPoints() + " means the discount is= "+ discount+ " and the new price is "+ newPrice);

			if (newPrice < 0) {
				double newLoyaltyPoints = newPrice * -10.00;
				System.out.println("The new points are "+newLoyaltyPoints+ " the price was "+ newPrice );
				int loyaltyPoints = (int) newLoyaltyPoints;
				user.setLoyaltyPoints(loyaltyPoints);
				user.getShoppingCart().setTotalCost(0);
				
			} else {
				System.out.println("new price is greater then 0: "+ newPrice);
				user.setLoyaltyPoints(0);
				user.getShoppingCart().setTotalCost(newPrice);
			}
		} else {
			nextInChain.calculate(request, user);
		}
	}
}
