package com.oracle.lnsd.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/error")
public class ErrorServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4363912449143979659L;
	
	private static Logger logger = LoggerFactory.getLogger(ErrorServlet.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
//		logger.error("", arg1);
//		Enumeration<String> attributeNames = req.getAttributeNames();
//		while (attributeNames.hasMoreElements()) {
//			String name = attributeNames.nextElement();
//			System.out.println(name + "===" + req.getAttribute(name));
//		}
		String uri = (String) req.getAttribute("javax.servlet.forward.request_uri");
		Throwable e = (Throwable) req.getAttribute("javax.servlet.error.exception");
		
		logger.error("访问以下路径出现异常：" + uri, e);
		getServletContext().getRequestDispatcher("/WEB-INF/page/error500.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}
	
	
}
