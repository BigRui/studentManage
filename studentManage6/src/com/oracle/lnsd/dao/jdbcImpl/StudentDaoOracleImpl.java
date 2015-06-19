package com.oracle.lnsd.dao.jdbcImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.oracle.lnsd.dao.StudentDao;
import com.oracle.lnsd.entity.Student;

/**
 * jdbc实现的第二个版本。
 * 引入了DButils来提供Connection
 * @author Administrator
 *
 */
@Repository("studentDao")
public class StudentDaoOracleImpl implements StudentDao {
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	private RowMapper<Student> studentRowMapper = new RowMapper<Student>() {

		@Override
		public Student mapRow(ResultSet rs, int arg1) throws SQLException {
			Student student = new Student();
			student.setId(rs.getInt("id"));
			student.setName(rs.getString("name"));
			student.setAge(rs.getInt("age"));
			student.setEmail(rs.getString("email"));
			return student;
		}
		
	};
	
	@Override
	public boolean isEntityExists(String name) {
//		boolean result = false;
//		Connection con = null;
//		try {
//			//2.取得Connection
//	        con = DataSourceUtils.getConnection(dataSource);
//	        String sql = "select id from student s where s.name = ? ";
//	        //3.创建PreparedStatement
//	        PreparedStatement pst = con.prepareStatement(sql);
//	        pst.setString(1, name);
//	        //4.取得ResultSet
//	        ResultSet rs = pst.executeQuery();
//	        if(rs.next()) {
//	        	result = true;
//	        }
//        } catch (SQLException e) {
//	        throw new DaoException("sql写错误" ,e);
//        }
//		return result;
		String sql = "select count(*) from student s where s.name = ? ";
		//只找你能看得懂的：有sql，有参数Object[], ...;返回类型推断
//		this.jdbcTemplate.query(sql, new PreparedStatementSetter(){
//			@Override
//			public void setValues(PreparedStatement ps) throws SQLException {
//				ps.setString(1, name);
//			}
//			
//		}, new ResultSetExtractor<Integer>() {
//
//			@Override
//			public Integer extractData(ResultSet rs) throws SQLException,
//					DataAccessException {
//				return rs.getInt(1);
//			}
//		}) != 0;
		
		return this.jdbcTemplate.queryForObject(sql, Integer.class, name) != 0;
	}

	@Override
	public void saveOrUpdateEntity(Student student) {
//		Connection con = null;
//		try {
//			//2.取得Connection
//			 con = DataSourceUtils.getConnection(dataSource);
//	        String sql = null;
//	        PreparedStatement pst = null;
//	        if(student.getId() == null) {
//	        	sql = "insert into student(id, name, age, email) values(pkid.nextval, ?, ?, ?)";
//	        	pst = con.prepareStatement(sql);
//	        	pst.setString(1, student.getName());
//	        	pst.setInt(2, student.getAge());
//	        	pst.setString(3, student.getEmail());
//	        } else {
//	        	sql = "update student set name = ?, age = ?, email = ? where id = ?";
//	        	pst = con.prepareStatement(sql);
//	        	pst.setString(1, student.getName());
//	        	pst.setInt(2, student.getAge());
//	        	pst.setString(3, student.getEmail());
//	        	pst.setInt(4, student.getId());
//	        }
//			//4.取得ResultSet
//	        pst.executeUpdate();
//	       
//        } catch (SQLException e) {
//        	throw new DaoException("sql写错误" ,e);
//        }
		
		String sql;
	/*	
	 * if(student.getId() == null) {
			sql = "insert into student(name, age, email, id) values(?, ?, ?,pkid.nextval)";
			this.jdbcTemplate.update(sql, new Object[]{student.getName(), student.getAge(), student.getEmail()}, new int[]{Types.VARCHAR, Types.INTEGER, Types.VARCHAR});
		} else {
			sql = "update student set name = ?, age = ?, email = ? where id = ?";
			this.jdbcTemplate.update(sql, new Object[]{student.getName(), student.getAge(), student.getEmail(), student.getId()}, new int[]{Types.VARCHAR, Types.INTEGER, Types.VARCHAR, Types.INTEGER});
		}
		*/
		Object[] args;
		if(student.getId() == null) {
			sql = "insert into student(name, age, email, id) values(?, ?, ?,pkid.nextval)";
			args = new Object[]{student.getName(), student.getAge(), student.getEmail()};
		} else {
			sql = "update student set name = ?, age = ?, email = ? where id = ?";
			args = new Object[]{student.getName(), student.getAge(), student.getEmail(), student.getId()};
		}
		this.jdbcTemplate.update(sql, args);
	}

	
	@Override
	public List<Student> sharchByName(String studentName, int offset, int numPerPage) {
//		List<Student> studentList = new ArrayList<>();
//		Connection con = null;
//		if(studentName == null || "".endsWith(studentName.trim())){
//			studentName = "%";
//		} else {
//			studentName = "%" + studentName.trim() + "%";
//		}
//		try {
//			//2.取得Connection
//			 con = DataSourceUtils.getConnection(dataSource);
//			 String sql = 
//					 "select * from " +
//					 "(select t.*, rownum rn from " +
//					 "(select id, name, age, email from student where name like ? order by id) t "+
//					 "where rownum < ? "+
//					 ") where rn > ? ";
//
//	        //3.创建PreparedStatement
//	        PreparedStatement pst = con.prepareStatement(sql);
//	        pst.setString(1, studentName);
//	        pst.setInt(2, offset + numPerPage + 1);
//	        pst.setInt(3, offset);
//	        //4.取得ResultSet
//	        ResultSet rs = pst.executeQuery();
//	        while(rs.next()) {
//	        	Student stu = new Student(rs.getInt("id"), rs.getString("name"), rs.getInt("age"), rs.getString("email"));
//	        	studentList.add(stu);
//	        }
//        } catch (SQLException e) {
//        	throw new DaoException("sql写错误" ,e);
//        }
//		return studentList;
		String sql = 
				"select * from " +
						"(select t.*, rownum rn from " +
						"(select id, name, age, email from student where name like ? order by id) t "+
						"where rownum < ? "+
						") where rn > ? ";
		studentName = StringUtils.isBlank(studentName) ? "%" : "%" + studentName.trim() + "%";
		Object[] args = new Object[]{studentName, offset + numPerPage + 1, offset};
		return this.jdbcTemplate.query(sql, args, this.studentRowMapper);
	}

