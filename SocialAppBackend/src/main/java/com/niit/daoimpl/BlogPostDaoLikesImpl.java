package com.niit.daoimpl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.dao.BlogPostLikesDao;
import com.niit.dto.BlogPost;
import com.niit.dto.BlogPostLikes;
import com.niit.dto.User;
@Repository
@Transactional
public class BlogPostDaoLikesImpl implements BlogPostLikesDao {

	@Autowired
	private SessionFactory sessionFactory;
		

		public BlogPostLikes userLikedPost(BlogPost blogPost, User user) {
			Session session=sessionFactory.getCurrentSession();
			@SuppressWarnings("deprecation")
			Query query=session.createQuery("from BlogPostLikes where blogPost.id=? and user.username=?");
			 System.out.println("BlogPost id"+blogPost.getId());
			 System.out.println("Username"+blogPost.getId());
			 query.setInteger(0,blogPost.getId());
			 query.setString(1,user.getUsername());
			 BlogPostLikes blogPostLikes=(BlogPostLikes)query.uniqueResult();
			 System.out.println(blogPostLikes);
			return blogPostLikes;
		}

		public BlogPost updateLikes(BlogPost blogPost, User user) {
			Session session=sessionFactory.getCurrentSession();
			BlogPostLikes blogPostLikes=userLikedPost(blogPost, user);
			//insert and increment /delete and decrement.
			//like
			if(blogPostLikes==null){//insert into blogpostlikes,increment blogpost.likes
				BlogPostLikes insertLikes=new BlogPostLikes();
				insertLikes.setBlogPost(blogPost);//FK blogpost_id
				insertLikes.setUser(user);//FK user_username
				session.save(insertLikes);//insert into blogpostlikes
				blogPost.setLikes(blogPost.getLikes() + 1);//increment the number of likes.
				session.update(blogPost);//update blogpost set likes=.. where id=?			
			}
			else //unlike
			{
				session.delete(blogPostLikes); //delete from blogpostlikes.
				blogPost.setLikes(blogPost.getLikes() - 1); //decrement the number of likes.
				session.merge(blogPost);//update blogpost set likes...			
			}
			
			return blogPost;
		}
}