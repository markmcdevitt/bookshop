package com.finalspringproject.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Stock {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private int StockLevel;

	public Stock() {
		super();
	}

	public Stock(int stockLevel) {
		super();
		StockLevel = stockLevel;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStockLevel() {
		return StockLevel;
	}

	public void setStockLevel(int stockLevel) {
		StockLevel = stockLevel;
	}

	@Override
	public String toString() {
		return String.valueOf(StockLevel);
	}

}
