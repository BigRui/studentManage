package com.oracle.lnsd.utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ProcessTimeRecordAspect {
	/**
	 * 线程级的全局变量
	 */
	private static final ThreadLocal<Long> START_TIME_THREADLOCAL = new ThreadLocal<>();
	
	@Pointcut("execution(* com.oracle.lnsd.dao..*.*Dao*.sharch*(..))")
	public void poitcut(){}
	
	@Before(value="poitcut()")
	public void before() { 
		START_TIME_THREADLOCAL.set(System.nanoTime());
	}
	@After(value="poitcut()")
	public void affter(JoinPoint jpt){//想知道target的方法名。
		long startTime = START_TIME_THREADLOCAL.get();
		long endTime = System.nanoTime();
		System.out.print("=======方法：" + jpt.getSignature().getName());
		System.out.println("共执行的时间：" + (endTime - startTime)+"========");
	}
}
