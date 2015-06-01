package com.oracle.lnsd.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DButils {
	/**
	 * 取得jdbc的Connection
	 * @return
	 */
	public static Connection getDbConnection() {
		Connection con = null;
		try {
			//1.加载数据库驱动
	        Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
	        e.printStackTrace();
        }
		try {
			//2.取得Connection
	        con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	/**
	 * 取得jdbc的Connection
	 * @return
	 */
	public static Connection getDbConnectionByDataResource() {
		Connection con = null;
		try {
			//取得到DataSource
	        DataSource ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/studentDB");
	        //从DataSource中取得Connection
	        con= ds.getConnection();
        } catch (NamingException | SQLException e) {
	        e.printStackTrace();
        }
		return con;
	}
}
