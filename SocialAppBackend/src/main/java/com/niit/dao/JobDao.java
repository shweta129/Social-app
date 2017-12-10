package com.niit.dao;

import java.util.List;

import com.niit.dto.Job;

public interface JobDao {

	public void saveJob(Job job);
	
	List<Job> getAllJobs();

	public Job getJob(int jobId);
	
}
