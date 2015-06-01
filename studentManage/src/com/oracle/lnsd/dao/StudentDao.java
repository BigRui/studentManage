package com.oracle.lnsd.dao;

import java.util.List;

import com.oracle.lnsd.entity.Student;

public interface StudentDao {
	boolean isEntityExists(String name);
	void saveEntity(Student student);
	
	/**
	 * 取得所有学生
	 */
	List<Student> listStudent();
}
