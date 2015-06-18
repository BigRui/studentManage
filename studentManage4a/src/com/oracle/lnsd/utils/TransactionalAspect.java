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
	
	//�ڵ�ǰ�߳�������λ�ö���ȡ��һ��Connection�����ǲ�һ�����µģ�����Ѿ����˾��õ�ǰ��Connection
	public Connection getConnection() throws SQLException{
		Connection con = CONNECTION_THREADLOCAL.get();
		if(con == null) {
			System.out.println("===========ȡ������==============");
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
			System.out.println("===========������===========");
			con.setAutoCommit(false);
			con.setSavepoint();
		}
	}
	
	
	
	@AfterReturning(value="txPointCut()")
	public void afterReturning() throws SQLException {
		Connection con = CONNECTION_THREADLOCAL.get();
		System.out.println("=========�ύ����=============");
		con.commit();
		con.close();
		//������Դ
		CONNECTION_THREADLOCAL.remove();
		SAVEPOINT_THREADLOCAL.remove();
	}
	
	@AfterThrowing(value="txPointCut()", throwing="ex")
	public void afterThrowing(RuntimeException ex) throws SQLException{
		//�������е��쳣��Ҫ�ع���һ�����RuntimeException��Ҫ�ع�
		Connection con = CONNECTION_THREADLOCAL.get();
		Savepoint savepoint = SAVEPOINT_THREADLOCAL.get();
		System.out.println("=======�׳��쳣"+ ex.getMessage() + "��ʼ�ع�=====");
		con.rollback(savepoint);
		//������Դ
		con.close();
		CONNECTION_THREADLOCAL.remove();
		SAVEPOINT_THREADLOCAL.remove();
	}
	
	///////////////////////////////////���������ʱ������
	
	@Before(value="execution(* com.oracle.lnsd.service.*Service.*(..)) && not txPointCut()")
	public void beforeNoTx() throws SQLException {
		System.out.println("========û���������ʱ========");
		getConnection();
	}
	@After(value="execution(* com.oracle.lnsd.service.*Service.*(..)) && not txPointCut()")
	public void afterNoTx() throws SQLException {
		System.out.println("========û���������ʱ���ر���������========");
		Connection con = CONNECTION_THREADLOCAL.get();
		//������Դ
		con.close();
		CONNECTION_THREADLOCAL.remove();
	}
}
