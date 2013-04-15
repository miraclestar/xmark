package com.miracle.myfav.dao;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.miracle.myfav.base.DBPool;
import com.miracle.myfav.entity.UserSites;

public class UserSitesDAO {

	private static Logger log = Logger.getLogger(UserSitesDAO.class);

	public static List<UserSites> querySiteByUserId(String userid) {
		List<UserSites> userSitesList = new ArrayList<UserSites>();
		Connection conn = DBPool.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT user_id, site_id, category, descrip, delflag, in_time, del_time, order_no,sname,surl,sdesc,insert_time FROM  user_site u LEFT JOIN sites s  ON u.site_id=s.id WHERE user_id=? and delflag=0";
		log.info("----------------querySiteByUserId  :  " + userid + "-----------");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				UserSites userSites = new UserSites();
				userSites.setCategory(rs.getString("category"));
				userSites.setDelflag(rs.getInt("delflag"));
				userSites.setDelTime(rs.getTimestamp("del_time"));
				userSites.setDescrip(rs.getString("descrip"));
				userSites.setInsertTime(rs.getTimestamp("insert_time"));
				userSites.setInTime(rs.getTimestamp("in_time"));
				userSites.setOrderNo(rs.getInt("order_no"));
				userSites.setSdesc(rs.getString("sdesc"));
				userSites.setSiteId(rs.getInt("site_id"));
				// userSites.setSname(new
				// String(rs.getString("sname").getBytes("ISO-8859-1"),
				// "UTF-8"));
				userSites.setSname(rs.getString("sname"));
				userSites.setSurl(rs.getString("surl"));
				userSites.setUserId(rs.getString("user_id"));
				userSitesList.add(userSites);
				log.debug(rs.getString("user_id") + " :: " + new String(rs.getString("sname").getBytes("ISO-8859-1"), "UTF-8") + ", sname: "
						+ rs.getString("sname"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("~~~~~~~~~~~~~~~ querySiteByUserId error : ", e);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} finally {
			DBPool.getInstance().close(pstmt, rs, conn);
		}
		return userSitesList;
	}

	//
	// public static void main(String[] args) {
	// List<UserSites> u = UserSitesDAO.querySiteByUserId(1);
	// for (UserSites s : u) {
	// System.out.print(s.getSname() + "::: ");
	// System.out.println(s.getSurl());
	// }
	// }

	public static int delete(String userid, String siteid) {

		int res = 0;

		Connection conn = DBPool.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "delete from user_site where user_id=? and site_id=?";
		log.info("----------------delete from user_site  :  userid:　" + userid + "----siteid:  " + siteid + "-------");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			pstmt.setInt(2, Integer.parseInt(siteid));
			res = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			log.error("~~~~~~~~~~~~~~~ delete from user_sites error : ", e);
		} finally {
			DBPool.getInstance().close(pstmt, rs, conn);
		}
		return res;

	}

}
