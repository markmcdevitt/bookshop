package com.finalspringproject.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.finalspringproject.visitor.Visitable;
import com.finalspringproject.visitor.Visitor;

@Entity
public class ShoppingCart implements Visitable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private double TotalCost;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<LineItem> lineItem;

	public ShoppingCart() {

	}

	public ShoppingCart(double totalCost, List<LineItem> lineItem) {
		super();
		TotalCost = totalCost;
		this.lineItem = lineItem;
	}

	public double getTotalCost() {
		return TotalCost;
	}

	public void setTotalCost(double totalCost) {
		TotalCost = totalCost;
	}

	public List<LineItem> getLineItem() {
		return lineItem;
	}

	public void setLineItem(List<LineItem> lineItem) {
		this.lineItem = lineItem;
	}

	@Override
	public String toString() {
		return "ShoppingCart [id=" + id + ", TotalCost=" + TotalCost + ", lineItem=" + lineItem + "]";
	}

	public double accept(Visitor visitor) {
		return visitor.charge(this);
	}
}
