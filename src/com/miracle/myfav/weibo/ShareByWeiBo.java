package com.miracle.myfav.weibo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ShareByWeiBo
 */
public class ShareByWeiBo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String token = (String) session.getAttribute("token");
		String content = "这是我收藏的一些网站，分享给大家，有需要的拿去吧，http://studytree.sinaapp.com";
		WeiboTool.sendweibo(content, token);
	}

}
