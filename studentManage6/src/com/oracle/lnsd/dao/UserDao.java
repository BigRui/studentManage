package com.oracle.lnsd.dao;

import javax.sql.DataSource;

import com.oracle.lnsd.entity.User;

public interface UserDao {
	User findUserByUserName(String userName);

}
