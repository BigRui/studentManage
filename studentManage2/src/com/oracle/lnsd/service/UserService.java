package com.oracle.lnsd.service;

import com.oracle.lnsd.dao.UserDao;
import com.oracle.lnsd.dao.jdbcImpl.UserDaoH2Impl;
import com.oracle.lnsd.entity.User;

public class UserService {
	private UserDao userDao = new UserDaoH2Impl();

	public User findUserByUserName(String userName) {
		return userDao.findUserByUserName(userName);
	}
}
