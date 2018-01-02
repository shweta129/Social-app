package com.niit.controlles;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.niit.dao.FriendDao;
import com.niit.dto.ErroClazz;
import com.niit.dto.User;

@Controller
public class FriendController {

	@Autowired
	private FriendDao friendDao;
	
	@RequestMapping (value="/suggestedusers",method=RequestMethod.GET)
	public  ResponseEntity<?> getSuggestedUsers(HttpSession session){
		String username=(String)session.getAttribute("username");
		if(username==null){
			ErroClazz error = new ErroClazz(5, "Unauthorized access");
		    return new ResponseEntity<ErroClazz>(error,HttpStatus.UNAUTHORIZED);
	
	}
		//String username = "ayush";
		List<User> suggestedUsers=friendDao.suggestedUsersList(username);
		return new ResponseEntity<List<User>>(suggestedUsers,HttpStatus.OK);
		
}
}
