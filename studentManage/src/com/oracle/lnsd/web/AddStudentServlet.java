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
		
		Student student = new Student(name, age, email);
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
			
			writer.println("<table>                   ");
			writer.println("	<tr>                  ");
			writer.println("		<th>ѧ������</th> ");
			writer.println("		<th>ѧ������</th> ");
			writer.println("		<th>ѧ��email</th>");
			writer.println("	</tr>                 ");
			writer.println("	<tbody>               ");
			for (Student stu : studentList) {
				writer.println("		<tr>              ");
				writer.println("			<td>" + stu.getName() + "</td>     ");
				writer.println("			<td>" + stu.getAge() + "</td>     ");
				writer.println("			<td>" + stu.getEmail() + "</td>     ");
				writer.println("		</tr>             ");
			}
			
			writer.println("	</tbody>              ");
			writer.println("</table>                  ");
			writer.println("<a href=\"addStudent.html\">��ת�����ҳ��</a>");
		}
	}

}
