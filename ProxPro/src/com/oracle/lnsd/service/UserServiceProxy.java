package com.oracle.lnsd.service;

public class UserServiceProxy implements UserServiceInterface {
	private UserServiceInterface userServiceInterface;
	
	@Override
	public void saveOrUpdate() {
		System.out.println("---------------������--------------");
		userServiceInterface.saveOrUpdate();
		System.out.println("---------------�ر�����--------------");

	}

	@Override
	public void delete() {
		System.out.println("---------------������--------------");
		this.userServiceInterface.delete();
		System.out.println("---------------�ر�����--------------");
	}

	@Override
	public void shearchByName() {

	}

	@Override
	public void shearchById() {

	}

}
