package com.finalspringproject.strategy;

import java.util.List;

import com.finalspringproject.entity.Book;

public class Situation {
	
	private Strategy strategy;
	 
	public Situation(Strategy strategy){
		this.strategy = strategy;
	}

	public List<Book> findBy(String word){
		return this.strategy.searchTable(word);
	}

}