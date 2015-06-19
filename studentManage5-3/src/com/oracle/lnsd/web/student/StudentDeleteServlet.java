package com.oracle.lnsd.web.student;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.oracle.lnsd.service.StudentService;

/**
 * Servlet implementation class StudentDeleteServlet
 */
//@WebServlet("/student/student-delete")
@Component("student/student-delete")
public class StudentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource
	private StudentService studentService;
	
	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id_str = request.getParameter("id");
		Integer id = Integer.valueOf(id_str);
		this.studentService.deleteById(id);
		response.sendRedirect(request.getServletContext().getAttribute("ctx") + "/student/student-list");
	}

}