package com.finalspringproject.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.NotBlank;

import com.finalspringproject.dao.FormValidationGroup;
import com.finalspringproject.dao.PersistenceValidationGroup;

@Entity
public class User {

	@Id
	@NotBlank(groups = { FormValidationGroup.class, PersistenceValidationGroup.class })
	@Size(min = 5, max = 20, groups = { FormValidationGroup.class, PersistenceValidationGroup.class })
	private String username;

	@NotBlank(groups = { FormValidationGroup.class, PersistenceValidationGroup.class })
	@Size(min = 3, max = 15, groups = { FormValidationGroup.class })
	private String password;

	private boolean enabled = false;
	private String authority;
	private int loyaltyPoints;
	private String shippingAddress;

	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<PurchaseHistory> purchaseHistory;

	@OneToOne(cascade = CascadeType.ALL)
	private ShoppingCart shoppingCart;

	@OneToOne(cascade = CascadeType.ALL)
	private Card card;

	public User() {

	}

	public User(String username, String password, boolean enabled, String authority, int loyaltyPoints,
			String shippingAddress) {
		super();
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.authority = authority;
		this.loyaltyPoints = loyaltyPoints;
		this.shippingAddress = shippingAddress;
	}

	public User(String username, String password, boolean enabled, String authority, int loyaltyPoints,
			String shippingAddress, List<PurchaseHistory> purchaseHistory, ShoppingCart shoppingCart, Card card) {
		super();
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.authority = authority;
		this.loyaltyPoints = loyaltyPoints;
		this.shippingAddress = shippingAddress;
		this.purchaseHistory = purchaseHistory;
		this.shoppingCart = shoppingCart;
		this.card = card;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public List<PurchaseHistory> getPurchaseHistory() {
		return purchaseHistory;
	}

	public void setPurchaseHistory(List<PurchaseHistory> purchaseHistory) {
		this.purchaseHistory = purchaseHistory;
	}

	public int getLoyaltyPoints() {
		return loyaltyPoints;
	}

	public void setLoyaltyPoints(int loyaltyPoints) {
		this.loyaltyPoints = loyaltyPoints;
	}

	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", shippingAddress=" + shippingAddress + ", purchaseHistory="
				+ purchaseHistory + ", shoppingCart=" + shoppingCart + ", card=" + card + "]";
	}
	

}
