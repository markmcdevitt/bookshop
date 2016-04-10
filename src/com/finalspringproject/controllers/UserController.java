package com.finalspringproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import com.finalspringproject.dao.FormValidationGroup;
import com.finalspringproject.entity.User;
import com.finalspringproject.service.UsersService;

@Controller
public class UserController  {

	private UsersService usersService;	

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
