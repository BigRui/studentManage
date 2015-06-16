package com.oracle.lnsd.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class CommonListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		ServletContext servletContext = arg0.getServletContext();
		//在ServletContext中加入服务器的url根路径，整个项目所使用到的url根路径将会得到统一。
		servletContext.setAttribute("ctx", servletContext.getContextPath());
		//在log4j.properties中的变量赋值
		System.setProperty("webAppHome", servletContext.getRealPath("/WEB-INF/"));
	}

}
