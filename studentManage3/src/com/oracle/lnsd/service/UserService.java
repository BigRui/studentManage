package com.oracle.lnsd.service;

import com.oracle.lnsd.dao.UserDao;
import com.oracle.lnsd.entity.User;

public class UserService {
	private UserDao userDao;
	

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}


	public User findUserByUserName(String userName) {
	    return userDao.findUserByUserName(userName);
    }
}
