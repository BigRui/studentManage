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
	 * 创建静态Logger
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
		//一下处理生成页面
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter writer = response.getWriter();
		try {
			this.studentService.addStudent(student);
			List<Student> studentList = this.studentService.studentList();
			request.setAttribute("students", studentList);
			getServletContext().getRequestDispatcher("/WEB-INF/page/student/student-list.jsp").forward(request, response);
		} catch (ServiceException | DaoException e) {
			logger.error("servlet中捕获了一个异常", e);
			logger.info("这是info级别的");
			logger.warn("警告级别");
			writer.println("保存失败</br>");
			writer.println("<a href=\"addStudent.html\">跳转到添加页面</a>");
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	        throws ServletException, IOException {
	    getServletContext().getRequestDispatcher("/WEB-INF/page/student/student-add.jsp").forward(req, resp);
	}
}
