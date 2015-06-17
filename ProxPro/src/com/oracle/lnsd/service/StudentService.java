package com.oracle.lnsd.service;

public class StudentService implements StudentServiceInterface{
	
	@Override
	public void saveOrUpdate() {
		System.out.println("保存学生信息");
	}
	@Override
	public void deleteStudent() {
		System.out.println("删除一条学生信息");
	}
	@Override
	public void shearchByName() {
		System.out.println("检索一条学生记录通过姓名");
	}
	@Override
	public void shearchById() {
		System.out.println("检索一条学生记录通过id");
	}
}
