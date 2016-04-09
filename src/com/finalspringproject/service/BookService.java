package com.finalspringproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.finalspringproject.dao.BookDao;
import com.finalspringproject.entity.Book;

@Service("bookService")
public class BookService {

	private BookDao bookDao;

	@Autowired
	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}

	public List<Book> getBooks() {
		return bookDao.getBooks();
	}

	public List<Book> getBook(String title){
		return bookDao.getBook(title);
	}

	public void saveOrUpdate(Book currentBook) {
		bookDao.saveOrUpdate(currentBook);
	}
}
