package com.niit.controlles;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.niit.dao.ProfilePictureDao;
import com.niit.dao.UserDao;
import com.niit.dto.ErroClazz;
import com.niit.dto.ProfilePicture;

@Controller
public class ProfilePictureController {

	@Autowired
	private ProfilePictureDao profilePictureDao;
	
	
	
	@RequestMapping(value="/updateprofilepic",method=RequestMethod.POST)
	public ResponseEntity<?> uploadProfilePicture(@RequestParam CommonsMultipartFile image,HttpSession session){
		String username =(String)session.getAttribute("username");
		if(username==null) {
			ErroClazz error = new ErroClazz(5, "Unauthorized Access");
			return new ResponseEntity<ErroClazz>(error, HttpStatus.UNAUTHORIZED);  //401
		
	}
	
		ProfilePicture profilePicture=new ProfilePicture();
		profilePicture.setImage(image.getBytes());
		profilePicture.setUsername(username);
		profilePictureDao.saveOrUpdateProfilePicture(profilePicture);
		return new ResponseEntity<ProfilePicture>(profilePicture,HttpStatus.OK);
}
}
