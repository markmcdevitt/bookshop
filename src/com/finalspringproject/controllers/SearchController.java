package com.finalspringproject.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.finalspringproject.entity.Book;
import com.finalspringproject.service.BookService;
import com.finalspringproject.service.FindAuthorService;
import com.finalspringproject.service.FindBookService;
import com.finalspringproject.service.FindCategoryService;
import com.finalspringproject.service.UsersService;
import com.finalspringproject.strategy.Situation;

@Controller
public class SearchController {

	private BookService bookService;
	private FindBookService findBookService;
	private FindAuthorService findAuthorService;
	private FindCategoryService findCategoryService;
	private UsersService userService;
	
	
	@Autowired
	public void setFindCategoryService(FindCategoryService findCategoryService) {
		this.findCategoryService = findCategoryService;
	}

	@Autowired
	public void setFindAuthorService(FindAuthorService findAuthorService) {
		this.findAuthorService = findAuthorService;
	}

	@Autowired
	public void setFindBookService(FindBookService findBookService) {
		this.findBookService = findBookService;
	}

	@Autowired
	public void setUserService(UsersService userService) {
		this.userService = userService;
	}

	@Autowired
	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

	@RequestMapping(value = "/search")
	public String search(Model model, @RequestParam(value = "search") String search,@RequestParam(value = "type") String category) {

	   List<Book> books = new ArrayList<>();
		
		if (category.equals("title")) {
			Situation s1 = new Situation(findBookService);
			books=s1.findBy(search);
		} else if(category.equals("author")) {
			Situation s1 = new Situation(findAuthorService);
			books=s1.findBy(search);
		}else{
			Situation s1 = new Situation(findCategoryService);
			books=s1.findBy(search);	
		}

		model.addAttribute("books", books);
		return "search";
	}
}
