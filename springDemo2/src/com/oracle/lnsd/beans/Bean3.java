package com.oracle.lnsd.beans;

public class Bean3  implements BeanInterface {
	private String name = "����bean3";

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Bean1 [name=" + name + "]";
	}
	
	public void initMethod(){
		System.out.println("�����˳�ʼ������");
	}
	public void destroyMethod(){
		System.out.println("���������ٷ���");
	}
	@Override
	public void sayHello() {
		System.out.println("hello ���� bean3");
	}
	
}
