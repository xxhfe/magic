package com.magicloud.entity;

import java.io.Serializable;

public class Job implements Serializable{

	private String jobId;
	private String jobName;
	private String state;
	public Job() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Job(String jobId, String jobName, String state) {
		super();
		this.jobId = jobId;
		this.jobName = jobName;
		this.state = state;
	}
	public String getJobId() {
		return jobId;
	}
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
}
