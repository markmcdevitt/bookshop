package com.finalspringproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finalspringproject.dao.BookAuthorDao;
import com.finalspringproject.entity.Book;
import com.finalspringproject.strategy.Strategy;

@Service("findAuthorService")
public class FindAuthorService implements Strategy {

	private BookAuthorDao bookAuthorDao;

	@Autowired
	public void setBookAuthorDao(BookAuthorDao bookAuthorDao) {
		this.bookAuthorDao = bookAuthorDao;
	}

	public List<Book> searchTable(String word) {
		return bookAuthorDao.find(word);
	}
}
