package com.niit.daoimpl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.niit.dao.BlogPostLikesDao;
import com.niit.dto.BlogPost;
import com.niit.dto.BlogPostLikes;
import com.niit.dto.User;

public class BlogPostDaoLikesImpl implements BlogPostLikesDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public BlogPostLikes userLikes(BlogPost blogPost, User user) {
		Session session = sessionFactory.getCurrentSession();
		//select * from blogPostLikes where blogbops_id=? and user_username=?
		Query query=session.createQuery("from BloPostLikes where blogPost.id=? and user.username=?");
		query.setInteger(0, blogPost.getId());
		query.setString(1, user.getUsername());
		BlogPostLikes blogPostLikes=(BlogPostLikes)query.uniqueResult();
		return blogPostLikes;
	}

	public BlogPost updateLikes(BlogPost blogPost, User user) {
		Session session = sessionFactory.getCurrentSession();
		BlogPostLikes blogPostLikes=userLikes(blogPost, user);
		//insert and increment / delete and decrement
		//likes
		if(blogPostLikes==null){  //inset into blogpostlikes, increment blogpost.likes
			BlogPostLikes insertLikes= new BlogPostLikes();
			insertLikes.setBlogpost(blogPost);//FK blogpost_id
			insertLikes.setUser(user);//FK user_username
			session.save(insertLikes);//insert into blogpostlikess
			blogPost.setLikes(blogPost.getLikes() + 1); //increment the number of likes
			session.update(blogPost);//update blogpost set likes=.. where id=?
		}
		else{//unlike
			session.delete(blogPostLikes);//delete from blogpostlikes
			blogPost.setLikes(blogPost.getLikes() - 1);//decrement the nuber of likes
			session.update(blogPost);//update blogpost set likes....
			
		}
		
		return blogPost;
	}

}
