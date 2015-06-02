package com.oracle.lnsd.web.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oracle.lnsd.entity.User;

/**
 * Servlet implementation class UserLoginServlet
 */
@WebServlet("/user/login")
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * 访问登陆页面jsp
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		Cookie[] cookies = request.getCookies();
//		if (cookies != null) {
//			for (Cookie cookie : cookies) {
//				if ("userName".equals(cookie.getName())) {
//					request.setAttribute("userName", cookie.getValue());
//				} else if ("password".equals(cookie.getName())) {
//					request.setAttribute("password", cookie.getValue());
//				}
//			}
//		}
		getServletContext().getRequestDispatcher("/WEB-INF/page/user/login.jsp").forward(request, response);
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
	    	User user = new User(null, "admin", "张三", null);
	    	HttpSession session = request.getSession();
	    	session.setAttribute("user", user);
	    	System.out.println("==========================================" + session.getId());
	    	//如果客户禁用了cookie则需要对url进行编码，url后边会加上jsessionid
	    	String url = response.encodeRedirectURL("http://localhost:8080/studentManage/student/add");
	    	response.sendRedirect(url);
	    } else {
	    	//登陆失败了
	    	request.setAttribute("loginFail", "用户名或密码错误");
	    	getServletContext().getRequestDispatcher("/WEB-INF/page/user/login.jsp").forward(request, response);
	    }
	}
}
