package com.oracle.lnsd.service;

public class UserService implements UserServiceInterface {
	
	@Override
	public void saveOrUpdate() {
		System.out.println("�����û���Ϣ");
	}
	@Override
	public void delete() {
		System.out.println("ɾ��һ���û���Ϣ");
	}
	@Override
	public void shearchByName() {
		System.out.println("����һ���û���¼ͨ������");
	}
	@Override
	public void shearchById() {
		System.out.println("����һ���û���¼ͨ��id");
	}
}
