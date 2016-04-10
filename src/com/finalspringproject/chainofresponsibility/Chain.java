package com.finalspringproject.chainofresponsibility;

import com.finalspringproject.entity.User;

public interface Chain {

	public void setNextChain(Chain nextChain);
	public void calculate(String request,User user);
}
