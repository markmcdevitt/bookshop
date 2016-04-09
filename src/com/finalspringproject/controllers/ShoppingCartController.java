package com.finalspringproject.controllers;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.finalspringproject.entity.Book;
import com.finalspringproject.entity.Card;
import com.finalspringproject.entity.LineItem;
import com.finalspringproject.entity.PurchaseHistory;
import com.finalspringproject.entity.ShoppingCart;
import com.finalspringproject.entity.Stock;
import com.finalspringproject.entity.User;
import com.finalspringproject.service.BookService;
import com.finalspringproject.service.ShoppingCartService;
import com.finalspringproject.service.UsersService;

@Controller
public class ShoppingCartController {

	private ShoppingCartService shoppingCartService;
	private String timeStamp = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
	private BookService bookService;
	private UsersService userService;

	@RequestMapping(value = "/addtoshoppingcart/{title}")
	public String addtoshoppingcart(@PathVariable String title, Model model, Principal principal,
			@RequestParam(value = "quantity") String quan) {

		String username = principal.getName();
		User user = userService.getUser(username);// have the user
		List<Book> book = bookService.getBook(title);// have the book list and
														// book
		int stock = book.get(0).getStock().getStockLevel();

		int quantity = Integer.parseInt(quan);// have the quantity
		if (stock < quantity) {
			model.addAttribute("book", book);
			return "book";
		}

		LineItem lineItem = new LineItem(quantity, book.get(0));

		ShoppingCart cart;
		try {
			cart = user.getShoppingCart();
		} catch (Exception e) {
			cart = new ShoppingCart();
		}

		boolean newItem = true;
		List<LineItem> allLineItems;

		try {
			allLineItems = cart.getLineItem();
			for (LineItem l : allLineItems) {
				if (l.getBook().getTitle().equals(lineItem.getBook().getTitle())) {
					int q = l.getQuantity() + lineItem.getQuantity();
					l.setQuantity(q);
					newItem = false;
				}

			}
			if (newItem) {
				allLineItems.add(lineItem);
			}
		} catch (Exception e) {
			allLineItems = new ArrayList<LineItem>();
			allLineItems.add(lineItem);
		}

		double totalPrice = 0;
		for (LineItem l : allLineItems) {
			System.out.println("quantity: " + l.getQuantity() + " price: " + l.getBook().getPrice());
			totalPrice += l.getQuantity() * l.getBook().getPrice();
			System.out.println("total: " + totalPrice);
		}

		ShoppingCart shoppingCart = new ShoppingCart(totalPrice, allLineItems);
		user.setShoppingCart(shoppingCart);

		userService.saveOrUpdate(user);

		model.addAttribute("book", book);
		return "book";
	}

	@RequestMapping(value = "/shoppingcart")
	public String shoppingcart(Model model, Principal principal) {
		User user = userService.getUser(principal.getName());
		List<User> userList = new ArrayList<>();
		userList.add(user);
		model.addAttribute("user", userList);
		return "shoppingcart";

	}

	@RequestMapping(value = "/completeorder")
	public String completeorder(Model model, Principal principal,
			@RequestParam(value = "card-holder-name") String cardHolderName,
			@RequestParam(value = "address") String address, @RequestParam(value = "card-number") String cardNumber,
			@RequestParam(value = "cvv") String cvv, @RequestParam(value = "expiry-month") String expiryMonth,
			@RequestParam(value = "expiry-year") String expiryYear) {

		System.out.println("cardHolderName: " + cardHolderName + " cardNumber: " + cardNumber + " cvv: " + cvv
				+ " expiryMonth: " + expiryMonth + " expiryYear:" + expiryYear + " address: " + address);

		User user = userService.getUser(principal.getName());
		ShoppingCart shoppingCart = user.getShoppingCart();
		for (LineItem lineItem : shoppingCart.getLineItem()) {
			int quan = lineItem.getQuantity();
			Book book = lineItem.getBook();
			Stock stock = book.getStock();
			int newStockLevel = stock.getStockLevel() - quan;
			stock.setStockLevel(newStockLevel);
			book.setStock(stock);
			bookService.saveOrUpdate(book);
		}

		PurchaseHistory purchaseHistory = new PurchaseHistory(timeStamp, shoppingCart);

		List<PurchaseHistory> pList;
		try {
			pList = user.getPurchaseHistory();
		} catch (Exception e) {
			pList = new ArrayList<PurchaseHistory>();
		}
		pList.add(purchaseHistory);
		user.setPurchaseHistory(pList);

		ShoppingCart newShoppingCart = new ShoppingCart();
		user.setShoppingCart(newShoppingCart);
		userService.saveOrUpdate(user);

		user.setShoppingCart(shoppingCart);
		user.setShippingAddress(address);
		Card card = new Card(cardNumber, cvv, expiryMonth, expiryYear, cardHolderName);
		user.setCard(card);

		List<User> userList = new ArrayList<User>();
		userList.add(user);
		model.addAttribute("user", userList);
		return "receipt";

	}

	@RequestMapping(value = "/shippingaddress")
	public String shippingaddress(Model model, Principal principal) {
		User user = userService.getUser(principal.getName());
		List<User> userList = new ArrayList<>();
		userList.add(user);
		model.addAttribute("user", userList);
		System.out.println(userList.size());
		for (User u : userList) {
			System.out.println(u.toString());
		}
		return "shippingaddress";
	}

	@Autowired
	public void setShoppingCartService(ShoppingCartService shoppingCartService) {
		this.shoppingCartService = shoppingCartService;
	}

	@Autowired
	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

	@Autowired
	public void setUserService(UsersService userService) {
		this.userService = userService;
	}

}
