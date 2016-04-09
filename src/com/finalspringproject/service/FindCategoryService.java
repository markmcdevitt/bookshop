package com.finalspringproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finalspringproject.dao.BookCategoryDao;
import com.finalspringproject.entity.Book;
import com.finalspringproject.strategy.Strategy;

@Service("findCategoryService")
public class FindCategoryService implements Strategy {

	private BookCategoryDao bookCategoryDao;

	@Autowired
	public void setBookCategoryDao(BookCategoryDao bookCategoryDao) {
		this.bookCategoryDao = bookCategoryDao;
	}

	public List<Book> searchTable(String word) {
		return bookCategoryDao.find(word);
	}
}
