package com.miracle.myfav.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.miracle.myfav.entity.User;
import com.miracle.myfav.util.Tool;

public class AddSite extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(AddSite.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		String surl = request.getParameter("surl");
		String stitle = request.getParameter("stitle");
		log.info("--------------- add site url: " + surl + "  user: " + user);
		String html = Tool.clientReq(surl);
		String title = Tool.getTitle(html);
		String encode = Tool.getEncode(html);

		if (stitle != null && !stitle.equals("")) {
			title = stitle;
		}

		int result = Tool.insertSite(surl, title, encode, user.getUid());
		out.print(result);

		out.flush();
		out.close();
	}
}
