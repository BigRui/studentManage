package com.oracle.lnsd.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.oracle.lnsd.dao.UserDao;
import com.oracle.lnsd.entity.User;
@Service
public class UserService {
	@Resource
	private UserDao userDao;
	

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}


	public User findUserByUserName(String userName) {
	    return userDao.findUserByUserName(userName);
    }
}