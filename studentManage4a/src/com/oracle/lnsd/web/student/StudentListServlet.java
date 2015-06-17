package com.oracle.lnsd.web.student;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.oracle.lnsd.entity.Student;
import com.oracle.lnsd.service.StudentService;
import com.oracle.lnsd.utils.Page;
import com.oracle.lnsd.web.CommonListener;

/**
 * Servlet implementation class StudentListServlet
 */
//@WebServlet("/student/student-list")
@Component("student/student-list")
public class StudentListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource
	private StudentService studentService;
	
	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ApplicationContext app = (ApplicationContext) config.getServletContext().getAttribute(CommonListener.APPLICATION_ATTRIBUTE);
		this.studentService = app.getBean("studentService", StudentService.class);
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String studentName = request.getParameter("studentName");
		String numPerPage_str = request.getParameter("numPerPage");
		String currentPage_str = request.getParameter("currentPage");
		Page<Student> page = null;
		if(StringUtils.isNotBlank(currentPage_str) && StringUtils.isNotBlank(numPerPage_str)){
			page = this.studentService.sharchByName(studentName, Integer.parseInt(currentPage_str), Integer.parseInt(numPerPage_str));
		} else {
			page = this.studentService.sharchByName();
		}
		
		request.setAttribute("page", page);
		request.getServletContext().getRequestDispatcher("/WEB-INF/page/student/student-list.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}