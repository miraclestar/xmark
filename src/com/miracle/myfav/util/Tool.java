package com.miracle.myfav.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.log4j.Logger;

import com.miracle.myfav.base.DBPool;

public class Tool {

	private static Logger log = Logger.getLogger(Tool.class);

	public static String clientReq(String surl) throws HttpException, IOException {
		String html = "";
		HttpClient client = new HttpClient();
		HttpMethod method = new GetMethod(surl);

		client.executeMethod(method);
		// 打印服务器返回的状态
		log.info(method.getStatusLine());
		html = method.getResponseBodyAsString();

		method.releaseConnection();

		return html;
	}

	/**
	 * 获取url的title
	 * 
	 * @param surl
	 * @return
	 * @throws HttpException
	 * @throws IOException
	 */
	public static String getTitle(String html) throws HttpException, IOException {

		String ret = "";
		String pattern = "<title>(.*)</title>";
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(html);
		while (m.find()) {
			ret = m.group(1);
		}
		return ret;
	}

	public static int insertSite(String site, String title, String encode, String userid) throws UnsupportedEncodingException {
		int ret = 0;
		String titledecode = title;
		log.debug("+++  title before encode :" + title + " ,encode: " + encode);
		// if (encode != null && !encode.equals("")) {
		// titledecode = new String(title.getBytes(encode), "UTF-8");
		// }

		log.debug("++++  site:" + site);
		log.debug("++++  titledecode:" + titledecode);

		try {

			String sql = "insert into sites(sname,surl,insert_time) values('" + titledecode + "','" + site + "',now())";
			log.info("insert sites: " + sql);
			int siteid = DBPool.commonExe(sql);

			String inUsersite = "insert into user_site(user_id,site_id,category,descrip,in_time) values('" + userid + "'," + siteid
					+ ",'未归档','还没归档的网页',now());";
			log.info("insert user_site: " + inUsersite);
			DBPool.commonExe(inUsersite);

			ret = siteid;

		} catch (SQLException e) {
			e.printStackTrace();
			log.error("------------------- SQL exe error", e);
		}
		return ret;
	}

	public static void main(String[] args) throws HttpException, IOException {
		String html = clientReq("http://www.baidu.com");
		System.out.println("---------------------");
		System.out.println(getEncode(html));
	}

	public static String getEncode(String html) {
		String ret = "";
		String pattern = "charset=([^'\"]+)[\"']";
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(html);
		while (m.find()) {
			ret = m.group(1);
			log.debug("    encode: " + ret);
		}
		return ret;
	}
}
