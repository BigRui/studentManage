package com.oracle.lnsd.service;

import com.oracle.lnsd.dao.StudentDao;

public class StudentService {
	private StudentDao studentDao;

	
	public StudentService() {
		System.out.println("�������޲ι��캯��");
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
