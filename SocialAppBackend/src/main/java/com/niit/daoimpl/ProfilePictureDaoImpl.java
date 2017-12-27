package com.niit.daoimpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.dao.ProfilePictureDao;
import com.niit.dto.ProfilePicture;
@Repository
@Transactional
public class ProfilePictureDaoImpl implements ProfilePictureDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	public void saveOrUpdateProfilePicture(ProfilePicture profilePicture) {
		Session session=sessionFactory.getCurrentSession();
		session.saveOrUpdate(profilePicture);//insert[username doesnt exist in the table]
		
	}

	public ProfilePicture getProfilePicture(String username) {
		Session session =sessionFactory.getCurrentSession();
		ProfilePicture profilePicture=(ProfilePicture)session.get(ProfilePicture.class, username);
		return profilePicture;
	}

}
