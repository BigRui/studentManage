package com.oracle.lnsd.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oracle.lnsd.entity.User;

public class LoginFilter implements Filter {
	private String[] ignore;
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
	        FilterChain chain) throws IOException, ServletException {
		HttpServletRequest hrequest = (HttpServletRequest) request;
		HttpServletResponse hresponse = (HttpServletResponse) response;
		
		HttpSession session = hrequest.getSession();
		User user = (User) session.getAttribute("user");
		String url = hrequest.getRequestURI();
		
		//如果你访问的不是login页面，并且还没登陆，则跳转到login页面。
		if(isIgnore(url) || user != null){
			chain.doFilter(request, response);
		} else {
			String ctx = (String) hrequest.getServletContext().getAttribute("ctx");
			hresponse.sendRedirect(ctx + "/user/login");
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		ignore = arg0.getInitParameter("ignore").split(",");
		for (int i = 0; i < ignore.length; i++) {
			ignore[i] = ignore[i].trim();
		}
	}
	/**
	 * 验证当前访问的url是否放开。
	 * @param uri
	 * @return
	 */
	private boolean isIgnore(String uri) {
		boolean result = false;
		for (int i = 0; i < this.ignore.length; i++) {
			if(uri.contains(ignore[i])) {
				result = true;
				break;
			}
		}
		return result;
	}
}