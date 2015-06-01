package com.oracle.lnsd.web;

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
@WebServlet("/addStudent")
public class AddStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private StudentService studentService = new StudentService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		name = new String(name.getBytes("iso-8859-1"), "utf-8");
		
		String age_str = request.getParameter("age");
		int age = Integer.parseInt(age_str);
		String email = request.getParameter("email");
		
		Student student = new Student(null, name, age, email);
		boolean isSuccess = this.studentService.addStudent(student);
		
		//一下处理生成页面
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter writer = response.getWriter();
		if(!isSuccess) {
			writer.println("保存失败</br>");
			writer.println("<a href=\"addStudent.html\">跳转到添加页面</a>");
		} else {
			List<Student> studentList = this.studentService.studentList();
			request.setAttribute("students", studentList);
//			ServletContext servletContext = getServletContext();
//			RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/WEB-INF/page/student/student-list.jsp");
//			requestDispatcher.forward(request, response);
			
//			getServletContext().getRequestDispatcher("/dajiangyou").include(request, response);
			
//			writer.println("学生一览");
			getServletContext().getRequestDispatcher("/WEB-INF/page/student/student-list.jsp").forward(request, response);
		}
	}

}