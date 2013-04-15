package com.miracle.myfav.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.apache.log4j.Logger;

public class EncryptUtil {
	private static Logger log = Logger.getLogger(EncryptUtil.class);

	/**
	 * 简单加密，把uid转成16进制，然后随即产生一个32位字符串，把16进制的数放在随即位置上
	 * 
	 * @param uid
	 * @return
	 */
	public static String encrypt(int uid) {
		String ret = "";
		ret = Integer.toHexString(uid);

		int len = ret.length();
		char[] base = "ghijklmnopqrstuvwxyz".toCharArray();
		char[] encry = new char[32];
		int[] address = new int[len];
		Set<Integer> set = new HashSet<Integer>();

		for (int i = 0; i < 32; i++) {
			int n = new Random().nextInt(base.length - 1);
			encry[i] = base[n];
		}
		for (int i = 0; set.size() < len; i++) {
			int n = new Random().nextInt(32 - len);
			set.add(n);
		}
		int in = 0;
		for (int i : set) {
			address[in] = i;
			in++;
		}

		Arrays.sort(address);
		for (int i = 0; i < len; i++) {
			encry[address[i]] = ret.charAt(i);
		}
		ret = new String(encry);
		return ret;
	}

	/**
	 * 解密：去掉g到z的字母，然后转换成10进制
	 * 
	 * @param code
	 * @return
	 */
	public static int decrypt(String code) {
		int ret = -1;
		try {
			String base = "ghijklmnopqrstuvwxyz";

			for (int i = 0; i < base.length(); i++) {
				code = code.replace(String.valueOf(base.charAt(i)), "");
			}
			ret = Integer.parseInt(code, 16);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("··········解密失败", e);
		}
		return ret;
	}

	public static void main(String[] args) {
		String en = EncryptUtil.encrypt(121233);
		System.out.println(en);
		System.out.println(decrypt("uv6i9vuh65fle6tqwiol4osijkphyqpi"));
	}
}
