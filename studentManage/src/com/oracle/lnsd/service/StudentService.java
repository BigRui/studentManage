package com.oracle.lnsd.service;

import java.util.List;

import com.oracle.lnsd.dao.StudentDao;
import com.oracle.lnsd.dao.jdbcImpl.StudentDaoJdbcImpl2;
import com.oracle.lnsd.entity.Student;
/**
 * ѧ�����ݴ����ҵ���߼�
 * @author Administrator
 *
 */
public class StudentService {
	private StudentDao studentDao = new StudentDaoJdbcImpl2(); 
	
	/**
	 * ���һ��ѧ����ҵ���߼�
	 * @param student
	 * @return
	 */
	public boolean addStudent(Student student){
		if(studentDao.isEntityExists(student.getName())) {
			return false;
		} else {
			this.studentDao.saveEntity(student);
			return true;
		}
	}
	public List<Student> studentList() {
		return this.studentDao.listStudent();
	}
}
