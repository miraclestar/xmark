package com.miracle.myfav.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

/**
 * Servlet implementation class EncodeFilter
 */
public class EncodeFilter extends HttpServlet implements Filter {
	private static final long serialVersionUID = 1L;

	private String targetEncoding = "UTF-8";
	private FilterConfig filterConfig = null;

	public EncodeFilter() {
		super();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		request.setCharacterEncoding(this.targetEncoding);
		filterChain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		this.targetEncoding = this.filterConfig.getInitParameter("encoding");
	}

}
