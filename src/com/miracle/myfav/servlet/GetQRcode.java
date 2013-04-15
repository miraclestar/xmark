package com.miracle.myfav.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.miracle.myfav.util.QRcodeGenerate;

/**
 * Servlet implementation class GetQRcode
 */
public class GetQRcode extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid = request.getParameter("uid");
		String imgPath = "qrcode/" + userid + ".png";
		String encoderContent = "http://studytree.cloudfoundry.com/Home?id=" + userid;
		QRcodeGenerate handler = new QRcodeGenerate();
		handler.encoderQRCode(encoderContent, imgPath, "png");
	}

}
