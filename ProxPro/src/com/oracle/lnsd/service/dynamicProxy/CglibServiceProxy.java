package com.oracle.lnsd.service.dynamicProxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InvocationHandler;

public class CglibServiceProxy implements InvocationHandler {
	private Object target;

	private CglibServiceProxy(Object target) {
		this.target = target;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T bind(T target) {
		InvocationHandler handler = new CglibServiceProxy(target);
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(target.getClass());
		enhancer.setCallback(handler);
		return (T)enhancer.create();
	}

	@Override
	public Object invoke(Object arg0, Method method, Object[] args)
			throws Throwable {
		boolean flag = false;
		String methodName = method.getName();
		if(methodName.contains("save") || methodName.contains("update") || methodName.contains("delete")) {
			flag = true;
		}
		if(flag){
			System.out.println("---------------������--------------");
		}
		
		Object result = method.invoke(target, args);
		
		if(flag) {
			System.out.println("---------------�ر�����--------------");
		}
		return result;
	}
	
}
