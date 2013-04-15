package com.miracle.myfav.entity;

/**
 * 自己用的user对象
 * 
 * @author hyliu
 * 
 */
public class User {

	private String name, uid, avatar, whichweibo;

	public String getWhichweibo() {
		return whichweibo;
	}

	public void setWhichweibo(String whichweibo) {
		this.whichweibo = whichweibo;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	@Override
	public String toString() {
		return name + " , " + uid + "," + avatar + "," + whichweibo;
	}

}
