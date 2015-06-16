package com.oracle.lnsd.dao;

import com.oracle.lnsd.entity.User;

public interface UserDao {

	User findUserByUserName(String userName);
	
}
