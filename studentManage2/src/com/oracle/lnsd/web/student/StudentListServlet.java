package com.oracle.lnsd.web.student;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.oracle.lnsd.entity.Student;
import com.oracle.lnsd.service.StudentService;
import com.oracle.lnsd.utils.Page;

/**
 * Servlet implementation class StudentListServlet
 */
@WebServlet("/student/student-list")
public class StudentListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private StudentService studentService = new StudentService();
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		getServletContext().getRequestDispatcher("/WEB-INF/page/student/student-list.jsp").forward(request, response);
	}

}
