package com.finalspringproject.state;

public class StateContext {

	private State currentState;
	
	public StateContext(){
		currentState = new LimitedStockLevel();
	}

	public void changeState(State newState) {
		this.currentState = newState;
	}

	public double saySomething() {
		return this.currentState.changePrice(this);
	}
}
