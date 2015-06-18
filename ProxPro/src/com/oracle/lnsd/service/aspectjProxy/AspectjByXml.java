package com.oracle.lnsd.service.aspectjProxy;

/**
 * 完全通过配置文件的方式，实现通过注解方式aspectj完全一样的效果
 * @author Administrator
 *
 */
public class AspectjByXml {
	
	public void beforeInAspectjByXml(){
		System.out.println("这是通过配置文件的方式实现的前置增强");
	}
}
