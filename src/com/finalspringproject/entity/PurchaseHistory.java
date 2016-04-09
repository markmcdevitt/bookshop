package com.finalspringproject.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class PurchaseHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String date;
	@OneToOne
	private ShoppingCart shoppingCart;

	public PurchaseHistory() {

	}

	public PurchaseHistory(String date, ShoppingCart shoppingCart) {
		this.date = date;
		this.shoppingCart = shoppingCart;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

}
