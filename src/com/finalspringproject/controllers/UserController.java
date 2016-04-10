package com.finalspringproject.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import com.finalspringproject.dao.FormValidationGroup;
import com.finalspringproject.entity.User;
import com.finalspringproject.iterator.BookShopIterator;
import com.finalspringproject.service.AdminService;
import com.finalspringproject.service.UsersService;

@Controller
public class UserController  {

	private UsersService usersService;	
	private BookShopIterator userService;
	private BookShopIterator adminService;
	
	public UserController(BookShopIterator userService, BookShopIterator adminService) {	
		this.userService = userService;
		adminService = adminService;
	}
	
	public void allUsers(){
		Iterator allAdminUsers = adminService.createIterator();
		Iterator allNormalUsers = userService.createIterator();
		
		List<User> allUsers = new ArrayList<User>();
		
		allUsers.addAll(getAllUsers(allNormalUsers));
		
	}
	public List<User> getAllUsers(Iterator iterator){
		List<User> allUsers = new ArrayList<User>();
		while (iterator.hasNext()) {
			User i = (User) iterator.next();
			
			
			allUsers.add(i);
			
			System.out.println(i.getUsername());
			System.out.println(i.getAuthority());
			System.out.println(i.getPurchaseHistory());
			System.out.println(i.getShippingAddress()+ "\n");
			
			
		}
		return allUsers;
	}

	@Autowired
	public void setUsersService(UsersService usersService) {
		this.usersService = usersService;
	}

	@RequestMapping("/register")
	public String register(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}

	@RequestMapping(value = "/doRegister")
	public String doRegister(Model model, @Validated(FormValidationGroup.class) User user, BindingResult result) {

		if (result.hasErrors()) {
			return "register";
		} else {
			System.out.println("form is correct");
		}
		user.setAuthority("ROLE_USER");
		user.setEnabled(true);

		try {
			usersService.create(user);
		} catch (DuplicateKeyException e) {
			result.rejectValue("username", "DuplicateKey.user.username");
			return "newaccount";
		}

		return "loggedin";
	}



}
