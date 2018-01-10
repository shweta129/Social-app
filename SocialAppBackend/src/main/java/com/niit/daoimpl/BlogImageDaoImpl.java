package com.niit.daoimpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.dao.BlogImageDao;
import com.niit.dto.BlogImage;
@Repository
@Transactional
public class BlogImageDaoImpl implements BlogImageDao {

	@Autowired
	private SessionFactory sessionFac;
	
	public void saveorUpdateBlogImage(BlogImage blogImage) {
		Session session=sessionFac.getCurrentSession();
		session.saveOrUpdate(blogImage);  //insert or update.

	}

	public BlogImage getBlogImage(int id) {
		Session session=sessionFac.getCurrentSession();
		BlogImage blogImage=(BlogImage)session.get(BlogImage.class,id);		
		return blogImage;
		
		
	}

}

