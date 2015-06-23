package com.oracle.lnsd.web;

import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.oracle.lnsd.dao.DaoException;
import com.oracle.lnsd.entity.Student;
import com.oracle.lnsd.service.ServiceException;
import com.oracle.lnsd.service.StudentService;
import com.oracle.lnsd.utils.Page;

@Controller
@RequestMapping("/student")
public class StudentController {
	@Resource
	private StudentService studentService;
	/**
	 * 创建静态Logger
	 */
	private static Logger logger =  LoggerFactory.getLogger(StudentController.class);
	
	@RequestMapping(value="/student-add", method=RequestMethod.GET)
	public String toAdd(Integer id, Map<String, Object> map) {
		if(id != null) {
			Student student = this.studentService.getById(id);
			map.put("student", student);
			map.put("updateOperate", true);
		} else {
			map.put("updateOperate", false);
		}
		return "student/student-add";
	}
	

	@RequestMapping(value="/student-add", method=RequestMethod.POST)
	public String doAdd(Student student) {
		try {
			this.studentService.saveOrUpdateStudent(student);
			return "redirect:/student/student-list";
		} catch (ServiceException | DaoException e) {
			logger.error("servlet中捕获了一个异常", e);
			return "student/add-error";
		}
	}
	@RequestMapping(value="/student-delete", method=RequestMethod.POST)
	public String doDelete(Integer id){
		this.studentService.deleteById(id);
		return "redirect:/student/student-list";
	}
	@RequestMapping(value="/student-list")
	public ModelAndView listStudent(String studentName, Integer numPerPage, Integer currentPage){
		Page<Student> page;
		if(currentPage != null && numPerPage != null){
			page = this.studentService.sharchByName(studentName, currentPage, numPerPage);
		} else {
			page = this.studentService.sharchByName();
		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("student/student-list");
		mv.addObject("page", page);
		return mv;
	}
}
