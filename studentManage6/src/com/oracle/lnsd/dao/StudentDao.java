package com.oracle.lnsd.dao;

import java.util.List;

import javax.sql.DataSource;

import com.oracle.lnsd.entity.Student;

public interface StudentDao {
	boolean isEntityExists(String name);
	void saveOrUpdateEntity(Student student);
	
	/**
	 * 取得所有学生
	 */
	List<Student> sharchByName(String studentName, int offset, int numPerPage);
	void deleteById(Integer id);
	int getTotalNum(String studentName);
	Student getById(Integer id);
}
