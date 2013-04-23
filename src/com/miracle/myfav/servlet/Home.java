package com.miracle.myfav.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.miracle.myfav.dao.UserSitesDAO;
import com.miracle.myfav.entity.UserSites;

public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(Home.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String uid = request.getParameter("id");

		// Cookie cookie = new Cookie("userid", uid);
		// cookie.setMaxAge(360000);
		// cookie.setPath("/");
		// response.addCookie(cookie);
		request.getSession().setMaxInactiveInterval(60 * 60 * 24 * 2);

		List<UserSites> uslist = new ArrayList<UserSites>();
		if (uid != null && !uid.equals("")) {
			uslist = UserSitesDAO.querySiteByUserId(uid);
		}
		request.setAttribute("sites", uslist);

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("jsp/home.jsp?uid=" + uid);
		requestDispatcher.forward(request, response);

	}

}
