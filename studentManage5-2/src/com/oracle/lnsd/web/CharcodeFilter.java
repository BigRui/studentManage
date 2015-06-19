package com.oracle.lnsd.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
/**
 * 中文乱码的彻底解决方案。
 * @author Administrator
 *
 */
public class CharcodeFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
	        FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		if(request.getMethod().equalsIgnoreCase("get")) {
			request = new EncodingRequest(request);
		} else {
			request.setCharacterEncoding("utf-8");
		}
		
		response.setCharacterEncoding("utf-8");
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
	
	/**
	 * 包装器的使用
	 * @author Administrator
	 *
	 */
	private static class EncodingRequest extends HttpServletRequestWrapper{
		public EncodingRequest(HttpServletRequest request) {
	        super(request);
        }
		@Override
		public String getParameter(String name) {
		    
		    String param =  super.getParameter(name);
		    
		    if (param != null) {
				try {
					param = new String(param.getBytes("iso-8859-1"), "utf-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
			return param;
		}
		@Override
		public String[] getParameterValues(String name) {
		    String[] params =  super.getParameterValues(name);
		    if (params != null) {
				try {
					for (int i = 0; i < params.length; i++) {
						params[i] = new String(
								params[i].getBytes("iso-8859-1"), "utf-8");
					}
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
			return params;
		}
	}
}
