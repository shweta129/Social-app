package com.niit.dao;

import java.util.List;

import com.niit.dto.CommentNotification;
import com.niit.dto.LikeNotification;
import com.niit.dto.Notification;

public interface NotificationDao {
//select * from notification where username="sandy" and viewd=o
	//list of notification not yet viewd by sandy
	List<Notification> getNotification(String username,int viewed);
	Notification updateNotification(int notificationId);
	
	//notification for user like post
	public List<LikeNotification> getNotificationLike(String username,int viewedlike);
	LikeNotification updateNotificationLike(int notificationId);
	
	//notification for user like post
		public List<CommentNotification> getNotificationComment(String username,int viewedcomment);
		CommentNotification updateNotificationComment(int notificationId);
	
}
