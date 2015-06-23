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
		String sql = "select count(*) from student s where s.name = ? ";
		//只找你能看得懂的：有sql，有参数Object[], ...;返回类型推断
		return this.jdbcTemplate.queryForObject(sql, Integer.class, name) != 0;
	}

	@Override
	public void saveOrUpdateEntity(Student student) {
		String sql;

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
		String sql = "delete from student where id = ?";
		Object[] args = {id};
		this.jdbcTemplate.update(sql, args, new int[]{Types.INTEGER});
	}

	@Override
	public int getTotalNum(String studentName) {
		String sql = "select count(*) from student where name like ?";
		studentName = StringUtils.isBlank(studentName) ? "%" : "%" + studentName.trim() + "%";
		return this.jdbcTemplate.queryForObject(sql, Integer.class, studentName);
	}

	@Override
	public Student getById(Integer id) {
		String sql = "select id, name, age, email from student where id = ?";
//		return this.jdbcTemplate.queryForObject(sql, this.studentRowMapper, id);
		//最佳实践是下边这种
		List<Student> studentList = this.jdbcTemplate.query(sql, this.studentRowMapper, id);
		return studentList.size() == 0 ? null : studentList.get(0);
	}

}