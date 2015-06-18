package com.oracle.lnsd.service.aspectjProxy;

public class MyInterfaceImpl implements MyInterface {

	@Override
	public void showMyInterface() {
		System.out.println("我实现了MyInterface接口，执行接口中方法");
	}

}
