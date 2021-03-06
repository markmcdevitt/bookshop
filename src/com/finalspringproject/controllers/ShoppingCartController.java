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
import org.springframework.web.bind.annotation.RequestParam;

import com.finalspringproject.chainofresponsibility.CantMinusPoints;
import com.finalspringproject.chainofresponsibility.Chain;
import com.finalspringproject.chainofresponsibility.DontMinusPoints;
import com.finalspringproject.chainofresponsibility.MinusPoints;
import com.finalspringproject.entity.Book;
import com.finalspringproject.entity.Card;
import com.finalspringproject.entity.LineItem;
import com.finalspringproject.entity.PurchaseHistory;
import com.finalspringproject.entity.ShoppingCart;
import com.finalspringproject.entity.Stock;
import com.finalspringproject.entity.User;
import com.finalspringproject.memento.Address;
import com.finalspringproject.service.BookService;
import com.finalspringproject.service.UsersService;
import com.finalspringproject.state.NormalStockLevel;
import com.finalspringproject.state.StateContext;
import com.finalspringproject.visitor.AverageUser;
import com.finalspringproject.visitor.FrequentUser;
import com.finalspringproject.visitor.NewUserCharge;

@Controller
public class ShoppingCartController {

	private String timeStamp = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
	private BookService bookService;
	private UsersService userService;
	private Address address = new Address();
	private List<Address.Memento> savedAddress = new ArrayList<Address.Memento>();
	private StateContext sc = new StateContext();

	@RequestMapping(value = "/addtoshoppingcart/{title}")
	public String addtoshoppingcart(@PathVariable String title, Model model, Principal principal,
			@RequestParam(value = "quantity") String quan) {

		String username = principal.getName();
		User user = userService.getUser(username);// have the user
		List<Book> book = bookService.getBook(title);
		

		book.get(0).setPrice(updatedPrice(book.get(0),title));

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
			totalPrice += l.getQuantity() * l.getBook().getPrice();
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

		try {
			double newCost = newTotalCost(user);
			user.getShoppingCart().setTotalCost(newCost);
		} catch (Exception e) {
			
		}
		
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
			@RequestParam(value = "expiry-year") String expiryYear, @RequestParam(value = "discount") String request) {
		savedAddress.clear();

		User user = userService.getUser(principal.getName());
		ShoppingCart shoppingCart = user.getShoppingCart();

		Address addressClass = new Address();

		addressClass.setAddress(user.getShippingAddress());
		savedAddress.add(addressClass.saveToMemento());

		addressClass.setAddress(address);
		savedAddress.add(addressClass.saveToMemento());

		Chain chain1 = new MinusPoints();
		Chain chain2 = new CantMinusPoints();
		Chain chain3 = new DontMinusPoints();

		chain1.setNextChain(chain2);
		chain2.setNextChain(chain3);

		chain1.calculate(request, user);

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
		model.addAttribute("savedAddress", savedAddress);
		model.addAttribute("user", userList);
		return "receipt";

	}

	@RequestMapping(value = "/newaddress")
	public String newAddress(Model model, Principal principal) {

		User user = userService.getUser(principal.getName());
		user.setShippingAddress(address.restoreFromMemento(savedAddress.get(1)));
		userService.saveOrUpdate(user);
		return "home";

	}

	@RequestMapping(value = "/restoreaddress")
	public String restoreAddress(Model model, Principal principal) {
		User user = userService.getUser(principal.getName());
		user.setShippingAddress(address.restoreFromMemento(savedAddress.get(0)));
		userService.saveOrUpdate(user);
		return "home";
	}

	@RequestMapping(value = "/shippingaddress")
	public String shippingaddress(Model model, Principal principal) {
		User user = userService.getUser(principal.getName());
		double newCost = newTotalCost(user);
		user.getShoppingCart().setTotalCost(newCost);
		userService.saveOrUpdate(user);
		List<User> userList = new ArrayList<>();
		userList.add(user);
		model.addAttribute("user", userList);
		return "shippingaddress";
	}

	@Autowired
	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

	@Autowired
	public void setUserService(UsersService userService) {
		this.userService = userService;
	}

	public double newTotalCost(User user) {

		double newTotalCost;
		ShoppingCart cart = user.getShoppingCart();
		int phSize = user.getPurchaseHistory().size();
		if (phSize == 0) {
			NewUserCharge newUserCharge = new NewUserCharge();
			newTotalCost = cart.accept(newUserCharge);
		} else if (phSize <= 3) {
			AverageUser averageUser = new AverageUser();
			newTotalCost = cart.accept(averageUser);
		} else {
			FrequentUser frequentUser = new FrequentUser();
			newTotalCost = cart.accept(frequentUser);
		}
		return newTotalCost;
	}

	public double updatedPrice(Book book, String title){
		if (book.getTitle().equals(title)) {
			if (book.getStock().getStockLevel() < 10) {
				double newPrice = sc.saySomething();
				book.setPrice(book.getPrice() * newPrice);
			} else if (book.getStock().getStockLevel() > 10) {
				sc.changeState(new NormalStockLevel());
				double newPrice = sc.saySomething();
				book.setPrice(book.getPrice() * newPrice);
			}
		}
		return book.getPrice();
	}
}
