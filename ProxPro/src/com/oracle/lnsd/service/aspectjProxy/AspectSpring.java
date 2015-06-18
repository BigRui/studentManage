package com.oracle.lnsd.service.aspectjProxy;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.DeclareParents;
import org.aspectj.lang.annotation.Pointcut;

@Aspect

public class AspectSpring {
	@Pointcut("execution(* com.oracle.lnsd.service.*Service.save*(..))")
	public void savePointcut(){}
	//�����е�
	@Pointcut("savePointcut() || " +
			"execution(* com.oracle.lnsd.service.*Service.*update*(..)) ||" +
			"execution(* com.oracle.lnsd.service.*Service.delete*(..))")
	public void pointcut(){}
	
	@Before("pointcut()")
	public void beforAdvise(){
		System.out.println("---������---");
	}
	
	@After("pointcut()") //�ȼ�finally
	public void affter(){
		System.out.println("---�ر�����---");
	}
	@AfterThrowing(value="pointcut()" , throwing="ex")
	public void throwException(RuntimeException ex){
		System.out.println("�׳����쳣:"+ ex.toString());
		System.out.println("---�ع�����---");
	}
	@Around(value="pointcut()")
	public Object around(ProceedingJoinPoint jpt) throws Throwable {
		System.out.println("---------��ʼ����--------");
		Object result = jpt.proceed();
		System.out.println("---------�ύ����--------");
		return result;
	}
	
	//������ǿ���﷨�ܹ��죬ע�⣬�е�ֻ�ܵ�����
	@DeclareParents(value="com.oracle..*.StudentService", defaultImpl=MyInterfaceImpl.class)
	public MyInterface myInterface;
}