	@Override
	public void deleteById(Integer id) {
	/*	Connection con = null;
		try {
			//2.取得Connection
			 con = DataSourceUtils.getConnection(dataSource);
	        String sql = "delete from student where id = ?";
	        //3.创建PreparedStatement
	        PreparedStatement pst = con.prepareStatement(sql);
	       	pst.setInt(1, id);

	        //4.取得ResultSet
	        pst.executeUpdate();
	       
        } catch (SQLException e) {
        	throw new DaoException("sql写错误" ,e);
        }*/
		String sql = "delete from student where id = ?";
		Object[] args = {id};
		this.jdbcTemplate.update(sql, args, new int[]{Types.INTEGER});
	}

	@Override
	public int getTotalNum(String studentName) {
		/*int result = 0;
		Connection con = null;
		
		if(StringUtils.isBlank(studentName)){
			studentName = "%";
		} else {
			studentName = "%" + studentName.trim() + "%";
		}
		try {
			//2.取得Connection
			 con = DataSourceUtils.getConnection(dataSource);
			 String sql = "select count(*) from student where name like ?";

	        //3.创建PreparedStatement
	        PreparedStatement pst = con.prepareStatement(sql);
	        pst.setString(1, studentName);
	        //4.取得ResultSet
	        ResultSet rs = pst.executeQuery();
	        if(rs.next()) {
	        	result = rs.getInt(1);
	        }
        } catch (SQLException e) {
        	throw new DaoException("sql写错误" ,e);
        }
		return result;*/
		String sql = "select count(*) from student where name like ?";
		studentName = StringUtils.isBlank(studentName) ? "%" : "%" + studentName.trim() + "%";
		return this.jdbcTemplate.queryForObject(sql, Integer.class, studentName);
	}

	@Override
	public Student getById(Integer id) {
		/*Student stu = null;
		Connection con = null;
		try {
			//2.取得Connection
			 con = DataSourceUtils.getConnection(dataSource);
			 String sql = "select id, name, age, email from student where id = ?";

	        //3.创建PreparedStatement
	        PreparedStatement pst = con.prepareStatement(sql);
	        pst.setInt(1, id);
	        //4.取得ResultSet
	        ResultSet rs = pst.executeQuery();
	        if(rs.next()) {
	        	stu = new Student(rs.getInt("id"), rs.getString("name"), rs.getInt("age"), rs.getString("email"));
	        }
        } catch (SQLException e) {
        	throw new DaoException("sql写错误" ,e);
        }
		return stu;*/
		String sql = "select id, name, age, email from student where id = ?";
//		return this.jdbcTemplate.queryForObject(sql, this.studentRowMapper, id);
		//最佳实践是下边这种
		List<Student> studentList = this.jdbcTemplate.query(sql, this.studentRowMapper, id);
		return studentList.size() == 0 ? null : studentList.get(0);
	}

}