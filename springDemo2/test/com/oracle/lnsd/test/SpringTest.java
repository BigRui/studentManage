package com.oracle.lnsd.test;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.oracle.lnsd.beans.Bean1;
import com.oracle.lnsd.beans.Bean2;
import com.oracle.lnsd.beans.Bean3;
import com.oracle.lnsd.beans.BeansCollection;
import com.oracle.lnsd.service.StudentService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application.xml")
public class SpringTest {
	@Autowired
	ApplicationContext app;

	@Test
	public void test() {
		StudentService studentService = app.getBean("studentService", StudentService.class);
		studentService.service();
	}
	
	@Test
	public void test2() throws Exception {
		Bean1 bean1 = app.getBean("bean1", Bean1.class);
		System.out.println(bean1);
	}
	@Test
	/**
	 * 静态工厂方法实例化
	 * @throws Exception
	 */
	public void test3() throws Exception {
		Bean2 bean2 = app.getBean("bean2", Bean2.class);
		System.out.println(bean2);
	}
	@Test
	/**
	 * 实例工厂方法实例化
	 * @throws Exception
	 */
	public void test4() throws Exception {
		Bean3 bean3 = app.getBean("bean3", Bean3.class);
		System.out.println(bean3);
	}
	@Test
	public void test5(){
		BeansCollection bc = app.getBean("beanColl", BeansCollection.class);
		assertEquals(bc.getName(), "zhangsan");
		assertEquals(bc.getAge(), 123);
		System.out.println(bc.getBeanList().get(3).toString());
		assertEquals(bc.getBeanArray().length, 4);
		assertEquals(bc.getBeanSet().size(), 3);
		Bean1 bean1 = app.getBean(Bean1.class);
		assertEquals(bc.getBeanMap().get("第一个bean"), bean1);
		assertEquals(bc.getBeanMap().size(), 2);
	}
}
