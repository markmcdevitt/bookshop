package com.finalspringproject.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finalspringproject.dao.UsersDao;
import com.finalspringproject.entity.User;
import com.finalspringproject.iterator.BookShopIterator;

@Service("adminService")
public class AdminService implements BookShopIterator {

	private UsersDao usersDao;
	private List<User> menuItems = new ArrayList<User>();

	@Autowired
	public void setUsersDao(UsersDao usersDao) {
		this.usersDao = usersDao;
	}

	@Override
	public Iterator createIterator() {
		menuItems = usersDao.getAllAdminUsers();
		return menuItems.iterator();
	}

}
