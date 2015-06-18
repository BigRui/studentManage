package com.oracle.lnsd.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.oracle.lnsd.entity.Student;
import com.oracle.lnsd.utils.Page;
public class StudentServiceTest {

	@Test
	public void test() {
		StudentService ss = new StudentService();
		Page<Student> page = ss.sharchByName(" ", 2, 5);
		assertEquals(5, page.getList().size());
		System.out.println(page.getList());
	}
	@Test
	public void test2() throws Exception {
		StudentService ss = new StudentService();
		System.out.println(ss.getStudentDao().getClass());
		assertNotNull(ss.getStudentDao());
	}
	
	@Test
	public void test3(){
		//对象工厂
		ApplicationContext app =  new ClassPathXmlApplicationContext("classpath:application.xml");
//		StudentService studentService = (StudentService) app.getBean("studentService");
		StudentService studentService = app.getBean("studentService", StudentService.class);
		Student student = studentService.getById(161);
		assertEquals(student.getAge(), 123);
	}
	@Test
	public void test4(){
		//对象工厂
		ApplicationContext app =  new ClassPathXmlApplicationContext("classpath:application.xml");
		StudentService studentService = app.getBean("studentService", StudentService.class);
		StudentService studentService2 = app.getBean("studentService", StudentService.class);
		assertTrue(studentService == studentService2);
	}
}
