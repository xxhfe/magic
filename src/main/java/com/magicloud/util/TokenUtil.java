package com.magicloud.util;

import java.util.UUID;

import com.magicloud.common.Md5Util;

public class TokenUtil{
	
	public static String createToke(String user) {
		return Md5Util.MD5(user+UUID.randomUUID().toString());
	}
}
