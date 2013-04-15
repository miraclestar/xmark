package com.miracle.myfav.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

/**
 * 在过滤器中实现权限控制类，用来检验用户是否有权限进入当前页面
 */
public class FilterServlet extends HttpServlet implements Filter {
	private static final long serialVersionUID = 5162189625393315379L;

	private static Logger log = Logger.getLogger(FilterServlet.class);

	public void init(FilterConfig filterConfig) throws ServletException {
	}

	/**
	 * 在过滤器中实现权限控制
	 */
	public void doFilter(ServletRequest sRequest, ServletResponse sResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) sRequest;
		HttpServletResponse response = (HttpServletResponse) sResponse;
		HttpSession session = request.getSession();

		session.setMaxInactiveInterval(7200000);
		response.setContentType("text/html;charset=UTF-8");

		String ref = request.getHeader("Referer");
		String reqUrl = request.getRequestURL().toString();
		String query = request.getQueryString();

		filterChain.doFilter(sRequest, sResponse);

	}

	public void destroy() {
	}

}