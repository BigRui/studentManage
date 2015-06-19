package com.oracle.lnsd.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
public class DbSourceImp implements DbSource {
	private static Properties pro = new Properties();
	static{
		try (InputStream input = DbSourceImp.class.getResourceAsStream("/common.properties")){
	        pro.load(input);
	        //1.加载数据库驱动
	        Class.forName(pro.getProperty("db.driver"));
        } catch (IOException | ClassNotFoundException e1) {
	        e1.printStackTrace();
        }
	}
	@Override
	public Connection getConnection() {
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

}
