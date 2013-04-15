package com.miracle.myfav.qq;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.miracle.myfav.entity.User;
import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.api.qzone.UserInfo;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.javabeans.qzone.UserInfoBean;
import com.qq.connect.oauth.Oauth;

/**
 * Servlet implementation class QQCallBack
 */
public class QQCallBack extends HttpServlet {
	private static Logger log = Logger.getLogger(QQCallBack.class);
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");

		PrintWriter out = response.getWriter();

		try {
			AccessToken accessTokenObj = (new Oauth()).getAccessTokenByRequest(request);

			String accessToken = null, openID = null;
			long tokenExpireIn = 0L;

			if (accessTokenObj.getAccessToken().equals("")) {
				// 我们的网站被CSRF攻击了或者用户取消了授权
				// 做一些数据统计工作
				System.out.println("没有获取到响应参数");
			} else {
				accessToken = accessTokenObj.getAccessToken();
				tokenExpireIn = accessTokenObj.getExpireIn();

				// 利用获取到的accessToken 去获取当前用的openid -------- start
				OpenID openIDObj = new OpenID(accessToken);
				openID = openIDObj.getUserOpenID();

				out.println("欢迎你，代号为 " + openID + " 的用户!");

				// 利用获取到的accessToken 去获取当前用户的openid --------- end

				UserInfo qzoneUserInfo = new UserInfo(accessToken, openID);
				UserInfoBean userInfoBean = qzoneUserInfo.getUserInfo();
				out.println("<br/>");
				if (userInfoBean.getRet() == 0) {
					out.println(userInfoBean.getNickname() + "<br/>");
					out.println("<image src=" + userInfoBean.getAvatar().getAvatarURL30() + "/><br/>");
				}

				request.getSession().setAttribute("qq_openid", openID);
				request.getSession().setAttribute("qq_token", accessToken);
				request.getSession().setAttribute("qq_token_expirein", String.valueOf(tokenExpireIn));

				User user = new User();
				user.setAvatar(userInfoBean.getAvatar().getAvatarURL30());
				user.setName(userInfoBean.getNickname());
				user.setUid(openID);
				user.setWhichweibo("qq");
				request.getSession().setAttribute("user", user);

				response.sendRedirect("/Home?id=" + openID);
			}
		} catch (QQConnectException e) {
			e.printStackTrace();
		}
	}
}
