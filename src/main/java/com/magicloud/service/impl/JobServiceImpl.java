package com.magicloud.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magicloud.dao.JobDao;
import com.magicloud.entity.Job;
import com.magicloud.service.JobService;

@Service
public class JobServiceImpl implements JobService{

	@Autowired
	JobDao jobDao;
	@Override
	public List<Job> getJobList(String parameter) {
		// TODO Auto-generated method stub
		return jobDao.getJobList(parameter);
	}
	@Override
	public void addJob(Job job) {
		// TODO Auto-generated method stub
		jobDao.addJob(job);
	}
	@Override
	public Job getJob(String parameter) {
		// TODO Auto-generated method stub
		return jobDao.getJob(parameter);
	}
	@Override
	public void updateJob(Job job) {
		// TODO Auto-generated method stub
		jobDao.updateJob(job);
	}
}
