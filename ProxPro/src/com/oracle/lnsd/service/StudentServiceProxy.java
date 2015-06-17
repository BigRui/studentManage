package com.oracle.lnsd.service;

/**
 * userservice�ľ�̬����
 * @author Administrator
 *
 */
public class StudentServiceProxy implements StudentServiceInterface {
	/**
	 * ���������
	 */
	private StudentServiceInterface userServiceInterface;
	
	
	public StudentServiceProxy(StudentServiceInterface userServiceInterface) {
		super();
		this.userServiceInterface = userServiceInterface;
	}

	@Override
	public void saveOrUpdate() {
		System.out.println("---------------������--------------");
		this.userServiceInterface.saveOrUpdate();
		System.out.println("---------------�ر�����--------------");

	}

	@Override
	public void deleteStudent() {
		System.out.println("---------------������--------------");
		this.userServiceInterface.deleteStudent();
		System.out.println("---------------�ر�����--------------");
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
