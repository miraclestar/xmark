package com.miracle.myfav.weibo;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.miracle.myfav.base.DBPool;
import com.miracle.myfav.entity.User;

/**
 * Servlet implementation class Test
 */
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String sql = request.getParameter("sql");

		response.setContentType("text/html");

		PrintWriter out = response.getWriter();

		out.print("java.lang.System.getenv('VCAP_SERVICES') : " + java.lang.System.getenv("VCAP_SERVICES"));

		out.print("<br>##################################<br> ");

		out.print("<br> request encode : " + request.getCharacterEncoding() + "<br> ");
		out.print("response encode : " + response.getCharacterEncoding() + "<br> ");
		InputStream is = DBPool.class.getResourceAsStream("/jdbc.properties");
		Properties p = new Properties();
		p.load(is);
		String dbUrl = p.getProperty("cdn.url");
		String dbUser = p.getProperty("cdn.username");
		String dbPwd = p.getProperty("cdn.password");
		String driver = p.getProperty("driver");
		out.print("version 18:00,,<br><br>");

		out.print(dbUrl + "<br>");
		out.print(dbUser + "<br>");
		out.print(dbPwd + "<br>");
		out.print("sql string : " + sql);
		out.print("<br>##################################<br> ");

		// sql = "show variables like 'character_set%'; ";

		try {
			Class.forName(driver).newInstance();
			Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
			// String sql = "CREATE DATABASE myfav;";
			PreparedStatement pstmt = con.prepareStatement(sql);
			if (sql.startsWith("show")) {
				ResultSet rs = pstmt.executeQuery();

				while (rs.next()) {
					out.print(rs.getString(1) + "<br>");
					out.print(rs.getString(2) + "<br>");
				}
				/*
				 * if (rs.next()) { out.print(rs.getString(1) + "<br>");
				 * out.print(rs.getString(2) + "<br>"); } else {
				 * out.print("NO data in db!  "); }
				 */

			} else if (sql.contains("select ")) {
				ResultSet rs = pstmt.executeQuery();

				out.print("DB test  ");
				if (rs.next()) {
					out.print(rs.getString(1) + "<br>");
					out.print("there is data in db  ");
				} else {
					out.print("NO data in db!  ");
				}

				while (rs.next()) {
					out.print(rs.getInt(1));
					out.print(rs.getString(2));
					out.print(rs.getString(3));
					out.print(rs.getString(4));
					out.print(rs.getString(5));
					System.out.println(rs.getString(2));
				}

				rs.close();
			} else {
				out.print(" ExeUpdate result :" + pstmt.executeUpdate());
			}
			pstmt.close();
			con.close();

		} catch (InstantiationException e) {
			e.printStackTrace();
			out.print(e.getMessage());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			out.print(e.getMessage());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			out.print(e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
			out.print(e.getMessage());
		}

		out.print("<br><br><br><br>");

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		out.print(user);
		out.close();
	}
}
