package com.niit.dao;

import java.util.List;

import com.niit.dto.Friend;
import com.niit.dto.User;

public interface FriendDao {

	
	List<User> suggestedUsersList(String username);

	void addFriendRequest(Friend friend);
	//select * from Friend  where toId=? and status = 'P' 
	List<Friend> pendingRequests(String username);// value for toId --> loging id 

	void updatePendingRequest(Friend friend);
	
	List<User> listOfFriends(String username);//usrname is login id
}
