package com.magicloud.common;

public class StringUtil {

	public static boolean isNULL(String str) {
		if(str == null || str.trim().isEmpty()) {
			return true;
		}
		return false;
	}

	public static boolean isNumericZidai(String str) {
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}
}
