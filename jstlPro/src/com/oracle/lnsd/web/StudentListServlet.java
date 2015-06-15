package com.oracle.lnsd.web;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oracle.lnsd.beans.Student;

/**
 * Servlet implementation class StudentListServlet
 */
@WebServlet("/student-list")
public class StudentListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date birthday = df.parse("1992-12-01 12:34:11");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Student> studentList = new ArrayList<>();
		studentList.add(new Student("zhangsan", 10, "f", new Date(), 123231.123));
		studentList.add(new Student("lisi", 18, "m", new Date(), 3.1));
		studentList.add(new Student("wangwu", 20, "f", new Date(),100d));
		studentList.add(new Student("zhaoliu", 30, "sdafdas", new Date(), 122.237));
		
		request.setAttribute("studentList", studentList);
		
		Map<String, Integer> map = new HashMap<>();
		map.put("first", 1);
		map.put("second", 2);
		map.put("third", 3);
		request.setAttribute("map", map);
		
		
		request.setAttribute("htmlStr", "<h1>在里在h1中<h1>");
		request.getRequestDispatcher("/WEB-INF/page/student-list.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
