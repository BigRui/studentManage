package com.oracle.lnsd.dao.jdbcImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.oracle.lnsd.dao.DaoException;
import com.oracle.lnsd.dao.StudentDao;
import com.oracle.lnsd.entity.Student;
import com.oracle.lnsd.utils.DButils;

/**
 * jdbcʵ�ֵĵڶ����汾��
 * ������DButils���ṩConnection
 * @author Administrator
 *
 */
public class StudentDaoJdbcImpl2 implements StudentDao {

	@Override
	public boolean isEntityExists(String name) {
		boolean result = false;
		Connection con = null;
		try {
			//2.ȡ��Connection
	        con = DButils.getDbConnection();
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
        } finally {
        	if(con != null) {
        		try {
	                con.close();
                } catch (SQLException e) {
                	throw new DaoException("connection�ر��쳣" ,e);
                }
        	}
        }
		return result;
	}

	@Override
	public void saveEntity(Student student) {
		Connection con = null;
		try {
			//2.ȡ��Connection
			 con = DButils.getDbConnection();
	        String sql = "insert into student(id, name, age, email) values(pkid.nextval, ?, ?, ?)";
	        //3.����PreparedStatement
	        PreparedStatement pst = con.prepareStatement(sql);
	        pst.setString(1, student.getName());
	        pst.setInt(2, student.getAge());
	        pst.setString(3, student.getEmail());
	        //4.ȡ��ResultSet
	        pst.executeUpdate();
	       
        } catch (SQLException e) {
        	throw new DaoException("sqlд����" ,e);
        } finally {
        	if(con != null) {
        		try {
	                con.close();
                } catch (SQLException e) {
                	throw new DaoException("connection�ر��쳣" ,e);
                }
        	}
        }

	}

	@Override
	public List<Student> listStudent() {
		List<Student> studentList = new ArrayList<>();
		Connection con = null;
		try {
			//2.ȡ��Connection
			 con = DButils.getDbConnection();
	        String sql = "select id, name, age, email from student";
	        //3.����PreparedStatement
	        PreparedStatement pst = con.prepareStatement(sql);
	        //4.ȡ��ResultSet
	        ResultSet rs = pst.executeQuery();
	        while(rs.next()) {
	        	Student stu = new Student(rs.getInt("id"), rs.getString("name"), rs.getInt("age"), rs.getString("email"));
	        	studentList.add(stu);
	        }
        } catch (SQLException e) {
        	throw new DaoException("sqlд����" ,e);
        } finally {
        	if(con != null) {
        		try {
	                con.close();
                } catch (SQLException e) {
                	throw new DaoException("connection�ر��쳣" ,e);
                }
        	}
        }
		return studentList;
	}

	@Override
	public List<Student> sharchByName(String studentName) {
		List<Student> studentList = new ArrayList<>();
		Connection con = null;
		try {
			//2.ȡ��Connection
			 con = DButils.getDbConnection();
			 String sql = "select id, name, age, email from student where name like '%" + studentName + "%'";
	        //3.����PreparedStatement
	        PreparedStatement pst = con.prepareStatement(sql);
	        //4.ȡ��ResultSet
	        ResultSet rs = pst.executeQuery();
	        while(rs.next()) {
	        	Student stu = new Student(rs.getInt("id"), rs.getString("name"), rs.getInt("age"), rs.getString("email"));
	        	studentList.add(stu);
	        }
        } catch (SQLException e) {
        	throw new DaoException("sqlд����" ,e);
        } finally {
        	if(con != null) {
        		try {
	                con.close();
                } catch (SQLException e) {
                	throw new DaoException("connection�ر��쳣" ,e);
                }
        	}
        }
		return studentList;
	}

	@Override
	public void deleteById(Integer id) {
		Connection con = null;
		try {
			//2.ȡ��Connection
			 con = DButils.getDbConnection();
	        String sql = "delete from student where id = ?";
	        //3.����PreparedStatement
	        PreparedStatement pst = con.prepareStatement(sql);
	       	pst.setInt(1, id);

	        //4.ȡ��ResultSet
	        pst.executeUpdate();
	       
        } catch (SQLException e) {
        	throw new DaoException("sqlд����" ,e);
        } finally {
        	if(con != null) {
        		try {
	                con.close();
                } catch (SQLException e) {
                	throw new DaoException("connection�ر��쳣" ,e);
                }
        	}
        }
		
	}

}
