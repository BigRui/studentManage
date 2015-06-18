package com.oracle.lnsd.dao.jdbcImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.oracle.lnsd.dao.DaoException;
import com.oracle.lnsd.dao.UserDao;
import com.oracle.lnsd.entity.User;

public class UserDaoH2Impl implements UserDao {
	private DataSource dbs;

	@Override
	public void setDbSource(DataSource dbSorce) {
		this.dbs = dbSorce;
	}
	@Override
	public User findUserByUserName(String userName) {
		Connection con = null;
		User user = null;
		try {
			con = dbs.getConnection();
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
	        throw new DaoException(e);
        }
		return user;
	}


}