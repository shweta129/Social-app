package com.niit.dao;

import java.util.List;

import com.niit.dto.Friend;
import com.niit.dto.User;

public interface FriendDao {

	
	List<User> suggestedUsersList(String username);

	void addFriendRequest(Friend friend);
}
