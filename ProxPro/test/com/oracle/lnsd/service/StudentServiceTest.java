package com.oracle.lnsd.service;

import static org.junit.Assert.*;

import org.junit.Test;

import com.oracle.lnsd.service.dynamicProxy.CglibServiceProxy;
import com.oracle.lnsd.service.dynamicProxy.DynamicProxyService;

public class StudentServiceTest {

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
}
