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
	 * �̼߳���ȫ�ֱ���
	 */
	private static final ThreadLocal<Long> START_TIME_THREADLOCAL = new ThreadLocal<>();
	
	@Pointcut("execution(* com.oracle.lnsd.dao..*.*Dao*.sharch*(..))")
	public void poitcut(){}
	
	@Before(value="poitcut()")
	public void before() { 
		START_TIME_THREADLOCAL.set(System.nanoTime());
	}
	@After(value="poitcut()")
	public void affter(JoinPoint jpt){//��֪��target�ķ�������
		long startTime = START_TIME_THREADLOCAL.get();
		long endTime = System.nanoTime();
		System.out.print("=======������" + jpt.getSignature().getName());
		System.out.println("��ִ�е�ʱ�䣺" + (endTime - startTime)+"========");
	}
}
