package com.oracle.lnsd.web.student;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oracle.lnsd.entity.Student;
import com.oracle.lnsd.service.StudentService;

/**
 * Servlet implementation class AddStudentServlet
 */
@WebServlet("/student/add")
public class StudentAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
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
		boolean isSuccess = this.studentService.addStudent(student);
		
		//һ�´�������ҳ��
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter writer = response.getWriter();
		if(!isSuccess) {
			writer.println("����ʧ��</br>");
			writer.println("<a href=\"addStudent.html\">��ת�����ҳ��</a>");
		} else {
			List<Student> studentList = this.studentService.studentList();
			request.setAttribute("students", studentList);
//			ServletContext servletContext = getServletContext();
//			RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/WEB-INF/page/student/student-list.jsp");
//			requestDispatcher.forward(request, response);
			
//			writer.println("ѧ��һ��");
			getServletContext().getRequestDispatcher("/WEB-INF/page/student/student-list.jsp").forward(request, response);
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	        throws ServletException, IOException {
	    getServletContext().getRequestDispatcher("/WEB-INF/page/student/student-add.jsp").forward(req, resp);
	}
}
