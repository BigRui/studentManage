package com.oracle.lnsd.dao;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.oracle.lnsd.entity.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/application.xml")
public class UserDaoTest {

	@Autowired
	private ApplicationContext app;
	
	@Test
	public void test() {
		UserDao userDao = app.getBean("userDao", UserDao.class);
		User zhangsan = userDao.findUserByUserName("zhangsan");
		assertEquals(zhangsan.getPassword(), "123");
	}

}
