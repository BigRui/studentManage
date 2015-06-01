package com.oracle.lnsd.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DButils {
	/**
	 * ȡ��jdbc��Connection
	 * @return
	 */
	public static Connection getDbConnection() {
		Connection con = null;
		try {
			//1.�������ݿ�����
	        Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
	        e.printStackTrace();
        }
		try {
			//2.ȡ��Connection
	        con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	/**
	 * ȡ��jdbc��Connection
	 * @return
	 */
	public static Connection getDbConnectionByDataResource() {
		Connection con = null;
		try {
			//ȡ�õ�DataSource
	        DataSource ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/studentDB");
	        //��DataSource��ȡ��Connection
	        con= ds.getConnection();
        } catch (NamingException | SQLException e) {
	        e.printStackTrace();
        }
		return con;
	}
}
