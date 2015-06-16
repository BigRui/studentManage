package com.oracle.lnsd.web.student;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oracle.lnsd.dao.DaoException;
import com.oracle.lnsd.entity.Student;
import com.oracle.lnsd.service.ServiceException;
import com.oracle.lnsd.service.StudentService;

/**
 * Servlet implementation class AddStudentServlet
 */
@WebServlet("/student/student-update")
public class StudentUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * ������̬Logger
	 */
	private static Logger logger =  LoggerFactory.getLogger(StudentUpdateServlet.class);
	
	private StudentService studentService = new StudentService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String name = request.getParameter("name");
		
		String age_str = request.getParameter("age");
		int age = Integer.parseInt(age_str);
		String email = request.getParameter("email");
		
		String id_str = request.getParameter("id");
		Integer id = Integer.valueOf(id_str);
		
		Student student = new Student(id, name, age, email);
		
		//һ�´�������ҳ��
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter writer = response.getWriter();
		try {
			this.studentService.updateStudent(student); 
			response.sendRedirect(getServletContext().getAttribute("ctx") + "/student/student-list");
		} catch (ServiceException | DaoException e) {
			logger.error("servlet�в�����һ���쳣", e);
			
			writer.println("����ʧ��</br>");
			writer.println("<a href=\"addStudent.html\">��ת�����ҳ��</a>");
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	        throws ServletException, IOException {
		String id_str = req.getParameter("id");
		Integer id = Integer.valueOf(id_str);
		Student student = this.studentService.getById(id);
		req.setAttribute("student", student);
	    getServletContext().getRequestDispatcher("/WEB-INF/page/student/student-update.jsp").forward(req, resp);
	}
}
