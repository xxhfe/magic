package com.magicloud.interceptor;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.magicloud.common.StringUtil;
import com.magicloud.entity.Menu;
import com.magicloud.entity.Power;
import com.magicloud.entity.Role;
import com.magicloud.entity.User;
import com.magicloud.service.MenuService;
import com.magicloud.service.PowerService;
import com.magicloud.service.RoleService;
import com.magicloud.service.UserService;

public class MyInterceptor implements HandlerInterceptor{

	@Autowired
	private	RoleService roleService;
	@Autowired
	private MenuService menuService;
	@Autowired
	private PowerService powerService;
	@Autowired
	StringRedisTemplate redisTemplate;
	@Autowired
	UserService userService;
	
    private static final ThreadLocal<Power> t_power = new ThreadLocal<Power>();
    private static final ThreadLocal<User> t_user = new ThreadLocal<User>();
	/**
	 *
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		String link = request.getServletPath();
		String key = "";
		String v = "";
		if("/index".equals(link)) {
			key = request.getParameter("key");
			v = request.getParameter("v");
			if(StringUtil.isNULL(key) || StringUtil.isNULL(v)) {
				returnHtml(response,"[900001]：参数错误！");
				return false;
			}
		}else {
			Cookie[] cookies = request.getCookies();
			if (cookies != null && cookies.length > 0) {
				for (Cookie cookie : cookies) {
					String redisV = redisTemplate.opsForValue().get(cookie.getName());
					if (!StringUtil.isNULL(redisV) && redisV.equals(cookie.getValue())) {
						key = cookie.getName();
						v = cookie.getValue();
						break;
					}
				} 
			}
		}
		
		String redisV = redisTemplate.opsForValue().get(key);
		if (!StringUtil.isNULL(redisV) && v.equals(redisV)) {
			
			// 客户端参数与redis一致
			// 获取user对象
			key = key.replace("$$", "=");
			String userID = new String(Base64.decodeBase64(key.getBytes()));
			User user = userService.getUser(userID);
			if(user != null) {
				Role role = roleService.getRole(user.getRoleId());
				if (role == null) { // 无权限
					response.sendRedirect("/login");
					returnHtml(response,"无权限！");
					return false;
				}
				if("2".equals(role.getState())) { // 被冻结的角色
					response.sendRedirect("/login");
					returnHtml(response,"帐号已被冻结！");
					return false;
				}
				user.setRoleName(role.getRoleName());
				Menu menu = menuService.getMenuByLink(link);
				if(menu!=null) {
					Power p = powerService.getPowerByMenu(user.getRoleId(), menu.getMenuId());
					if(p == null || !"1".equals(p.getPowerSel())) {
						returnHtml(response,"无权限，请与管理员联系！");
						return false;
					}else {
						p.setMenuName(menu.getMenuName());
						t_power.set(p);
					}
				}
				user.setToken(v);
				t_user.set(user);
			}else {
				response.sendRedirect("/login");
				return false;
			}
			
		}else {
			response.sendRedirect("login");
			return false;
		}
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
	
	
	private void returnHtml(HttpServletResponse response,String text) {
		response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        
        PrintWriter writer;
		try {
			writer = response.getWriter();
			writer.print("<div style='position: absolute;font-size: 1.3em;color: #ff7171;left: 2em;top: 1em;'>"+text+"</div>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		t_power.remove();
		t_user.remove();
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
	
	public static Power getLocalPower() {
		return t_power.get();
	}
	public static User getLocalUser() {
		return t_user.get();
	}
}
