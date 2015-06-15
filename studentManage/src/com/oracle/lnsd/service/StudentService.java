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
	public void addStudent(Student student){
		if(studentDao.isEntityExists(student.getName())) {
			throw new ServiceException("�û����Ѿ�������");
		} else {
			this.studentDao.saveEntity(student);
		}
	}
	public List<Student> studentList() {
		return this.studentDao.listStudent();
	}
	public List<Student> sharchByName(String studentName) {
		List<Student> result = null;
		result = this.studentDao.sharchByName(studentName);
		return result;
	}
	/**
	 * ����idɾ��һ��student��¼
	 * @param id
	 */
	public void deleteById(Integer id) {
		this.studentDao.deleteById(id);
	}
}
