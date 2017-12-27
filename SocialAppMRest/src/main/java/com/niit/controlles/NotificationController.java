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
import com.niit.dto.ErroClazz;
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
}
