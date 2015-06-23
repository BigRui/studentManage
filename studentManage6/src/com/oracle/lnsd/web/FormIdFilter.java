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
//		//1.判断set中有没有这个formId
//		//2.如果有，删除，返回true  如果没有：重复提交
//		if(formIdSet.remove(formId)) {
//			//第一次提交的
//			chain.doFilter(request, response);
//			return;
//		} else {
//			//重复提交，什么也不用干
//		}
		String formId = request.getParameter(FORM_ID);
		if(formId != null){
			HttpSession session = ((HttpServletRequest)request).getSession();
			HashSet<String> formIdSet = (HashSet<String>) session.getAttribute(FORM_ID_SET);
			if(!formIdSet.remove(formId)) {
				//只有这种情况才是重复提交了，不用管了
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
