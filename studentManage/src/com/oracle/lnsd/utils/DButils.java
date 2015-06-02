package com.oracle.lnsd.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DButils {
	private static Properties pro = new Properties();
	static{
		try (InputStream input = DButils.class.getResourceAsStream("/common.properties")){
	        pro.load(input);
	        //1.加载数据库驱动
	        Class.forName(pro.getProperty("db.driver"));
        } catch (IOException | ClassNotFoundException e1) {
	        e1.printStackTrace();
        }
	}
	/**
	 * 取得jdbc的Connection
	 * @return
	 */
	public static Connection getDbConnection() {
		Connection con = null;
		try {
			//2.取得Connection
			String dbUrl = pro.getProperty("db.url");
	        String dbPassword = pro.getProperty("db.password");
			String dbUsername = pro.getProperty("db.username");
			
			con = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
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
