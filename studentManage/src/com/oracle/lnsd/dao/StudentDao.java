package com.oracle.lnsd.dao;

import java.util.List;

import com.oracle.lnsd.entity.Student;

public interface StudentDao {
	boolean isEntityExists(String name);
	void saveEntity(Student student);
	
	/**
	 * ȡ������ѧ��
	 */
	List<Student> listStudent();
}
