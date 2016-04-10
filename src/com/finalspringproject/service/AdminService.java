package com.finalspringproject.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.finalspringproject.dao.UsersDao;
import com.finalspringproject.entity.User;
import com.finalspringproject.iterator.BookShopIterator;

public class AdminService  implements BookShopIterator {

	private List<User> menuItems;
	private UsersDao usersDao;

	@Autowired
	public void setUsersDao(UsersDao usersDao) {
		this.usersDao = usersDao;
	}

	public AdminService() {
		menuItems = usersDao.getAllNormalUsers();
	}

	@Override
	public Iterator createIterator() {
		return menuItems.iterator();
	}
}
