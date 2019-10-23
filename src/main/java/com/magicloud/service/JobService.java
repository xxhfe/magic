package com.magicloud.service;

import java.util.List;

import com.magicloud.entity.Job;

public interface JobService {

	/**
	 * 返回job list，参数自定义
	 * @param parameter
	 * @return
	 */
	public List<Job> getJobList(String parameter);
	/**
	 * 返回job实体，参数自定义
	 * @param parameter
	 * @return
	 */
	public Job getJob(String parameter);
	public void addJob(Job job);
	public void updateJob(Job job);
}
