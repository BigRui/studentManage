package com.oracle.lnsd.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.oracle.lnsd.dao.jdbcImpl.StudentDaoJdbcImpl2;
import com.oracle.lnsd.entity.Student;

public class StudentDaoTest {

	@Test
	public void test() {
		StudentDao studentDao = new StudentDaoJdbcImpl2();
		List<Student> studentList = studentDao.sharchByName(" ", 1, 2);
		System.out.println(studentList);
		assertEquals(2, studentList.size());
	}
	@Test
	public void test2() {
		StudentDao studentDao = new StudentDaoJdbcImpl2();
		int total = studentDao.getTotalNum("");
		assertEquals(10, total);
	}

}
