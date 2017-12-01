package com.niit.controlles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.niit.dao.UserDao;
import com.niit.dto.ErroClazz;
import com.niit.dto.User;

@Controller
public class UserController {

	@Autowired
	private UserDao userDao;
	
	public UserController(){
	System.out.println("User controller is instantiated");
	}
	//client-angular Js Client
	//User-in json
	//convert JSON to java object
	//? any type for success type is user,for error type is errorClazz
	
	@RequestMapping(value="/registeruser",method=RequestMethod.POST)
	public ResponseEntity<?> registerUser(@RequestBody User user){
	try{
		if(!userDao.isUsernameValid(user.getUsername())){ //not allow duplicate username
			ErroClazz error = new ErroClazz(2, "Username already exists..Please choose different username");
			return new ResponseEntity<ErroClazz>(error,HttpStatus.CONFLICT);//conflict 200-299status
			
		}
		if(!userDao.isEmailValid(user.getEmail())){
			ErroClazz error = new ErroClazz(3, "EmailId already exists..Please choose different email address");
			return new ResponseEntity<ErroClazz>(error,HttpStatus.CONFLICT);//conflict 200-299status
		}
		userDao.registerUser(user);
	}
	catch(Exception e)
	{
		ErroClazz error = new ErroClazz(1, "Unable to register user details");
		return new ResponseEntity<ErroClazz>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		//failure -response.data=error,response.status=500
	}
	return new ResponseEntity<User>(user,HttpStatus.OK);
}
}
