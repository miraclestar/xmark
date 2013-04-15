package com.miracle.myfav.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.miracle.myfav.util.EncryptUtil;
import com.miracle.myfav.util.Tool;

/**
 * Servlet implementation class ReceiveSite
 */
public class ReceiveSite extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(ReceiveSite.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		String site = request.getParameter("site");
		String title = request.getParameter("title");
		String encode = request.getParameter("encode");
		String encrypt = request.getParameter("encrypt");
		String openid = request.getParameter("oid");
		String whichweibo = request.getParameter("w");

		int ret = 0;
		title = new String(title.getBytes("ISO-8859-1"), "UTF-8");

		if (whichweibo != null && whichweibo.equals("sina")) {
			// sina微博用户
			if (encrypt != null && !encrypt.equals("-1")) {
				int userid = EncryptUtil.decrypt(encrypt);
				if (userid != -1) {
					/**
					 * 需要增加验证用户是否已经存在的逻辑
					 */
					int re = Tool.insertSite(site, title, encode, String.valueOf(userid));
					out.print(re);
					log.debug("sina : " + re);
				} else {
					out.print(" user not legal! ");
					log.debug("sina user not legal");
				}
			}
		} else if (whichweibo != null && whichweibo.equals("qq")) {
			// qq用户
			if (openid != null && !openid.equals("-1")) {
				int re = Tool.insertSite(site, title, encode, openid);
				out.print(re);
				log.debug(" qq: " + re);
			} else {
				out.print(" user not legal! ");
				log.debug("qq user not legal");
			}

		}
		out.close();
	}

}
