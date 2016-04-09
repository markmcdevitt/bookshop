package com.finalspringproject.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.finalspringproject.entity.Book;
import com.finalspringproject.strategy.Strategy;

@Transactional
@Repository
@Component("bookDao")
public class BookDao {


	@Autowired
	private SessionFactory sessionFactory;

	public Session session() {
		return sessionFactory.getCurrentSession();
	}
	
	@SuppressWarnings("unchecked")
	public List<Book> getBooks() {
		Criteria crit = session().createCriteria(Book.class);
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return crit.list();
	}
	@SuppressWarnings("unchecked")
	public List<Book>  getBook(String title) {
		Criteria crit = session().createCriteria(Book.class);
		crit.add(Restrictions.eq("title", title));
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return crit.list();
	}

	public void saveOrUpdate(Book currentBook) {
		session().saveOrUpdate(currentBook);
	}

	@SuppressWarnings("unchecked")
	public List<Book> find(String search) {
		Criteria criteria = session().createCriteria(Book.class, "book");
		criteria.add(Restrictions.like("book.title", search, MatchMode.ANYWHERE));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();	
	}
}
