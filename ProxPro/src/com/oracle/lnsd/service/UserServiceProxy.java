package com.oracle.lnsd.service;

public class UserServiceProxy implements UserServiceInterface {
	private UserServiceInterface userServiceInterface;
	
	@Override
	public void saveOrUpdate() {
		System.out.println("---------------打开事务--------------");
		userServiceInterface.saveOrUpdate();
		System.out.println("---------------关闭事务--------------");

	}

	@Override
	public void delete() {
		System.out.println("---------------打开事务--------------");
		this.userServiceInterface.delete();
		System.out.println("---------------关闭事务--------------");
	}

	@Override
	public void shearchByName() {

	}

	@Override
	public void shearchById() {

	}

}
