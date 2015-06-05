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
		//��ServletContext�м����������url��·����������Ŀ��ʹ�õ���url��·������õ�ͳһ��
		servletContext.setAttribute("ctx", servletContext.getContextPath());
		//��log4j.properties�еı�����ֵ
		System.setProperty("webAppHome", servletContext.getRealPath("/WEB-INF/"));
	}

	@Override
    public void sessionCreated(HttpSessionEvent arg0) {
		HttpSession session = arg0.getSession();
	    System.out.println("==========session��������============" + session.getId());
	    
    }

	@Override
    public void sessionDestroyed(HttpSessionEvent arg0) {
	    HttpSession session = arg0.getSession();
	    System.out.println("===============session��������================" + session.getId());
    }

	@Override
    public void requestDestroyed(ServletRequestEvent arg0) {
	    System.out.println("һ��request��������");
    }

	@Override
    public void requestInitialized(ServletRequestEvent arg0) {
		System.out.println("һ��request��������");
	    
    }

}
