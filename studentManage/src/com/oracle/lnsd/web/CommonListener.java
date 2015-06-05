package com.oracle.lnsd.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class CommonListener implements ServletContextListener, HttpSessionListener, ServletRequestListener {

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

	@Override
    public void sessionCreated(HttpSessionEvent arg0) {
		HttpSession session = arg0.getSession();
	    System.out.println("==========session被创建了============" + session.getId());
	    
    }

	@Override
    public void sessionDestroyed(HttpSessionEvent arg0) {
	    HttpSession session = arg0.getSession();
	    System.out.println("===============session被销毁了================" + session.getId());
    }

	@Override
    public void requestDestroyed(ServletRequestEvent arg0) {
	    System.out.println("一个request被销毁了");
    }

	@Override
    public void requestInitialized(ServletRequestEvent arg0) {
		System.out.println("一个request被创建了");
	    
    }

}
