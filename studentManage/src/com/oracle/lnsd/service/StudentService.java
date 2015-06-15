package com.oracle.lnsd.service;

import java.util.List;

import com.oracle.lnsd.dao.StudentDao;
import com.oracle.lnsd.dao.jdbcImpl.StudentDaoJdbcImpl2;
import com.oracle.lnsd.entity.Student;
/**
 * 学生数据处理的业务逻辑
 * @author Administrator
 *
 */
public class StudentService {
	private StudentDao studentDao = new StudentDaoJdbcImpl2(); 
	
	/**
	 * 添加一个学生的业务逻辑
	 * @param student
	 * @return
	 */
	public void addStudent(Student student){
		if(studentDao.isEntityExists(student.getName())) {
			throw new ServiceException("用户名已经存在了");
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
	 * 根据id删除一个student记录
	 * @param id
	 */
	public void deleteById(Integer id) {
		this.studentDao.deleteById(id);
	}
}
