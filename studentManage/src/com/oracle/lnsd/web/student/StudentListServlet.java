package com.oracle.lnsd.web.student;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oracle.lnsd.entity.Student;
import com.oracle.lnsd.service.StudentService;

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
		if(studentName == null){
			List<Student> studentList = this.studentService.studentList();
			request.setAttribute("students", studentList);
		} else {
			List<Student> studentList = this.studentService.sharchByName(studentName);
			request.setAttribute("students", studentList);
		}
		getServletContext().getRequestDispatcher("/WEB-INF/page/student/student-list.jsp").forward(request, response);
	}

}
