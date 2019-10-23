package com.magicloud.controller.business.login;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.magicloud.common.CodeUtil;
import com.magicloud.common.JsonUtil;
import com.magicloud.common.Md5Util;
import com.magicloud.entity.SoupeModel;
import com.magicloud.entity.User;
import com.magicloud.service.DepartmentService;
import com.magicloud.service.UserService;
import com.magicloud.util.TokenUtil;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping(value="/login")
public class UserBusinessController {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	StringRedisTemplate redisTemplate;
	@Autowired
	UserService userService;
	@Autowired
	DepartmentService departmentService;
	
	// 登录验证
	@ResponseBody
	@ApiOperation(value="用户登录", notes="验证登录，返回token")
	@ApiImplicitParam(paramType="query", name = "userNumber", value = "用户编号", required = true, dataType = "Integer")
	@RequestMapping(value="/login",method = RequestMethod.POST)
	public String login(@RequestParam("user") String user,@RequestParam("pwd") String pwd,HttpServletRequest request,HttpServletResponse response) {
		SoupeModel spm = new SoupeModel();
		User u = userService.getUser(user);
		String md5pwd = Md5Util.MD5(pwd);
		if (u != null) {
			if("2".equals(u.getState())) {
				spm.setMessage(CodeUtil.MSG_LOGIN_FROZEN);
				spm.setStatus("err");
				return JsonUtil.toJson(spm);
			}
			// 错误次数超限，冻结账户
			if (u.getErrcount() >= CodeUtil.MSG_USER_PASSWORD_COUNT) {
				u.setErrcount(0);
				u.setState("2");
				userService.updUserForCount(u);
				spm.setMessage(CodeUtil.MSG_LOGIN_FROZEN);
				spm.setStatus("err");
				return JsonUtil.toJson(spm);
			}
			if (u.getPwd().equals(md5pwd)) {
				u.setErrcount(0);
				userService.updUserForCount(u);
				String toke = TokenUtil.createToke(user);
				String userID = new String(Base64.encodeBase64(user.getBytes()));
				userID = userID.replace("=", "$$");
				redisTemplate.opsForValue().set(userID, toke); // redis 添加token

				spm.setUserid(userID);
				spm.setMessage(toke);
				spm.setStatus("success");
			} else {
				// 记录密码错误次数
				u.setErrcount(u.getErrcount() + 1);
				userService.updUserForCount(u);
				spm.setMessage(CodeUtil.MSG_USER_PASSWORD_NULL);
				spm.setStatus("err");
			}
		}else {
			spm.setMessage(CodeUtil.MSG_USER_NULL);
			spm.setStatus("err");
		}
		return JsonUtil.toJson(spm);
	}
	
	@ResponseBody
	@ApiOperation(value = "人员新增", notes = "")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "user", value = "用户名", required = true, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "pwd", value = "密码", required = true, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "roleId", value = "角色ID", required = true, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "departmentId", value = "部门ID", required = true, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "username", value = "姓名", required = true, dataType = "String") })
	@RequestMapping(value = "/adduser", method = RequestMethod.POST)
	public String adduser(@ModelAttribute("user") User user) {
		
		SoupeModel spm = new SoupeModel();
		user.setUserId(UUID.randomUUID().toString());
		user.setPwd(Md5Util.MD5(user.getPwd()));
		user.setLevel(90);
		user.setErrcount(0);
		user.setState("1");
		try {
			userService.addUser(user);
			spm.setStatus("success");
			return JsonUtil.toJson(spm);
		} catch (Exception e) {
			// TODO: handle exception
			spm.setMessage("系统错误");
			spm.setStatus("err");
			logger.error(e.getMessage());
			return JsonUtil.toJson(spm);
		}
	}
}
