package com.miracle.myfav.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.miracle.myfav.dao.UserSitesDAO;
import com.miracle.myfav.entity.User;

/**
 * Servlet implementation class DeleteSite
 */
public class DeleteSite extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(DeleteSite.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		String siteid = request.getParameter("siteid");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		log.info("---------------delete site  userid: " + user + " --siteid:" + siteid);

		String message = "";

		if (null != user) {
			String userid = user.getUid();
			if (null != siteid && !siteid.equals("")) {
				int res = UserSitesDAO.delete(userid, siteid);
				if (res != 0) {
					message = "ok ^_^";
				} else {
					message = "error! you can send me a email if you want";
				}
			} else {
				message = "error... you can send me a email if you want ";
			}
		} else {
			message = "not ok +_+ ,please login first.";
		}
		out.print(message);
	}
}
