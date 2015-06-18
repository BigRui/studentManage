package com.oracle.lnsd.dao;

import java.util.List;

import com.oracle.lnsd.entity.Student;
import com.oracle.lnsd.utils.TransactionalAspect;

public interface StudentDao {
	void setDbSource(TransactionalAspect dbSource);
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
