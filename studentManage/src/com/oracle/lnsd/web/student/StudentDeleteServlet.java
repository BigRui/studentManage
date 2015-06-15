package com.oracle.lnsd.web.student;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oracle.lnsd.service.StudentService;

/**
 * Servlet implementation class StudentDeleteServlet
 */
@WebServlet("/student/student-delete")
public class StudentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentService studentService = new StudentService();
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id_str = request.getParameter("id");
		Integer id = Integer.valueOf(id_str);
		this.studentService.deleteById(id);
		response.sendRedirect(getServletContext().getAttribute("ctx") + "/student/student-list");
	}

}
