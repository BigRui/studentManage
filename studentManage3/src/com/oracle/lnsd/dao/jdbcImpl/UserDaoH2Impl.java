package com.oracle.lnsd.dao.jdbcImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.oracle.lnsd.dao.UserDao;
import com.oracle.lnsd.entity.User;
import com.oracle.lnsd.utils.DbSource;

public class UserDaoH2Impl implements UserDao {
	private DbSource dbs;

	@Override
	public void setDbSource(DbSource dbSorce) {
		this.dbs = dbSorce;
	}
	@Override
	public User findUserByUserName(String userName) {
		Connection con = dbs.getConnection();
		User user = null;
		try {
	        String sql = "select id, user_name, real_name, password from users u where u.user_name = ?";
			PreparedStatement prepareStatement = con.prepareStatement(sql);
			prepareStatement.setString(1, userName);
	        ResultSet rs = prepareStatement.executeQuery();
	        if(rs.next()) {
	        	Integer id = rs.getInt("id");
	        	String userName2 = rs.getString("user_name");
	        	String realName = rs.getString("real_name");
	        	String password = rs.getString("password");
	        	user = new User(id, userName2, realName, password);
	        }
        } catch (SQLException e) {
	        e.printStackTrace();
        }
		return user;
	}


}
