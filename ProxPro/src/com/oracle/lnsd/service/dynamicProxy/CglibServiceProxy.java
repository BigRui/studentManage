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
		if(method.getName().contains("save") || method.getName().contains("update") || method.getName().contains("delete")) {
			flag = true;
		}
		if(flag){
			System.out.println("---------------打开事务--------------");
		}
		
		Object result = method.invoke(target, args);
		
		if(flag) {
			System.out.println("---------------关闭事务--------------");
		}
		return result;
	}
	
}
