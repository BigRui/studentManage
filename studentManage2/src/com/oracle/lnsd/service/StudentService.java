package com.oracle.lnsd.service;

import java.util.List;

import com.oracle.lnsd.dao.StudentDao;
import com.oracle.lnsd.dao.jdbcImpl.StudentDaoOracleImpl;
import com.oracle.lnsd.entity.Student;
import com.oracle.lnsd.utils.Page;
/**
 * ѧ�����ݴ�����ҵ���߼�
 * @author Administrator
 *
 */
public class StudentService {
	private StudentDao studentDao = new StudentDaoOracleImpl(); 
	
	/**
	 * ����һ��ѧ����ҵ���߼�
	 * @param student
	 * @return
	 */
	public void saveOrUpdateStudent(Student student){
		//���������ѧ��������Ҫ�ж������Ƿ���ڣ����²����ж�
		if(student.getId() == null && studentDao.isEntityExists(student.getName())) {
			throw new ServiceException("�û����Ѿ�������");
		} else {
			this.studentDao.saveOrUpdateEntity(student);
		}
	}

	public Page<Student> sharchByName(String studentName, int currentPage, int numPerPage) {
		//�����ܵ�����
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
	 * ����idɾ��һ��student��¼
	 * @param id
	 */
	public void deleteById(Integer id) {
		this.studentDao.deleteById(id);
	}
	public Student getById(Integer id) {
		return this.studentDao.getById(id);
	}
	
}