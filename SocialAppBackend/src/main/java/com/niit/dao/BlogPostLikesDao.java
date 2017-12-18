package com.niit.dao;

import com.niit.dto.BlogPost;
import com.niit.dto.BlogPostLikes;
import com.niit.dto.User;

public interface BlogPostLikesDao {
    //select * from blogpostlikes where blogpost_id=? and uer_username=?
	//if user already like the post, 1 object(blogpostlike)
	//if userhas not yet liked the post,null object
	BlogPostLikes userLikedPost(BlogPost blogPost,User user);
	
	//increment/decrement the number of likes
	//insert into blogpostlikes/delete from blogpostlikes
	BlogPost updateLikes(BlogPost blogPost,User user);
}
