package com.oracle.lnsd.service;

public class StudentService implements StudentServiceInterface{
	
	@Override
	public void saveOrUpdate() {
		System.out.println("����ѧ����Ϣ");
	}
	@Override
	public void deleteStudent() {
		System.out.println("ɾ��һ��ѧ����Ϣ");
	}
	@Override
	public void shearchByName() {
		System.out.println("����һ��ѧ����¼ͨ������");
	}
	@Override
	public void shearchById() {
		System.out.println("����һ��ѧ����¼ͨ��id");
	}
}
