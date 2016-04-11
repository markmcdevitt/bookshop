package com.finalspringproject.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.finalspringproject.entity.Book;
import com.finalspringproject.entity.PurchaseHistory;
import com.finalspringproject.entity.User;
import com.finalspringproject.service.AdminService;
import com.finalspringproject.service.BookService;
import com.finalspringproject.service.EnabledUserService;
import com.finalspringproject.service.UsersService;

@Controller
public class AdminController {

	private UsersService usersService;
	private BookService bookService;
	private AdminService adminService;
	private EnabledUserService enabledUserService;
	private List<User> allAdminUsers = new ArrayList<User>();

	@Autowired
	public void setEnabledUserService(EnabledUserService enabledUserService) {
		this.enabledUserService = enabledUserService;
	}

	@Autowired
	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}

	@Autowired
	public void setUsersService(UsersService usersService) {
		this.usersService = usersService;
	}

	@Autowired
	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

	@RequestMapping("/allenabledusers")
	public String allAdminUsers(Model model) {

		allAdminUsers.clear();
		Iterator itr = adminService.createIterator();
		Iterator itr2 = enabledUserService.createIterator();
		
		iterateList(itr);
		iterateList(itr2);
		
		model.addAttribute("users", allAdminUsers);
		return "admin";
	}

	@RequestMapping("/admin")
	public String showAdmin(Model model) {
		List<User> users = usersService.getAllUsers();
		model.addAttribute("users", users);
		return "admin";
	}

	@RequestMapping("/admin/alphabetical")
	public String alphabetical(Model model) {
		List<User> users = usersService.getAllUsers();

		Collections.sort(users, new Comparator<User>() {
			@Override
			public int compare(final User object1, final User object2) {
				return object1.getUsername().compareTo(object2.getUsername());
			}
		});
		model.addAttribute("users", users);
		return "admin";
	}

	@RequestMapping("/user/{username}")
	public String user(Model model, @PathVariable String username) {
		User user = usersService.getUser(username);
		List<User> userList = new ArrayList<User>();
		List<PurchaseHistory> purchaseHistories = user.getPurchaseHistory();

		model.addAttribute("user", user);
		model.addAttribute("userList", userList);
		model.addAttribute("purchaseHistories", purchaseHistories);
		return "userpage";
	}

	@RequestMapping("/editStock/{title}")
	public String editStock(Model model, @PathVariable String title, @RequestParam(value = "quantity") String quan) {
		List<Book> book = bookService.getBook(title);
		book.get(0).getStock().setStockLevel(Integer.parseInt(quan));
		bookService.saveOrUpdate(book.get(0));
		System.out.println("??");
		List<Book> books = bookService.getBooks();
		model.addAttribute("books", books);
		return "allbooks";

	}

	public List<User> iterateList(Iterator itr) {
		while (itr.hasNext()) {
			User i = (User) itr.next();

			System.out.println(i.getUsername());
			if (i.isEnabled()) {
				allAdminUsers.add(i);
			}
		}
		return allAdminUsers;
	}
}
