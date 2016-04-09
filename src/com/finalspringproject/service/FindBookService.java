package com.finalspringproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finalspringproject.dao.BookDao;
import com.finalspringproject.entity.Book;
import com.finalspringproject.strategy.Strategy;

@Service("findBookService")
public class FindBookService implements Strategy {

	private BookDao bookDao;

	@Autowired
	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}

	@Override
	public List<Book> searchTable(String word) {
		return bookDao.find(word);
	}

}
