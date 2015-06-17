package com.oracle.lnsd.service;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.oracle.lnsd.entity.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/application.xml")
public class UserServiceTest {
	@Autowired
	private ApplicationContext app;
	
	@Test
	public void test() {
		UserService userService = app.getBean("userService", UserService.class);
		assertNotNull(userService);
	}
	
	@Test
	public void test2() throws Exception {
		UserService userService = app.getBean("userService", UserService.class);
		User user = userService.findUserByUserName("zhangsan");
		assertEquals(user.getPassword(), "123");
	}
}
