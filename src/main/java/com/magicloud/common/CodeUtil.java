package com.magicloud.common;
/**
 *错误提示 err开头 ，信息提示msg开头
 */
public class CodeUtil {
	
	//// 返回代码
	
	/**
	 * 成功返回
	 */
	public static final String SUCCESS = "200";
	/**
	 * 系统错误
	 */
	public static final String ERROR = "500";
	///参数代码
	/**
	 *参数为空 
	 */
	public static final String PARAMETER_NULL = "100010";
	/**
	 * 上传文件为空
	 */
	public static final String UPLOAD_NULL = "200010";
	///权限代码
	/**
	 * 请移除权限下的人员
	 */
	public static final String SIZE_OVERFOLOW = "300010";
	
	/// 提示信息
	public static final String MSG_SUCCESS = "成功";
	public static final String MSG_ERROR = "失败";
	public static final String MSG_SIZE_OVERFOLOW = "请移除权限下的人员";	
	
	/**
	 * 登录信息验证失败
	 */
	public static final String MSG_LOGIN_OUT = "登录信息验证失败";
	/**
	 * 帐号未授权
	 */
	public static final String MSG_LOGIN_UNAUTHORIZED = "帐号未授权";
	/**
	 * 帐号已被冻结
	 */
	public static final String MSG_LOGIN_FROZEN = "帐号已被冻结";
	/**
	 * 帐号不存在
	 */
	public static final String MSG_USER_NULL = "帐号不存在";
	/**
	 * 密码错误
	 */
	public static final String MSG_USER_PASSWORD_NULL = "密码错误";
	/**
	 * 密码错误次数限制
	 */
	public static final int MSG_USER_PASSWORD_COUNT = 5;
	/**
	 *参数错误
	 */
	public static final String MSG_PARAMETER_NULL = "参数错误";
	/**
	 * 上传文件为空
	 */
	public static final String ERR_UPLOAD_NULL = "上传文件为空";
	
}
