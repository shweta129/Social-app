package com.niit.dao;

import com.niit.dto.BlogPicPost;

import com.niit.dto.ProfilePicture;

public interface BlogPicPostDao {
	
	void saveOrUpdateBlogPicPost(BlogPicPost blogPicPost);
	BlogPicPost getBlogPicPost(int id);
}
