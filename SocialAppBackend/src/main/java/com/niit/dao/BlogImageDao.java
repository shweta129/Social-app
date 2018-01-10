package com.niit.dao;

import com.niit.dto.BlogImage;

public interface BlogImageDao {

	public void saveorUpdateBlogImage(BlogImage blogImage);
	public BlogImage getBlogImage(int id);	
}
