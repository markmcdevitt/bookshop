package com.finalspringproject.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.finalspringproject.entity.Book;
import com.finalspringproject.entity.User;

@Repository
@Transactional
@Component("usersDao")
public class UsersDao {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private SessionFactory sessionFactory;

	public Session session() {
		return sessionFactory.getCurrentSession();
	}

	@Transactional // causes a rollback if one of the sql querys fails
	public void create(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		session().save(user);
	}

	public boolean exists(String username) {
		Criteria crit = session().createCriteria(User.class);
		crit.add(Restrictions.eq("username", username));
		User user = (User) crit.uniqueResult();
		return user != null;

	}
	
	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {
		return session().createQuery("FROM User").list();
	}

	public User getUser(String username) {
		Criteria crit = session().createCriteria(User.class);
		crit.add(Restrictions.eq("username", username));
		return(User) crit.uniqueResult();
	}

	public void saveOrUpdate(User user) {
		session().saveOrUpdate(user);
	}
	
	@SuppressWarnings("unchecked")
	public List<User> find(String search) {
		Criteria criteria = session().createCriteria(User.class, "user");
		criteria.add(Restrictions.like("user.username", search, MatchMode.ANYWHERE));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();	
	}

	public List<User> getAllAdminUsers() {
		Criteria criteria = session().createCriteria(User.class, "user");
		criteria.add(Restrictions.like("user.authority", "ROLE_ADMIN", MatchMode.ANYWHERE));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();	
	}

	public List<User> getAllNormalUsers() {
		Criteria criteria = session().createCriteria(User.class, "user");
		criteria.add(Restrictions.like("user.authority", "ROLE_USER", MatchMode.ANYWHERE));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();	
	}
	



}
