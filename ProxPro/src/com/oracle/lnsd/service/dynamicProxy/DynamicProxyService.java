package com.oracle.lnsd.service.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理的方式处理事务
 * 缺点，被代理对象必须实现某个接口
 * @author Administrator
 *
 */
public class DynamicProxyService implements InvocationHandler {
	
	private Object target;
	
	private DynamicProxyService(Object target) {
		this.target = target;
	}
	
	@SuppressWarnings("unchecked")
	public static  <T> T bind(T target) {
		InvocationHandler handler = new DynamicProxyService(target);
		return (T)Proxy.newProxyInstance(
										target.getClass().getClassLoader(), 
										target.getClass().getInterfaces(), 
										handler);
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
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
