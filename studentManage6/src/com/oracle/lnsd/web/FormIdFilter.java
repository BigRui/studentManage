package com.oracle.lnsd.web;

import java.io.IOException;
import java.util.HashSet;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class FormIdFilter
 */
//@WebFilter(dispatcherTypes = {DispatcherType.REQUEST }
//					, urlPatterns = { "/*" })
public class FormIdFilter implements Filter {
	private final static String FORM_ID = "formId";
	private final static String FORM_ID_SET = "formIdSet";

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//		String formId = request.getParameter("formId");
//		if(formId == null){
//			chain.doFilter(request, response);
//			return;
//		}
//		HttpSession session = ((HttpServletRequest)request).getSession();
//		HashSet<String> formIdSet = (HashSet<String>) session.getAttribute("formIdSet");
//		//1.�ж�set����û�����formId
//		//2.����У�ɾ��������true  ���û�У��ظ��ύ
//		if(formIdSet.remove(formId)) {
//			//��һ���ύ��
//			chain.doFilter(request, response);
//			return;
//		} else {
//			//�ظ��ύ��ʲôҲ���ø�
//		}
		String formId = request.getParameter(FORM_ID);
		if(formId != null){
			HttpSession session = ((HttpServletRequest)request).getSession();
			HashSet<String> formIdSet = (HashSet<String>) session.getAttribute(FORM_ID_SET);
			if(!formIdSet.remove(formId)) {
				//ֻ��������������ظ��ύ�ˣ����ù���
				return;
			}
		}
		
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
