package com.oracle.lnsd.service;

import java.util.List;

import com.oracle.lnsd.dao.StudentDao;
import com.oracle.lnsd.dao.jdbcImpl.StudentDaoJdbcImpl2;
import com.oracle.lnsd.entity.Student;
import com.oracle.lnsd.utils.Page;
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

	public Page<Student> sharchByName(String studentName, int currentPage, int numPerPage) {
		//放入总的行数
		int totalMum = this.studentDao.getTotalNum(studentName);
		Page<Student> page = new Page<>(currentPage,numPerPage, totalMum);
		List<Student> list = this.studentDao.sharchByName(studentName, page.getOffset(), numPerPage);
		page.setList(list);
		return page;
	}
	public Page<Student> sharchByName(){
		int currentPage = Page.DEFAULT_CURRENT_PAGE;
		int numPerPage = Page.DEFAULT_NUM_PER_PAGE;
		return this.sharchByName(null, currentPage, numPerPage);
	}
	/**
	 * 根据id删除一个student记录
	 * @param id
	 */
	public void deleteById(Integer id) {
		this.studentDao.deleteById(id);
	}
	public Student getById(Integer id) {
		return this.studentDao.getById(id);
	}
	public void updateStudent(Student student) {
		this.studentDao.updateEntity(student); 
	}
}
