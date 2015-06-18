package com.oracle.lnsd.dao;

import javax.sql.DataSource;

import com.oracle.lnsd.entity.User;

public interface UserDao {
	void setDbSource(DataSource dbSorce);
	User findUserByUserName(String userName);

}
