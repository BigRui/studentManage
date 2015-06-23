package com.oracle.lnsd.web;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.oracle.lnsd.entity.User;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@RequestMapping(value="/toIndex", method=RequestMethod.GET)
	public String toIndex() {
		return "index";
	}
	/**
	 * 方法参数可以使用javaee相关类，但是尽量避免
	 * @param request
	 * @param response
	 * @param session
	 * @param input
	 * @param output
	 * @param writer
	 * @return
	 */
	@RequestMapping(value="/toJee", method=RequestMethod.GET)
	public String toJee(HttpServletRequest request, HttpServletResponse response, 
			HttpSession session, 
			InputStream input, OutputStream output, 
			PrintWriter writer){
		return "success";
	}
	
	@RequestMapping(value="/toForm")
	public String toForm(@RequestParam(value="name", required=false) String name, Model model){
		model.addAttribute("name", name);
		return "success";
	}
	@RequestMapping(value="/toRest/id/{userId}/name/{name}/password/{password}")
	public String toRest(@PathVariable(value="userId") @ModelAttribute(value="userId") Integer userId,
			@PathVariable("name")  @ModelAttribute(value="name") String name, 
			@PathVariable("password")  @ModelAttribute(value="password") String password
			){
		return "success";
	}
	
	@RequestMapping(value="/toHeader")
	public String toHeader(@RequestHeader("Accept") String accept, @CookieValue("JSESSIONID") String jid){
		System.out.println("accept:" + accept);
		System.out.println("JSESSIONID:" + jid);
		return "success";
	}
	@RequestMapping(value="/toUser", method=RequestMethod.POST)
	public String toUser( 
			@RequestParam(value="birth") 
			@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date birthday,
			@Validated User user, BindingResult br){
		if(br.hasErrors()) {
			return "index";
		}
		System.out.println(user);
		System.out.println("birth=" + birthday);
		return "success";
	}
}
