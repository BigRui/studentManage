package com.oracle.lnsd.beans;

public class Bean3  implements BeanInterface {
	private String name = "我是bean3";

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Bean1 [name=" + name + "]";
	}
	
	public void initMethod(){
		System.out.println("调用了初始化方法");
	}
	public void destroyMethod(){
		System.out.println("调用了销毁方法");
	}
	@Override
	public void sayHello() {
		System.out.println("hello 我是 bean3");
	}
	
}
