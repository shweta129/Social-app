package com.niit.dao;

import java.util.List;

import com.niit.dto.User;

public interface FriendDao {

	
	List<User> suggestedUsersList(String username);
}
