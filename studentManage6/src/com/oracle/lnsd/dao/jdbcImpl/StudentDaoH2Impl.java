package com.oracle.lnsd.dao.jdbcImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;

import com.oracle.lnsd.dao.DaoException;
import com.oracle.lnsd.dao.StudentDao;
import com.oracle.lnsd.entity.Student;

/**
 * jdbcʵ�ֵĵڶ����汾��
 * ������DButils���ṩConnection
 * @author Administrator
 *
 */

public class StudentDaoH2Impl implements StudentDao {
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public boolean isEntityExists(String name) {
		boolean result = false;
		Connection con = null;
		try {
			//2.ȡ��Connection
	        con = DataSourceUtils.getConnection(dataSource);
	        String sql = "select id from student s where s.name = ? ";
	        //3.����PreparedStatement
	        PreparedStatement pst = con.prepareStatement(sql);
	        pst.setString(1, name);
	        //4.ȡ��ResultSet
	        ResultSet rs = pst.executeQuery();
	        if(rs.next()) {
	        	result = true;
	        }
        } catch (SQLException e) {
	        throw new DaoException("sqlд����" ,e);
        }
		return result;
	}

	@Override
	public void saveOrUpdateEntity(Student student) {
		Connection con = null;
		try {
			//2.ȡ��Connection
			 con = DataSourceUtils.getConnection(dataSource);
	        String sql = null;
	        PreparedStatement pst = null;
	        if(student.getId() == null) {
	        	sql = "insert into student(name, age, email) values(?, ?, ?)";
	        	pst = con.prepareStatement(sql);
	        	pst.setString(1, student.getName());
	        	pst.setInt(2, student.getAge());
	        	pst.setString(3, student.getEmail());
	        } else {
	        	sql = "update student set name = ?, age = ?, email = ? where id = ?";
	        	pst = con.prepareStatement(sql);
	        	pst.setString(1, student.getName());
	        	pst.setInt(2, student.getAge());
	        	pst.setString(3, student.getEmail());
	        	pst.setInt(4, student.getId());
	        }
			//4.ȡ��ResultSet
	        pst.executeUpdate();
	       
        } catch (SQLException e) {
        	throw new DaoException("sqlд����" ,e);
        }
	}

	
	@Override
	public List<Student> sharchByName(String studentName, int offset, int numPerPage) {
		List<Student> studentList = new ArrayList<>();
		Connection con = null;
		if(studentName == null || "".endsWith(studentName.trim())){
			studentName = "%";
		} else {
			studentName = "%" + studentName.trim() + "%";
		}
		try {
			//2.ȡ��Connection
			 con = DataSourceUtils.getConnection(dataSource);
			 String sql = 
					 "select id, name, age, email from student where name like ? order by id limit ? offset ? ";

	        //3.����PreparedStatement
	        PreparedStatement pst = con.prepareStatement(sql);
	        pst.setString(1, studentName);
	        pst.setInt(2, numPerPage);
	        pst.setInt(3, offset);
	        //4.ȡ��ResultSet
	        ResultSet rs = pst.executeQuery();
	        while(rs.next()) {
	        	Student stu = new Student(rs.getInt("id"), rs.getString("name"), rs.getInt("age"), rs.getString("email"));
	        	studentList.add(stu);
	        }
        } catch (SQLException e) {
        	throw new DaoException("sqlд����" ,e);
        }
		return studentList;
	}

	@Override
	public void deleteById(Integer id) {
		Connection con = null;
		try {
			//2.ȡ��Connection
			 con = DataSourceUtils.getConnection(dataSource);
	        String sql = "delete from student where id = ?";
	        //3.����PreparedStatement
	        PreparedStatement pst = con.prepareStatement(sql);
	       	pst.setInt(1, id);

	        //4.ȡ��ResultSet
	        pst.executeUpdate();
	       
        } catch (SQLException e) {
        	throw new DaoException("sqlд����" ,e);
        }
		
	}

	@Override
	public int getTotalNum(String studentName) {
		int result = 0;
		Connection con = null;
		
		if(StringUtils.isBlank(studentName)){
			studentName = "%";
		} else {
			studentName = "%" + studentName.trim() + "%";
		}
		try {
			//2.ȡ��Connection
			 con = DataSourceUtils.getConnection(dataSource);
			 String sql = "select count(*) from student where name like ?";

	        //3.����PreparedStatement
	        PreparedStatement pst = con.prepareStatement(sql);
	        pst.setString(1, studentName);
	        //4.ȡ��ResultSet
	        ResultSet rs = pst.executeQuery();
	        if(rs.next()) {
	        	result = rs.getInt(1);
	        }
        } catch (SQLException e) {
        	throw new DaoException("sqlд����" ,e);
        }
		return result;
	}

	@Override
	public Student getById(Integer id) {
		Student stu = null;
		Connection con = null;
		try {
			//2.ȡ��Connection
			 con = DataSourceUtils.getConnection(dataSource);
			 String sql = "select id, name, age, email from student where id = ?";

	        //3.����PreparedStatement
	        PreparedStatement pst = con.prepareStatement(sql);
	        pst.setInt(1, id);
	        //4.ȡ��ResultSet
	        ResultSet rs = pst.executeQuery();
	        if(rs.next()) {
	        	stu = new Student(rs.getInt("id"), rs.getString("name"), rs.getInt("age"), rs.getString("email"));
	        }
        } catch (SQLException e) {
        	throw new DaoException("sqlд����" ,e);
        }
		return stu;
	}

}