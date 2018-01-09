package com.niit.controlles;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.niit.dao.BlogPicPostDao;
import com.niit.dto.BlogPicPost;
import com.niit.dto.ErroClazz;

@Controller
public class BlogPicPostController {

	@Autowired
	private BlogPicPostDao blogPicPostDao;
	
	@RequestMapping(value="/uploadblogepic/{id}",method=RequestMethod.POST)
	public ResponseEntity<?> uploadBlogPicture(@PathVariable int id, @RequestParam CommonsMultipartFile image,HttpSession session){
		String username =(String)session.getAttribute("username");
		if(username==null) {
			ErroClazz error = new ErroClazz(5, "Unauthorized Access");
			return new ResponseEntity<ErroClazz>(error, HttpStatus.UNAUTHORIZED);  //401
		
	}
	    BlogPicPost blogPicPost = new BlogPicPost();
        blogPicPost.setImage(image.getBytes());
		blogPicPost.setId(id);
		blogPicPostDao.saveOrUpdateBlogPicPost(blogPicPost);
		return new ResponseEntity<BlogPicPost>(blogPicPost,HttpStatus.OK);
}
	//http://localhost:8080/SocialAppMRest/getimage/sandy
	@RequestMapping(value = "/getblogimage/{id}",method=RequestMethod.GET)
	public @ResponseBody byte[] getBlogPicture(@PathVariable int id,HttpSession session){
		String loginId =(String)session.getAttribute("username");
		if(loginId==null) {
			return null;
		}
		BlogPicPost blogPicPost= blogPicPostDao.getBlogPicPost(id);
		if(blogPicPost==null)
			return null;
		else{
			return blogPicPost.getImage();
		}
	}
	
	
	
}
