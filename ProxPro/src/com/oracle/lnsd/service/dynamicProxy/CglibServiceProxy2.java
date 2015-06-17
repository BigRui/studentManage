package com.oracle.lnsd.service.dynamicProxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InvocationHandler;

public class CglibServiceProxy2{

	private CglibServiceProxy2() {
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T bind(T target) {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(target.getClass());
		enhancer.setCallback(new MyInvocationHandler(target));
		return (T)enhancer.create();
	}
	
	
	private static class MyInvocationHandler implements InvocationHandler{
		private Object target;
		public MyInvocationHandler(Object target) {
			this.target = target;
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
				System.out.println("---------------打开事务--------------");
			}
			
			Object result = method.invoke(target, args);
			
			if(flag) {
				System.out.println("---------------关闭事务--------------");
			}
			return result;
		}
	}
	
}
