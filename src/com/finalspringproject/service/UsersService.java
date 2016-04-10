package com.finalspringproject.service;

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
public class UsersService implements BookShopIterator {

	private List<User> menuItems;
	private UsersDao usersDao;
	
	public UsersService() {
		menuItems = usersDao.getAllNormalUsers();
	}

	@Autowired
	public void setRecipeDao(UsersDao usersDao) {
		this.usersDao = usersDao;
	}

	public void create(User user) {
		usersDao.create(user);
	}
	public void saveOrUpdate(User user){
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
	public List<User> getAllAdminUsers() {
		return usersDao.getAllAdminUsers();
	}

	@Override
	public Iterator createIterator() {
		return menuItems.iterator();
	}
}
