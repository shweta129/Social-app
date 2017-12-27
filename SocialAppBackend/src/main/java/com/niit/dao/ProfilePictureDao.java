package com.niit.dao;

import com.niit.dto.ProfilePicture;

public interface ProfilePictureDao {

	
	void saveOrUpdateProfilePicture(ProfilePicture profilePicture);
	ProfilePicture getProfilePicture(String username);
}
