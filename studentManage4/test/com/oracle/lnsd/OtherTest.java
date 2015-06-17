package com.oracle.lnsd;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.oracle.lnsd.dao.StudentDao;
import com.oracle.lnsd.utils.DbSource;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/application.xml")
public class OtherTest {
	@Autowired
	private ApplicationContext app;
	
	@Test
	public void test() {
		DbSource bean = app.getBean("dbSource", DbSource.class);
		assertNotNull(bean);
	}
	@Test
	public void test2() throws Exception {
		StudentDao studentDao = app.getBean("studentDao", StudentDao.class);
		int totalNum = studentDao.getTotalNum("zhang");
		System.out.println(totalNum);
	}

}
