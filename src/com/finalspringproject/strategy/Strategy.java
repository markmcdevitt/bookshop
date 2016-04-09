package com.finalspringproject.strategy;

import java.util.List;

import com.finalspringproject.entity.Book;

public interface Strategy {

	public List<Book> searchTable(String word);
}
