package com.oracle.lnsd.dao.jdbcImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.oracle.lnsd.dao.UserDao;
import com.oracle.lnsd.entity.User;

@Repository("userDao")
public class UserDaoOracleImpl implements UserDao {
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	private RowMapper<User> userRowMapper = new RowMapper<User>() {

		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setId(rs.getInt("id"));
			user.setPassword(rs.getString("password"));
			user.setRealName(rs.getString("real_name"));
			user.setUserName(rs.getString("user_name"));
			return user;
		}
	};
	
	@Override
	public User findUserByUserName(String userName) {
		String sql = "select id, user_name, real_name, password from users u where u.user_name = ?";
//		return this.jdbcTemplate.queryForObject(sql,this.userRowMapper, userName);
		List<User> userList = this.jdbcTemplate.query(sql,this.userRowMapper, userName);
		return userList.size() == 0  ? null: userList.get(0);
	}

}