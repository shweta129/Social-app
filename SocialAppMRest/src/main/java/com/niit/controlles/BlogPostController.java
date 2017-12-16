package com.niit.controlles;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

	@RequestMapping(value = "/saveblog", method = RequestMethod.POST)
	public ResponseEntity<?> saveBlogPost(HttpSession session, @RequestBody BlogPost blogPost) {

		String username = (String) session.getAttribute("username");
		if (username == null) {
			ErroClazz error = new ErroClazz(5, "Unauthorized access");
			return new ResponseEntity<ErroClazz>(error, HttpStatus.UNAUTHORIZED);// 401
		}
		/* String username="ayush"; */
		User user = userDao.getUserByUsername(username);// select * from user
														// where username =
														// "adam"
		blogPost.setPostedOn(new Date());
		blogPost.setPostedBy(user);// FK column postedby_username['adam']
		try {
			blogPostDao.saveBlogPost(blogPost);

		} catch (Exception e) {
			ErroClazz error = new ErroClazz(8, "Unable to insert blog Details" + e.getMessage());
			return new ResponseEntity<ErroClazz>(error, HttpStatus.INTERNAL_SERVER_ERROR);// 500
		}
		return new ResponseEntity<BlogPost>(blogPost, HttpStatus.OK);
	}

	@RequestMapping(value = "/getblogs/{approved}", method = RequestMethod.GET)
	public ResponseEntity<?> getBlogs(@PathVariable int approved, HttpSession session) {

		String username = (String) session.getAttribute("username");
		if (username == null) {
			ErroClazz error = new ErroClazz(5, "Unauthorized access");
			return new ResponseEntity<ErroClazz>(error, HttpStatus.UNAUTHORIZED);// 401
		}
		/* String username = "shweta"; */
		if (approved == 0) { // list of blogs waiting for approval
			User user = userDao.getUserByUsername(username);
			if (!user.getRole().equals("ADMIN")) {
				ErroClazz error = new ErroClazz(7, "Access Denied");
				return new ResponseEntity<ErroClazz>(error, HttpStatus.UNAUTHORIZED);

			}
		}
		List<BlogPost> blogPosts = blogPostDao.getBlogs(approved);
		return new ResponseEntity<List<BlogPost>>(blogPosts, HttpStatus.OK);
	}

	@RequestMapping(value = "/getblog/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getBlogPost(@PathVariable int id, HttpSession session) {
		String username = (String) session.getAttribute("username");
		if (username == null) {
			ErroClazz error = new ErroClazz(5, "Unauthorized access");
			return new ResponseEntity<ErroClazz>(error, HttpStatus.UNAUTHORIZED);// 401
		}
		BlogPost blogPost = blogPostDao.getBlogById(id);
		return new ResponseEntity<BlogPost>(blogPost, HttpStatus.OK);
	}

	// for approval and rejection of user post
	@RequestMapping(value = "/updateapprovalstatus",method=RequestMethod.PUT)
	public ResponseEntity<?> updateApprovalStatus(@RequestBody BlogPost blogPost,
			@RequestParam(required = false) String rejectionReason, HttpSession session) {
		String username = (String)session.getAttribute("username");
		if (username == null) {
			ErroClazz error = new ErroClazz(5, "Unauthorized access");
			return new ResponseEntity<ErroClazz>(error, HttpStatus.UNAUTHORIZED);// 401
		}
		try
		{
			//if admin select Approve, blogPost.approved=1
			//if admin select Reject, blogPost.approved=0
		blogPostDao.updateBlogPost(blogPost,rejectionReason);
		}
		catch(Exception e)
		{
			ErroClazz error= new ErroClazz(7, "Unable to update blogpost approval status");
			return new ResponseEntity<ErroClazz>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Void>(HttpStatus.OK);
}}
