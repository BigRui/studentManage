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
	//命名切点
	@Pointcut("savePointcut() || " +
			"execution(* com.oracle.lnsd.service.*Service.*update*(..)) ||" +
			"execution(* com.oracle.lnsd.service.*Service.delete*(..))")
	public void pointcut(){}
	
	@Before("pointcut()")
	public void beforAdvise(){
		System.out.println("---打开事务---");
	}
	
	@After("pointcut()") //等价finally
	public void affter(){
		System.out.println("---关闭事务---");
	}
	@AfterThrowing(value="pointcut()" , throwing="ex")
	public void throwException(RuntimeException ex){
		System.out.println("抛出了异常:"+ ex.toString());
		System.out.println("---回滚事务---");
	}
	@Around(value="pointcut()")
	public Object around(ProceedingJoinPoint jpt) throws Throwable {
		System.out.println("---------开始事务--------");
		Object result = jpt.proceed();
		System.out.println("---------提交事务--------");
		return result;
	}
	
	//引介增强，语法很怪异，注意，切点只能到类名
	@DeclareParents(value="com.oracle..*.StudentService", defaultImpl=MyInterfaceImpl.class)
	public MyInterface myInterface;
}
