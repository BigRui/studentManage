package com.oracle.lnsd.web.user;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

import com.oracle.lnsd.entity.User;
import com.oracle.lnsd.service.UserService;

/**
 * Servlet implementation class UserLoginServlet
 */
//@WebServlet("/user/login")
@Component("user/login")
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource
    private UserService userService;
    
	

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * ���ʵ�½ҳ��jsp
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		Cookie[] cookies = request.getCookies();
//		if (cookies != null) {
//	        for (Cookie cookie : cookies) {
//		        if ("userName".equals(cookie.getName())) {
//			        request.setAttribute("userName", cookie.getValue());
//		        } else if ("password".equals(cookie.getName())) {
//			        request.setAttribute("password", cookie.getValue());
//		        }
//	        }
//        }
//		int i = 1/0;
		request.getServletContext().getRequestDispatcher("/WEB-INF/page/user/login.jsp").forward(request, response);
	}
	
	/**
	 * ������½�ύ�ı���
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
	    User user = this.userService.findUserByUserName(userName);
	    //����ܹ������ݿ������ָ�����Ƶ��û����������뻹���ύ��������ͬ����ô����Ϊ��½�ɹ�
	    if(user != null && password.equals(user.getPassword())) {
	    	if(null != remenberMe) {
	    		Cookie userNameCookie = new Cookie("userName", userName);
	    		userNameCookie.setMaxAge(60*60);
	    		userNameCookie.setPath(request.getServletContext().getAttribute("ctx") + "/user/");
	    		response.addCookie(userNameCookie);
	    		Cookie passwordCookie = new Cookie("password", password);
	    		passwordCookie.setMaxAge(60*60);
	    		passwordCookie.setPath(request.getServletContext().getAttribute("ctx") + "/user/");
	    		response.addCookie(passwordCookie);
	    	} else {
	    		//���û�й�ѡ��ס�û������룬��ɾ�����е�cookie
	    		Cookie userNameCookie = new Cookie("userName", userName);
	    		userNameCookie.setMaxAge(0);
	    		userNameCookie.setPath(request.getServletContext().getAttribute("ctx") + "/user/");
	    		response.addCookie(userNameCookie);
	    		Cookie passwordCookie = new Cookie("password", password);
	    		passwordCookie.setMaxAge(0);
	    		passwordCookie.setPath(request.getServletContext().getAttribute("ctx") + "/user/");
	    		response.addCookie(passwordCookie);
	    	}
	    	HttpSession session = request.getSession();
	    	
	    	session.setAttribute("user", user);
	    	
	    	//����ͻ�������cookie����Ҫ��url���б��룬url��߻����jsessionid
	    	String url = response.encodeRedirectURL(request.getServletContext().getAttribute("ctx") + "/student/student-add");
	    	response.sendRedirect(url);
	    } else {
	    	//��½ʧ����
	    	request.setAttribute("loginFail", "�û������������");
	    	request.getRequestDispatcher("/WEB-INF/page/user/login.jsp").forward(request, response);
	    }
	}
}