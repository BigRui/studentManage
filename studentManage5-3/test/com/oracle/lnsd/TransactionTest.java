package com.oracle.lnsd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Savepoint;

import org.junit.Test;

import com.oracle.lnsd.util.DbSourceImp;

/**
 * 帮助大家回忆事务控制
 * @author Administrator
 *
 */
public class TransactionTest {

	@Test
	/**
	 * 没有事务控制
	 * @throws SQLException
	 */
	public void test() throws SQLException {
		Connection connection = new DbSourceImp().getConnection();
		String sql = null;
        sql = "insert into student(id, name, age, email) values(pkid.nextval, ?, ?, ?)";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1, "zhangsanfeng");
        pst.setInt(2, 123);
        pst.setString(3, "67529468@qq.com");
        pst.executeUpdate();
        int i = 1/0;
        connection.close();
	}
	@Test
	/**
	 * 有事务控制
	 * @throws Exception
	 */
	public void test2() throws Exception {
		Connection connection = new DbSourceImp().getConnection();
		connection.setAutoCommit(false);
		Savepoint savepoint = connection.setSavepoint(); //设置回滚点
		//增删改查
		String sql = null;
        sql = "insert into student(id, name, age, email) values(pkid.nextval, ?, ?, ?)";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1, "zhangsanfeng");
        pst.setInt(2, 123);
        pst.setString(3, "67529468@qq.com");
        pst.executeUpdate();
        try {
			int i = 1 / 0;
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			connection.rollback(savepoint); //指定回滚到哪一点
		}
		connection.close();
	}

}
