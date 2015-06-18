package com.oracle.lnsd.dao;

import com.oracle.lnsd.entity.User;
import com.oracle.lnsd.utils.TransactionalAspect;

public interface UserDao {
	void setDbSource(TransactionalAspect dbSorce);
	User findUserByUserName(String userName);

}
