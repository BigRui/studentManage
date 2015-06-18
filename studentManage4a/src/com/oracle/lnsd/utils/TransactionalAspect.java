package com.oracle.lnsd.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TransactionalAspect {
	private static final ThreadLocal<Connection> CONNECTION_THREADLOCAL = new ThreadLocal<>();
	private static final ThreadLocal<Savepoint> SAVEPOINT_THREADLOCAL = new ThreadLocal<>();
	
	@Resource
	private DataSource dataSource;
	
	//在当前线程中任意位置都能取得一个Connection，但是不一定打开新的，如果已经有了就用当前的Connection
	public Connection getConnection() throws SQLException{
		Connection con = CONNECTION_THREADLOCAL.get();
		if(con == null) {
			System.out.println("===========取得连接==============");
			con = dataSource.getConnection();
			CONNECTION_THREADLOCAL.set(con);
		}
		return con;
	}
	
	@Pointcut(value="execution(* com.oracle.lnsd.service.*Service.*save*(..)) || " +
			"execution(* com.oracle.lnsd.service.*Service.*update*(..)) || " +
			"execution(* com.oracle.lnsd.service.*Service.*delete*(..))")
	public void txPointCut(){}
	
	
	@Before(value="txPointCut()")
	public void before() throws SQLException {
		Connection con = getConnection();
		Savepoint savepoint = SAVEPOINT_THREADLOCAL.get();
		if(savepoint == null) {
			System.out.println("===========打开事务===========");
			con.setAutoCommit(false);
			con.setSavepoint();
		}
	}
	
	
	
	@AfterReturning(value="txPointCut()")
	public void afterReturning() throws SQLException {
		Connection con = CONNECTION_THREADLOCAL.get();
		System.out.println("=========提交事务=============");
		con.commit();
		con.close();
		//清理资源
		CONNECTION_THREADLOCAL.remove();
		SAVEPOINT_THREADLOCAL.remove();
	}
	
	@AfterThrowing(value="txPointCut()", throwing="ex")
	public void afterThrowing(RuntimeException ex) throws SQLException{
		//不是所有的异常需要回滚，一般情况RuntimeException需要回滚
		Connection con = CONNECTION_THREADLOCAL.get();
		Savepoint savepoint = SAVEPOINT_THREADLOCAL.get();
		System.out.println("=======抛出异常"+ ex.getMessage() + "开始回滚=====");
		con.rollback(savepoint);
		//清理资源
		con.close();
		CONNECTION_THREADLOCAL.remove();
		SAVEPOINT_THREADLOCAL.remove();
	}
	
	///////////////////////////////////无事务控制时的切面
	
	@Before(value="execution(* com.oracle.lnsd.service.*Service.*(..)) && not txPointCut()")
	public void beforeNoTx() throws SQLException {
		System.out.println("========没有事务控制时========");
		getConnection();
	}
	@After(value="execution(* com.oracle.lnsd.service.*Service.*(..)) && not txPointCut()")
	public void afterNoTx() throws SQLException {
		System.out.println("========没有事务控制时，关闭连接连接========");
		Connection con = CONNECTION_THREADLOCAL.get();
		//清理资源
		con.close();
		CONNECTION_THREADLOCAL.remove();
	}
}
