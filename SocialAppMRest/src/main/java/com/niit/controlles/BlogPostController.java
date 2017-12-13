package com.niit.controlles;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.niit.dao.BlogPostDao;
import com.niit.dao.UserDao;
import com.niit.dto.BlogPost;
import com.niit.dto.ErroClazz;
import com.niit.dto.User;

@Controller
public class BlogPostController {

	@Autowired
	private BlogPostDao blogPostDao;
	
	@Autowired
	private UserDao userDao;
	
	@RequestMapping(value="/saveblog",method=RequestMethod.POST)
	public ResponseEntity<?> saveBlogPost(@RequestBody BlogPost blogPost,HttpSession session){
		String username=(String)session.getAttribute("username");
		if(username==null)
		{
			ErroClazz error=new ErroClazz(5, "Unauthorized access");
			return new ResponseEntity<ErroClazz>(error,HttpStatus.UNAUTHORIZED);
		}
		User user=userDao.getUserByUsername(username);//select * from user where username = "adam"
		blogPost.setPostedOn(new Date());
		blogPost.setPostedBy(user);//FK column postedby_username['adam']
		try{
			blogPostDao.saveBlogPost(blogPost);
			
		}catch(Exception e)
		{
		ErroClazz error= new ErroClazz(8, "Unable to insert blog Details" + e.getMessage());
		return new ResponseEntity<ErroClazz>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<BlogPost>(blogPost,HttpStatus.OK);
	}
}
