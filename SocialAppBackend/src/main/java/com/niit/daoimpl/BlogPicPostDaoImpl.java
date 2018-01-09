package com.niit.daoimpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.dao.BlogPicPostDao;
import com.niit.dto.BlogPicPost;

@Repository
@Transactional
public class BlogPicPostDaoImpl implements BlogPicPostDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void saveOrUpdateBlogPicPost(BlogPicPost blogPicPost) {
		Session session=sessionFactory.getCurrentSession();
		session.saveOrUpdate(blogPicPost);// insert or update
		
	}

	public BlogPicPost getBlogPicPost(int id) {
		Session session =sessionFactory.getCurrentSession();
		BlogPicPost blogPicPost=(BlogPicPost)session.get(BlogPicPost.class, id);
		return blogPicPost;
	}

}
