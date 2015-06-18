package com.oracle.lnsd.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.oracle.lnsd.service.aspectjProxy.MyInterface;
import com.oracle.lnsd.service.dynamicProxy.CglibServiceProxy;
import com.oracle.lnsd.service.dynamicProxy.DynamicProxyService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/application.xml")
public class StudentServiceTest {
	@Autowired
	private ApplicationContext app;
	@Test
	public void test() {
		StudentServiceInterface studentService = new StudentService();
		StudentServiceInterface proxyStudentService =  new StudentServiceProxy(studentService);
		proxyStudentService.saveOrUpdate();
		proxyStudentService.shearchByName();
	}

	@Test
	/**
	 * 动态代理的方式处理事务
	 * @throws Exception
	 */
	public void test2() throws Exception {
		StudentServiceInterface studentService = new StudentService();
		StudentServiceInterface dproxy = DynamicProxyService.bind(studentService);
		dproxy.saveOrUpdate();
		
		dproxy.shearchByName();
		
		UserService userService = new UserService();
		UserServiceInterface userServiceProxy = DynamicProxyService .bind(userService);
		userServiceProxy.delete();
	}
	@Test
	/**
	 * cglib的方式处理事务
	 * @throws Exception
	 */
	public void test3() throws Exception {
		TeacherService ts = new TeacherService();
		TeacherService teachers = CglibServiceProxy.bind(ts);
		teachers.delete();
		teachers.shearchByName();
	}
	@Test
	public void test4() throws Exception {
		StudentService studentService = app.getBean("studentServiceProxy", StudentService.class);
		studentService.saveOrUpdate();
		studentService.shearchByName();
	}
	@Test
	public void test5() throws Exception {
		StudentService studentService = app.getBean("studentService", StudentService.class);
		studentService.saveOrUpdate();
		//看看引介增强成功否
		MyInterface my = (MyInterface) studentService;
		my.showMyInterface();
	}
	@Test
	public void test6() throws Exception {
		UserService userService = app.getBean("userService", UserService.class);
		userService.delete();
		System.out.println("==================================");
		userService.saveOrUpdate();
	}
}
