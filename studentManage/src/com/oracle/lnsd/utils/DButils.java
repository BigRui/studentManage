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
		InputStream input = DButils.class.getResourceAsStream("/common.properties");
		try {
	        pro.load(input);
	        input.close();
        } catch (IOException e1) {
	        e1.printStackTrace();
        } finally {
        	try {
	            input.close();
            } catch (IOException e) {
	            e.printStackTrace();
            }
        }
		
		try {
			//1.�������ݿ�����
	        Class.forName(pro.getProperty("db.driver"));
        } catch (ClassNotFoundException e) {
	        e.printStackTrace();
        }
	}
	/**
	 * ȡ��jdbc��Connection
	 * @return
	 */
	public static Connection getDbConnection() {
		Connection con = null;
		try {
			//2.ȡ��Connection
	        con = DriverManager.getConnection(pro.getProperty("db.url"), pro.getProperty("db.username"), pro.getProperty("db.password"));
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
