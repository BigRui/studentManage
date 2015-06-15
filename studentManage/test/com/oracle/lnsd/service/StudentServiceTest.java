package com.oracle.lnsd.service;

import org.junit.Test;
import static org.junit.Assert.*;
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

}
