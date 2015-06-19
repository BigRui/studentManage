package com.oracle.lnsd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Savepoint;

import org.junit.Test;

import com.oracle.lnsd.util.DbSourceImp;

/**
 * ������һ����������
 * @author Administrator
 *
 */
public class TransactionTest {

	@Test
	/**
	 * û���������
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
	 * ���������
	 * @throws Exception
	 */
	public void test2() throws Exception {
		Connection connection = new DbSourceImp().getConnection();
		connection.setAutoCommit(false);
		Savepoint savepoint = connection.setSavepoint(); //���ûع���
		//��ɾ�Ĳ�
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
			connection.rollback(savepoint); //ָ���ع�����һ��
		}
		connection.close();
	}

}
