package com.magicloud.common;

import java.util.HashMap;
import java.util.Map;

public class StateUtil {

	public static Map<String,Object> StateMap(){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("0", "已删除");
		map.put("1", "正常");
		map.put("2", "冻结");
		return map;
	}
}
