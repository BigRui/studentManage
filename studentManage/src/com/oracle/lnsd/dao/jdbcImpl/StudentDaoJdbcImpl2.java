package com.oracle.lnsd.dao.jdbcImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.oracle.lnsd.dao.StudentDao;
import com.oracle.lnsd.entity.Student;
import com.oracle.lnsd.utils.DButils;

/**
 * jdbc实现的第二个版本。
 * 引入了DButils来提供Connection
 * @author Administrator
 *
 */
public class StudentDaoJdbcImpl2 implements StudentDao {

	@Override
	public boolean isEntityExists(String name) {
		boolean result = false;
		Connection con = null;
		try {
			//2.取得Connection
	        con = DButils.getDbConnection();
	        String sql = "select id from student s where s.name = ? ";
	        //3.创建PreparedStatement
	        PreparedStatement pst = con.prepareStatement(sql);
	        pst.setString(1, name);
	        //4.取得ResultSet
	        ResultSet rs = pst.executeQuery();
	        if(rs.next()) {
	        	result = true;
	        }
        } catch (SQLException e) {
	        e.printStackTrace();
        } finally {
        	if(con != null) {
        		try {
	                con.close();
                } catch (SQLException e) {
	                e.printStackTrace();
                }
        	}
        }
		return result;
	}

	@Override
	public void saveEntity(Student student) {
		Connection con = null;
		try {
			//2.取得Connection
			 con = DButils.getDbConnection();
	        String sql = "insert into student(id, name, age, email) values(pkid.nextval, ?, ?, ?)";
	        //3.创建PreparedStatement
	        PreparedStatement pst = con.prepareStatement(sql);
	        pst.setString(1, student.getName());
	        pst.setInt(2, student.getAge());
	        pst.setString(3, student.getEmail());
	        //4.取得ResultSet
	        pst.executeUpdate();
	       
        } catch (SQLException e) {
	        e.printStackTrace();
        } finally {
        	if(con != null) {
        		try {
	                con.close();
                } catch (SQLException e) {
	                e.printStackTrace();
                }
        	}
        }

	}

	@Override
	public List<Student> listStudent() {
		List<Student> studentList = new ArrayList<>();
		Connection con = null;
		try {
			//2.取得Connection
			 con = DButils.getDbConnection();
	        String sql = "select id, name, age, email from student";
	        //3.创建PreparedStatement
	        PreparedStatement pst = con.prepareStatement(sql);
	        //4.取得ResultSet
	        ResultSet rs = pst.executeQuery();
	        while(rs.next()) {
	        	Student stu = new Student(rs.getInt("id"), rs.getString("name"), rs.getInt("age"), rs.getString("email"));
	        	studentList.add(stu);
	        }
        } catch (SQLException e) {
	        e.printStackTrace();
        } finally {
        	if(con != null) {
        		try {
	                con.close();
                } catch (SQLException e) {
	                e.printStackTrace();
                }
        	}
        }
		return studentList;
	}

}
