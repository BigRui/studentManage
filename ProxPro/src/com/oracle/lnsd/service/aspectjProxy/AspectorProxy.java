package com.oracle.lnsd.service.aspectjProxy;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

public class AspectorProxy implements MethodBeforeAdvice {

	@Override
	public void before(Method method, Object[] args, Object target)
			throws Throwable {
		System.out.println("ͨ��aspector�ķ�ʽʵ�֣���������" + method.getName() + "������" + target.getClass());
	}
	
}
