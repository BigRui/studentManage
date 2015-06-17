package com.oracle.lnsd.beans;

public class Bean1 implements BeanInterface {
	private String name;

	public Bean1(String name) {
		super();
		this.name = name;
	}

	@Override
	public String toString() {
		return "Bean1 [name=" + name + "]";
	}

	@Override
	public void sayHello() {
		System.out.println("hello Œ“ « bean1");
	}
	
}
