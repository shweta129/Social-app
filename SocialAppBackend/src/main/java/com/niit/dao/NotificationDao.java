package com.niit.dao;

import java.util.List;

import com.niit.dto.Notification;

public interface NotificationDao {
//select * from notification where username="sandy" and viewd=o
	//list of notification not yet viewd by sandy
	List<Notification> getNotification(String username,int viewed);
}
