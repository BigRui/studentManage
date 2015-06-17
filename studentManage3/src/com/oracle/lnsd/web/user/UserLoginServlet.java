package com.oracle.lnsd.web.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oracle.lnsd.entity.User;
import com.oracle.lnsd.service.UserService;

/**
 * Servlet implementation class UserLoginServlet
 */
//@WebServlet("/user/login")
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserService userService;
    
	

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * 访问登陆页面jsp
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
	 * 处理登陆提交的表单
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
	    String userName = request.getParameter("userName");
	    String password = request.getParameter("password");
	    String remenberMe = request.getParameter("remenberMe"); 
	    //仔细体会一下redirect和forward的区别
	    //redirect:重定向
	    //forward：转发
	    User user = this.userService.findUserByUserName(userName);
	    //如果能够从数据库检索到指定名称的用户，并且密码还和提交的密码相同，那么就认为登陆成功
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
	    		//如果没有勾选记住用户名密码，则删掉已有的cookie
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
	    	
	    	//如果客户禁用了cookie则需要对url进行编码，url后边会加上jsessionid
	    	String url = response.encodeRedirectURL(request.getServletContext().getAttribute("ctx") + "/student/student-add");
	    	response.sendRedirect(url);
	    } else {
	    	//登陆失败了
	    	request.setAttribute("loginFail", "用户名或密码错误");
	    	request.getRequestDispatcher("/WEB-INF/page/user/login.jsp").forward(request, response);
	    }
	}
}
