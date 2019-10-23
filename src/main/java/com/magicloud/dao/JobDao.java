package com.magicloud.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.magicloud.entity.Job;
@Mapper
public interface JobDao {
	public List<Job> getJobList(@Param("parameter")String parameter);
	public Job getJob(@Param("parameter")String parameter);
	public void addJob(Job job);
	public void updateJob(Job job);
}
