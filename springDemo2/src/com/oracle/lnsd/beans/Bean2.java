package com.oracle.lnsd.beans;

public class Bean2  implements BeanInterface {
	private String name = "����bean2";

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Bean1 [name=" + name + "]";
	}
	@Override
	public void sayHello() {
		System.out.println("hello ���� bean2");
	}
	
}
