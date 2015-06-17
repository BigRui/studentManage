package com.oracle.lnsd.dao;

import com.oracle.lnsd.entity.User;
import com.oracle.lnsd.utils.DbSource;

public interface UserDao {
	void setDbSource(DbSource dbSorce);
	User findUserByUserName(String userName);

}
