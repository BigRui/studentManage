package com.oracle.lnsd.web;


import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class CommonListener implements ServletContextListener {
	public final static String APPLICATION_ATTRIBUTE = "app";

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
		//��spring�����ŵ�servletContext��
//		ApplicationContext app = new ClassPathXmlApplicationContext("application.xml");
//		servletContext.setAttribute(APPLICATION_ATTRIBUTE, app);
	}
}