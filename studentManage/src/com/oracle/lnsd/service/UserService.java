package com.oracle.lnsd.service;

import com.oracle.lnsd.dao.UserDao;
import com.oracle.lnsd.dao.jdbcImpl.UserDaoJdbcImpl;
import com.oracle.lnsd.entity.User;

public class UserService {
	private UserDao userDao = new UserDaoJdbcImpl();

	public User findUserByUserName(String userName) {
		return userDao.findUserByUserName(userName);
	}
}
