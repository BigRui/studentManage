package com.oracle.lnsd.web.student;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.oracle.lnsd.dao.DaoException;
import com.oracle.lnsd.entity.Student;
import com.oracle.lnsd.service.ServiceException;
import com.oracle.lnsd.service.StudentService;
import com.oracle.lnsd.web.CommonListener;

/**
 * Servlet implementation class AddStudentServlet
 */
//@WebServlet("/student/student-add")
public class StudentAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * ������̬Logger
	 */
	private static Logger logger =  LoggerFactory.getLogger(StudentAddServlet.class);
	
	private StudentService studentService;
	
	

	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String id_str = request.getParameter("id");
		//����ͻ���û�д���id��Ҳ������ӵ�ʱ����idֵΪnull
		Integer id = StringUtils.isBlank(id_str) ? null: Integer.valueOf(id_str);
		
		String name = request.getParameter("name");
		
		String age_str = request.getParameter("age");
		int age = Integer.parseInt(age_str);
		String email = request.getParameter("email");
		
		Student student = new Student(id, name, age, email);
		
		//һ�´�������ҳ��
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter writer = response.getWriter();
		try {
			this.studentService.saveOrUpdateStudent(student);
			response.sendRedirect(request.getServletContext().getAttribute("ctx") + "/student/student-list");
		} catch (ServiceException | DaoException e) {
			logger.error("servlet�в�����һ���쳣", e);
			
			writer.println("����ʧ��</br>");
			writer.println("<a href=\"addStudent.html\">��ת�����ҳ��</a>");
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	        throws ServletException, IOException {
		//�ͻ���������������ѧ�����򲻴���id
		//����Ǹ��£��򴫵�id��
		String id_str = req.getParameter("id");
		if (StringUtils.isNotBlank(id_str)) {
			Integer id = Integer.valueOf(id_str);
			Student student = this.studentService.getById(id);
			req.setAttribute("student", student);
			//��ҳ��֪�����ڵĲ����Ǹ���
			req.setAttribute("update-operate", true);
		} else {
			//��ҳ��֪�����ڵĲ����Ǹ���
			req.setAttribute("update-operate", false);
		}
		req.getServletContext().getRequestDispatcher("/WEB-INF/page/student/student-add.jsp").forward(req, resp);
	}
}
