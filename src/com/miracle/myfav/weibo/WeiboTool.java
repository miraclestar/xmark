package com.miracle.myfav.weibo;

import org.apache.log4j.Logger;

import weibo4j.Timeline;
import weibo4j.model.Status;
import weibo4j.model.WeiboException;

public class WeiboTool {

	private static Logger log = Logger.getLogger(WeiboTool.class);

	/**
	 * 发微博
	 * @param con
	 * @param token
	 * @return
	 */
	public static String sendweibo(String con, String token) {
		String res = "";
		String statuses = con;
		Timeline tm = new Timeline();
		tm.client.setToken(token);
		try {
			Status status = tm.UpdateStatus(statuses);
			log.info(status.toString());
		} catch (WeiboException e) {
			e.printStackTrace();
			log.error("send weibo error:", e);
		}
		return res;
	}
}
