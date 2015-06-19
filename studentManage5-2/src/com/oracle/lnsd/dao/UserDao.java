package com.oracle.lnsd.dao;

import javax.sql.DataSource;

import com.oracle.lnsd.entity.User;

public interface UserDao {
	void setDbSource(DataSource dataSource);
	User findUserByUserName(String userName);

}
