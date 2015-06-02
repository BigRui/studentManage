package com.oracle.lnsd.web.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserLoginServlet
 */
@WebServlet("/user/login")
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * ���ʵ�½ҳ��jsp
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
	        if("userName".equals(cookie.getName())) {
	        	request.setAttribute("userName", cookie.getValue());
	        }else if("password".equals(cookie.getName())) {
	        	request.setAttribute("password", cookie.getValue());
	        }
        }
		getServletContext().getRequestDispatcher("/WEB-INF/page/user/login.jsp").forward(request, response);
	}
	
	/**
	 * �����½�ύ�ı�
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
	    String userName = request.getParameter("userName");
	    String password = request.getParameter("password");
	    String remenberMe = request.getParameter("remenberMe"); 
	    //��ϸ���һ��redirect��forward������
	    //redirect:�ض���
	    //forward��ת��
	    if("admin".equals(userName) && "123".equals(password)) {
	    	if(null != remenberMe) {
	    		Cookie userNameCookie = new Cookie("userName", userName);
	    		userNameCookie.setMaxAge(60);
	    		userNameCookie.setPath("user");
	    		response.addCookie(userNameCookie);
	    		Cookie passwordCookie = new Cookie("password", password);
	    		passwordCookie.setMaxAge(60);
	    		passwordCookie.setPath("user");
	    		response.addCookie(passwordCookie);
	    	}
	    	response.sendRedirect("http://localhost:8080/studentManage/student/add");
	    } else {
	    	//��½ʧ����
	    	request.setAttribute("loginFail", "�û������������");
	    	getServletContext().getRequestDispatcher("/WEB-INF/page/user/login.jsp").forward(request, response);
	    }
	}
}
