package com.finalspringproject.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.finalspringproject.dao.UsersDao;
import com.finalspringproject.entity.User;
import com.finalspringproject.iterator.BookShopIterator;

@Service("usersService")
public class UsersService {

	private List<User> menuItems = new ArrayList<User>();
	private UsersDao usersDao;

	@Autowired
	public void setUsersDao(UsersDao usersDao) {
		this.usersDao = usersDao;
	}

	public void create(User user) {
		usersDao.create(user);
	}

	public void saveOrUpdate(User user) {
		usersDao.saveOrUpdate(user);
	}

	public boolean exists(String username) {
		return usersDao.exists(username);
	}

	public User getUser(String username) {
		return usersDao.getUser(username);
	}

	public List<User> getAllUsers() {
		return usersDao.getAllUsers();
	}
}
