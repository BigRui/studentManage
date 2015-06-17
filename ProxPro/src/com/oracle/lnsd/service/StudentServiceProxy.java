package com.oracle.lnsd.service;

/**
 * userservice的静态代理
 * @author Administrator
 *
 */
public class StudentServiceProxy implements StudentServiceInterface {
	/**
	 * 被代理对象
	 */
	private StudentServiceInterface userServiceInterface;
	
	
	public StudentServiceProxy(StudentServiceInterface userServiceInterface) {
		super();
		this.userServiceInterface = userServiceInterface;
	}

	@Override
	public void saveOrUpdate() {
		System.out.println("---------------打开事务--------------");
		this.userServiceInterface.saveOrUpdate();
		System.out.println("---------------关闭事务--------------");

	}

	@Override
	public void deleteStudent() {
		System.out.println("---------------打开事务--------------");
		this.userServiceInterface.deleteStudent();
		System.out.println("---------------关闭事务--------------");
	}

	@Override
	public void shearchByName() {
		this.userServiceInterface.shearchByName();
	}

	@Override
	public void shearchById() {
		this.userServiceInterface.shearchById();
	}

}
