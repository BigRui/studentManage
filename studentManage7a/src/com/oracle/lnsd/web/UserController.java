package com.oracle.lnsd.web;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oracle.lnsd.entity.User;
import com.oracle.lnsd.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Resource
    private UserService userService;
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String toLogin(){
		return "user/login";
	}
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String doLogin(String userName, String password,
			String remenberMe, HttpServletRequest request, 
			HttpServletResponse response, Model model) {
		
		 User user = this.userService.findUserByUserName(userName);
		 
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
		    	return "redirect:/student/student-add";
		    } else {
		    	//登陆失败了
		    	model.addAttribute("loginFail", "用户名或密码错误");
		    	return "user/login";
		    }
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/user/login";
	}
}
