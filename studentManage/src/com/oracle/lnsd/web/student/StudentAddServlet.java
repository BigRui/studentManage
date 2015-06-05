package com.oracle.lnsd.web.student;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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
@WebServlet("/student/add")
public class StudentAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * ������̬Logger
	 */
	private static Logger logger = LoggerFactory.getLogger(StudentAddServlet.class);
	
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
		
		Student student = new Student(null, name, age, email);
		//һ�´�������ҳ��
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter writer = response.getWriter();
		try {
			this.studentService.addStudent(student);
			List<Student> studentList = this.studentService.studentList();
			request.setAttribute("students", studentList);
			getServletContext().getRequestDispatcher("/WEB-INF/page/student/student-list.jsp").forward(request, response);
		} catch (ServiceException | DaoException e) {
			logger.error("servlet�в�����һ���쳣", e);
			logger.info("����info�����");
			logger.warn("���漶��");
			writer.println("����ʧ��</br>");
			writer.println("<a href=\"addStudent.html\">��ת�����ҳ��</a>");
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	        throws ServletException, IOException {
	    getServletContext().getRequestDispatcher("/WEB-INF/page/student/student-add.jsp").forward(req, resp);
	}
}
