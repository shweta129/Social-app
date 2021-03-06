package com.niit.dao;

import java.util.List;

import com.niit.dto.BlogComment;
import com.niit.dto.BlogPost;
import com.niit.dto.BlogPostLikes;
import com.niit.dto.User;

public interface BlogPostDao {
	void saveBlogPost(BlogPost blogPost);
	//return  list of blogs waiting for approval(0) / list of blogs approved(1)
	//getBlogs(0) -> list of blogs waiting for approval
	//getBlogs(1) -> list of blogs approved
	List<BlogPost> getBlogs(int approved);//value= 0 or 1
	BlogPost getBlogById(int id);
	void updateBlogPost(BlogPost blogPost,String rejectionReason);
	//BlogPostLikes userLikes(BlogPost blogPost, User user);
	void addComment(BlogComment blogComment);
}
