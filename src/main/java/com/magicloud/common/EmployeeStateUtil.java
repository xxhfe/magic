package com.magicloud.common;

import java.util.HashMap;
import java.util.Map;

public class EmployeeStateUtil {
	public static Map<String,Object> EmployeeStateMap(){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("0", "初始化");
		map.put("1", "休息");
		map.put("2", "工作");
		map.put("3", "请假");
		map.put("4", "外出");
		map.put("5", "旷工");
		map.put("6", "会议");
		map.put("7", "接待");
		map.put("99", "离职");
		map.put("100", "删除");
		return map;
	}
}
