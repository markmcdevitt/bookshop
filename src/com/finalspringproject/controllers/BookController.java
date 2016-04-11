package com.finalspringproject.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.finalspringproject.entity.Book;
import com.finalspringproject.service.BookService;
import com.finalspringproject.service.UsersService;
import com.finalspringproject.state.NormalStockLevel;
import com.finalspringproject.state.StateContext;

@Controller
public class BookController {

	private BookService bookService;
	private UsersService userService;
	private boolean state = false;

	@Autowired
	public void setUserService(UsersService userService) {
		this.userService = userService;
	}

	@Autowired
	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

	@RequestMapping("/allbooks")
	public String showBooks(Model model) {
		List<Book> books = bookService.getBooks();
		model.addAttribute("books", books);

		return "allbooks";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/book/{title}")
	public String showSpecificRecipe(@PathVariable String title, Model model, Principal principal) {
		StateContext sc = new StateContext();

		List<Book> books2 = bookService.getBooks();
		List<Book> books = new ArrayList<Book>();

		for (Book b : books2) {
			if (b.getTitle().equals(title)) {
				books.add(b);
				if (b.getStock().getStockLevel() < 10&&!state) {
					double newPrice = sc.saySomething();
					b.setPrice(b.getPrice() * newPrice);
					System.out.println("in the if");
				} else if (b.getStock().getStockLevel() > 10) {
					sc.changeState(new NormalStockLevel());
					double newPrice = sc.saySomething();
					b.setPrice(b.getPrice() * newPrice);
					System.out.println("in the if2");
				}
			}
		}
		model.addAttribute("book", books);
		return "book";

	}

	@RequestMapping("/books/alphabetical")
	public String alphabeticalBook(Model model) {
		List<Book> books = bookService.getBooks();

		Collections.sort(books, new Comparator<Book>() {
			@Override
			public int compare(final Book object1, final Book object2) {
				return object1.getTitle().compareTo(object2.getTitle());
			}
		});
		model.addAttribute("books", books);
		return "allbooks";
	}

	@RequestMapping("/author/alphabetical")
	public String alphabeticalAuthor(Model model) {
		List<Book> books = bookService.getBooks();

		Collections.sort(books, new Comparator<Book>() {
			@Override
			public int compare(final Book object1, final Book object2) {
				return object1.getAuthor().compareTo(object2.getAuthor());
			}
		});
		model.addAttribute("books", books);
		return "allbooks";
	}

	@RequestMapping("/rating/alphabetical")
	public String alphabeticalRating(Model model) {
		List<Book> books = bookService.getBooks();

		Collections.sort(books, new Comparator<Book>() {
			@Override
			public int compare(final Book object1, final Book object2) {
				return Double.compare(Double.parseDouble(object2.getTotalRating()),
						Double.parseDouble(object1.getTotalRating()));
			}
		});
		model.addAttribute("books", books);
		return "allbooks";
	}
}
