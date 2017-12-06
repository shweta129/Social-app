package com.niit.controlles;

import javax.servlet.http.HttpSession;

import org.hibernate.Session;
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
	
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ResponseEntity<?> login(@RequestBody User user,HttpSession session){
		User validUser = userDao.login(user);
		if(validUser==null)
		{
			ErroClazz erroClazz = new ErroClazz(4, "Invalid username/password");
			return new ResponseEntity<ErroClazz>(erroClazz,HttpStatus.UNAUTHORIZED);//ErrorClazz,401
		}
		else{
			validUser.setOnline(true);
			session.setAttribute("username",validUser.getUsername());
			userDao.updateUser(validUser);
			return new ResponseEntity<User>(validUser,HttpStatus.OK);
			
		}
	}
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public ResponseEntity<?> logout(HttpSession session){
		String username = (String)session.getAttribute("username");
		if(username==null){//without login
			ErroClazz error=new ErroClazz(5, "Unauthoried access");
			return new ResponseEntity<ErroClazz>(error,HttpStatus.UNAUTHORIZED);
		}
		User user = userDao.getUserByUsername(username);//sekect * from user where username=?
		user.setOnline(false);
		userDao.updateUser(user);//update user set online = false where username=?
		session.removeAttribute("username");
		session.invalidate();
		
		return new ResponseEntity<Void>(HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/getuser",method=RequestMethod.GET)
	public ResponseEntity<?> getUser(HttpSession session)
	{
		String username=(String)session.getAttribute("username");
		if(username==null){//not logged in
			ErroClazz error=new ErroClazz(5, "Unauthorized access");
			return new ResponseEntity<ErroClazz>(error,HttpStatus.UNAUTHORIZED);
		}
		User user=userDao.getUserByUsername(username);//select from User where username=?
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/edituserprofile",method=RequestMethod.PUT)
	public ResponseEntity<?> editUserProfile(@RequestBody User user,HttpSession session){
		String username=(String)session.getAttribute("username");
		if(username==null){//not logged in
			ErroClazz error=new ErroClazz(5, "Unauthorized access");
			return new ResponseEntity<ErroClazz>(error,HttpStatus.UNAUTHORIZED);
		}
		try{
			userDao.updateUser(user);
		}
		catch(Exception e){
			ErroClazz error=new ErroClazz(6, e.getMessage());
			return new ResponseEntity<ErroClazz>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
}
