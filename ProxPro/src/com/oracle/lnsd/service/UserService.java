package com.oracle.lnsd.service;

import org.springframework.stereotype.Component;

@Component
public class UserService implements UserServiceInterface {
	
	@Override
	public void saveOrUpdate() {
		System.out.println("�����û���Ϣ");
		int i = 1/0;
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
