package com.niit.controlles;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.niit.dao.NotificationDao;
import com.niit.dto.CommentNotification;
import com.niit.dto.ErroClazz;
import com.niit.dto.LikeNotification;
import com.niit.dto.Notification;

@Controller
public class NotificationController {

	@Autowired NotificationDao notificationDao;
	
	@RequestMapping(value = "/getnotification/{viewed}")
	public ResponseEntity<?> getNotification(@PathVariable int viewed,HttpSession session){
		String username =(String)session.getAttribute("username");
		if(username==null) {
			ErroClazz error = new ErroClazz(5, "Unauthorized Access");
			return new ResponseEntity<ErroClazz>(error, HttpStatus.UNAUTHORIZED);  //401
		}
		List<Notification> notifications=notificationDao.getNotification(username, viewed);
		return new ResponseEntity<List<Notification>>(notifications,HttpStatus.OK);
	}
	
	
	@RequestMapping(value ="/updatenotification/{notificationId}",method=RequestMethod.PUT)
	public ResponseEntity<?> updateNotification(@PathVariable int notificationId,HttpSession session)
	{
		String username =(String)session.getAttribute("username");
		if(username==null) {
			ErroClazz error = new ErroClazz(5, "Unauthorized Access");
			return new ResponseEntity<ErroClazz>(error, HttpStatus.UNAUTHORIZED);  //401
		}
		Notification notification=notificationDao.updateNotification(notificationId);
		return new ResponseEntity<Notification>(notification,HttpStatus.OK);
	}
	
	//request mapping for like notification 
	
	
	@RequestMapping(value = "/getnotificationlike/{viewedlike}")
	public ResponseEntity<?> getNotificationLike(@PathVariable int viewedlike,HttpSession session){
		String username =(String)session.getAttribute("username");
		if(username==null) {
			ErroClazz error = new ErroClazz(5, "Unauthorized Access");
			return new ResponseEntity<ErroClazz>(error, HttpStatus.UNAUTHORIZED);  //401
		}
		List<LikeNotification> likeNotifications=notificationDao.getNotificationLike(username, viewedlike);
		return new ResponseEntity<List<LikeNotification>>(likeNotifications,HttpStatus.OK);
	}
	
	
	@RequestMapping(value ="/updatenotificationlike/{notificationId}",method=RequestMethod.PUT)
	public ResponseEntity<?> updateNotificationlike(@PathVariable int notificationId,HttpSession session)
	{
		String username =(String)session.getAttribute("username");
		if(username==null) {
			ErroClazz error = new ErroClazz(5, "Unauthorized Access");
			return new ResponseEntity<ErroClazz>(error, HttpStatus.UNAUTHORIZED);  //401
		}
		LikeNotification likeNotification=notificationDao.updateNotificationLike(notificationId);
		return new ResponseEntity<LikeNotification>(likeNotification,HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/getnotificationcomment/{viewedcomment}",method=RequestMethod.GET)
	public ResponseEntity<?> getNotificationComment(@PathVariable int viewedcomment,HttpSession session)
	{
		String username = (String) session.getAttribute("username");
		if (username == null)
		{
			ErroClazz error = new ErroClazz(5, "Unauthorized Access");
			return new ResponseEntity<ErroClazz>(error, HttpStatus.UNAUTHORIZED);  //401
		}
		
		List<CommentNotification> commentnotify=notificationDao.getNotificationComment(username,viewedcomment);
		return new ResponseEntity<List<CommentNotification>>(commentnotify,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/updatenotificationcomment/{notificationId}",method=RequestMethod.PUT)
	public ResponseEntity<?> updateNotificationComment(@PathVariable int notificationId,HttpSession session)
	{
		String username = (String) session.getAttribute("username");
		if (username == null)
		{
			ErroClazz error = new ErroClazz(5, "Unauthorized Access");
			return new ResponseEntity<ErroClazz>(error, HttpStatus.UNAUTHORIZED);  //401
		}
		
		CommentNotification commentnotify=notificationDao.updateNotificationComment(notificationId);
		return new ResponseEntity<CommentNotification>(commentnotify,HttpStatus.OK);
		
	}
	
	
	
	
	
	
	
}
