package com.oracle.lnsd.service.aspectjProxy;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

public class AspectorProxy implements MethodBeforeAdvice {

	@Override
	public void before(Method method, Object[] args, Object target)
			throws Throwable {
		System.out.println("通过aspector的方式实现，方法名：" + method.getName() + "类名：" + target.getClass());
	}
	
}
