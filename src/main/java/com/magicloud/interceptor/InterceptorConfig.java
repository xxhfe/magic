package com.magicloud.interceptor;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer{
	
	@Value("${face.tempdir}")
	private String tempdir;// 临时存放路径
	@Value("${face.dir}")
	private String dir;     // 永久保存路径
	
	@Bean
	public MyInterceptor getMyInterceptor() {
		return new MyInterceptor();
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		//registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**");
		List<String> list = new ArrayList<String>();
		list.add("/login");
		list.add("/login/login");
		list.add("/css/**");
		list.add("/js/**");
		list.add("/image/**");
		///  swagger 全过
		list.add("/swagger-ui.html");
		list.add("/images/**");
		list.add("/webjars/**");
		list.add("/swagger-resources");
		list.add("/v2/api-docs");
		list.add("/configuration/**");
		registry.addInterceptor(getMyInterceptor()).excludePathPatterns(list);
	}
	
	// 资源映射
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO Auto-generated method stub
		String rootDir = System.getProperty("user.dir");/// 项目同级目录
		
		registry.addResourceHandler("/image/temp/**").addResourceLocations("file:"+rootDir+tempdir+new SimpleDateFormat("d").format(new Date())+"/");
		registry.addResourceHandler("/image/upload/**").addResourceLocations("file:"+rootDir+dir);
	}
}
