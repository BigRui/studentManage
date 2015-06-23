package com.oracle.lnsd.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;

/**
 * Servlet implementation class SpringProxyServlet
 */
public class SpringProxyFilter implements Filter {
	private static final long serialVersionUID = 1L;
	private ApplicationContext app;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.app = (ApplicationContext) filterConfig.getServletContext().getAttribute(CommonListener.APPLICATION_ATTRIBUTE);
	}


	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		String url = request.getRequestURI();
		ServletContext servletContext = req.getServletContext();
		String rootPath = servletContext.getContextPath();
			
		url = url.substring(rootPath.length()+ 1);
		HttpServlet servlet = app.getBean(url, HttpServlet.class);
		if (servlet != null) {
			//将请求交给从容器中取出的Servlet处理
			servlet.service(req, res);
		} else {
			chain.doFilter(req, res);
		}
		
	}


	@Override
	public void destroy() {
	}

}
