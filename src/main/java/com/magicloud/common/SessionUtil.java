package com.magicloud.common;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.codec.binary.Base64;

import com.magicloud.entity.User;

public class SessionUtil {

	public static String getCookieID(User user) {
		String userID = new String(Base64.encodeBase64(user.getUser().getBytes()));
		userID = userID.replace("=", "$$");
		return userID;
	}
}
