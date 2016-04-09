package com.finalspringproject.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String title;
	private String author;
	private String category;
	private double price;
	private String image;
	private String totalRating;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Review> review;

	@OneToOne(cascade = CascadeType.ALL)
	private Stock Stock;

	public Book() {

	}

	public Book(String title, String author, String category, double price, String image, String totalRating,
			List<Review> review, com.finalspringproject.entity.Stock stock) {
		super();
		this.title = title;
		this.author = author;
		this.category = category;
		this.price = price;
		this.image = image;
		this.totalRating = totalRating;
		this.review = review;
		Stock = stock;
	}

	public String getTotalRating() {
		return totalRating;
	}

	public void setTotalRating(String totalRating) {
		this.totalRating = totalRating;
	}

	public List<Review> getReview() {
		return review;
	}

	public void setReview(List<Review> review) {
		this.review = review;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Stock getStock() {
		return Stock;
	}

	public void setStock(Stock stock) {
		Stock = stock;
	}

	@Override
	public String toString() {
		return "Book [title=" + title + ", author=" + author + ", category=" + category + ", price=" + price
				+ ", image=" + image + ", totalRating=" + totalRating + ", review=" + review + ", Stock=" + Stock + "]";
	}

}
