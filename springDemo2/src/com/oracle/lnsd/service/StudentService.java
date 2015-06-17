package com.oracle.lnsd.service;

import com.oracle.lnsd.dao.StudentDao;

public class StudentService {
	private StudentDao studentDao;

	
	public StudentService() {
		System.out.println("调用了无参构造函数");
	}

	public StudentDao getStudentDao() {
		return studentDao;
	}

	public void setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
	}
	
	public void service(){
		this.studentDao.daoTest();
	}
}
