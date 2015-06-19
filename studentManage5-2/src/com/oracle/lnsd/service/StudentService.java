package com.oracle.lnsd.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.lnsd.dao.StudentDao;
import com.oracle.lnsd.entity.Student;
import com.oracle.lnsd.utils.Page;
/**
 * 学生数据处理的业务逻辑
 * @author Administrator
 *
 */
/**
 * 学生数据处理的业务逻辑
 * @author Administrator
 *
 */
@Service("studentService")
@Transactional()
public class StudentService {
//	@Autowired
//	@Qualifier("studentDao")
	@Resource
	private StudentDao studentDao; 
	
	public StudentDao getStudentDao() {
		return studentDao;
	}
	
	public void setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
	}

	/**
	 * 添加一个学生的业务逻辑
	 * @param student
	 * @return
	 */
	
	public void saveOrUpdateStudent(Student student){
		//如果是添加学生，则需要判断姓名是否存在，更新不用判断
		if(student.getId() == null && studentDao.isEntityExists(student.getName())) {
			throw new ServiceException("用户名已经存在了");
		} else {
			this.studentDao.saveOrUpdateEntity(student);
		}
		
//		int i = 1/0;
	}
	@Transactional(readOnly=true)
	public Page<Student> sharchByName(String studentName, int currentPage, int numPerPage) {
		//放入总的行数
		int totalMum = this.studentDao.getTotalNum(studentName);
		Page<Student> page = new Page<>(currentPage,numPerPage, totalMum);
		List<Student> list = this.studentDao.sharchByName(studentName, page.getOffset(), numPerPage);
		page.setList(list);
		return page;
	}
	@Transactional(readOnly=true)
	public Page<Student> sharchByName(){
		int currentPage = Page.DEFAULT_CURRENT_PAGE;
		int numPerPage = Page.DEFAULT_NUM_PER_PAGE;
		return this.sharchByName(null, currentPage, numPerPage);
	}
	
	/**
	 * 根据id删除一个student记录
	 * @param id
	 */
	public void deleteById(Integer id) {
		this.studentDao.deleteById(id);
	}
	@Transactional(readOnly=true)
	public Student getById(Integer id) {
		return this.studentDao.getById(id);
	}
}