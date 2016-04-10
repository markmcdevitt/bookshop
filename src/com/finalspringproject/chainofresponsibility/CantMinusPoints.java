package com.finalspringproject.chainofresponsibility;

import com.finalspringproject.entity.User;

public class CantMinusPoints implements Chain {

	private Chain nextInChain;
	
	@Override
	public void setNextChain(Chain nextChain) {
		// TODO Auto-generated method stub
		nextInChain = nextChain;
		
	}

	@Override
	public void calculate(String request, User user) {
		if(request.equals("yes")&&user.getLoyaltyPoints()<10){
			System.out.println("Not enough points");
		}else{
			System.out.println("through the second chain");
			nextInChain.calculate(request, user);
		}
		
	}

}
