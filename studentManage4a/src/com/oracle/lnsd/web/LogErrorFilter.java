package com.oracle.lnsd.web;

import java.io.IOException;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet Filter implementation class LogErrorFilter
 */
@WebFilter(dispatcherTypes = {DispatcherType.ERROR }
					, urlPatterns = { "/*" })
public class LogErrorFilter implements Filter {
	
	private Logger logger = LoggerFactory.getLogger(LogErrorFilter.class);

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String uri = (String) request.getAttribute("javax.servlet.forward.request_uri");
		Throwable e = (Throwable) request.getAttribute("javax.servlet.error.exception");
		
		logger.error("访问以下路径出现异常：" + uri, e);
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
