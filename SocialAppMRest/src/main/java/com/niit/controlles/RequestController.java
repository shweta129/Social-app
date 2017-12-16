package com.niit.controlles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.niit.dao.BlogPostDao;
import com.niit.dao.JobDao;
import com.niit.dao.UserDao;
import com.niit.dto.User;
import com.niit.service.EmailService;

public class RequestController {

	@Autowired
	private EmailService emailService;
	
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private BlogPostDao blogPostDao;
	
	/*@Autowired
	private EventDAO eventDAO;*/
	
	@Autowired
	private JobDao jobDao;
	
	@Autowired
	private User user;
	
	
	
	/*// method for fetching pending user list by status
	
	@RequestMapping(value="/user/request/list",method=RequestMethod.GET)
	public ResponseEntity<List<User>> fetchPendingUsers()
	{
		System.out.println("Fetching list of Pending users");
		
		List<User> user = userDao.list("PENDING");
		
		return new ResponseEntity<List<User>>(user,HttpStatus.OK);
	}*/
	
	//  method to change user registration status
	
	@RequestMapping(value="/user/request/approval/{id}", method=RequestMethod.POST)
	public ResponseEntity<User> changeStatus(@PathVariable("id") int id)
	{
		System.out.println("Starting of the method Changing status!");
		
		User user = new User();
		
		user = userDao.getUser(id);
		user.setStatus("APPROVED");
		userDao.updateUser(user);
		
		// add method when user approved then send email to user
		
		emailService1.approvedUserMessage(user);
		
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	// method for fetching list of all forum request with pending status
	
	@RequestMapping(value="/forum/request/list", method=RequestMethod.GET)
	public ResponseEntity<List<ForumRequest>> fetchForumRequests()
	{
		System.out.println("Starting of the method fetchForumRequests");
		
		List<ForumRequest> forumRequest = forumRequestDAO.list("PENDING");
		
		return new ResponseEntity<List<ForumRequest>>(forumRequest,HttpStatus.OK);
	}
	
	//method to change forum request status
	
	@RequestMapping(value="/forum/request/approval/{id}",method=RequestMethod.POST)
	public ResponseEntity<ForumRequest> changeForumReqStatus(@PathVariable("id") int id)
	{
		System.out.println("Starting of the method change Forum Request status");
		
		ForumRequest forumRequest = new ForumRequest();
		forumRequest = forumRequestDAO.getForumRequest(id);
		forumRequest.setStatus("APPROVED");
		forumRequestDAO.updateForumRequest(forumRequest);
		
		return new ResponseEntity<ForumRequest>(forumRequest,HttpStatus.OK);
	}
	
	// method for fetching pending blog list by status
	
	@RequestMapping(value="/blog/request/list",method=RequestMethod.GET)
	public ResponseEntity<List<Blog>> fetchPendingBlogs()
	{
		System.out.println("Starting of the method fetch the Pending Blog!");
		
		List<Blog> blog = blogDAO.getBlogByStatus("PENDING");
		
		return new ResponseEntity<List<Blog>>(blog,HttpStatus.OK);
	}
	
	// method to change blog request status
	
	@RequestMapping(value="blog/request/approval/{id}",method=RequestMethod.POST)
	public ResponseEntity<Blog> changeBlogStatus(@PathVariable("id") int id)
	{
		System.out.println("Starting of the method change Blog status!");
		
		Blog blog = null;
		blog = blogDAO.getBlog(id);
		blog.setStatus("APPROVED");
		blogDAO.updateBlog(blog);
		
		return new ResponseEntity<Blog>(blog,HttpStatus.OK);
	}
	
	// method for fetching the pending job list by status
	
	@RequestMapping(value="/job/request/list", method= RequestMethod.GET)
	public ResponseEntity<List<Job>> fetchPendingJobs()
	{
		System.out.println("Starting of the method fetching the list of pending jobs!");
		
		List<Job> jobList = jobDAO.getJobByStatus("PENDING");
		
		return new ResponseEntity<List<Job>>(jobList,HttpStatus.OK);
	}
	
	// method to approve job 
	
	@RequestMapping(value="/job/request/approval/{id}", method=RequestMethod.POST)
	public ResponseEntity<Job> approveJobs(@PathVariable("id") int id)
	{
		System.out.println("Starting of the methd approveJobs!");
		
		Job job = null;
		job = jobDAO.getJob(id);
		job.setStatus("APPROVED");
		
		jobDAO.updateJob(job);
		
		return new ResponseEntity<Job>(job,HttpStatus.OK);
	}
	
	// method for fetching pending event list by status
	
	@RequestMapping(value="/event/request/list", method=RequestMethod.GET)
	public ResponseEntity<List<Events>> fetchPendingEvents()
	{
		System.out.println("Starting of the method fetchPendingEvents list!");
		
		List<Events> eventList = eventDAO.getEventByStatus("PENDING");
		
		return new ResponseEntity<List<Events>>(eventList,HttpStatus.OK);
	}
	
	// method to approve events
	
	@RequestMapping(value="/event/request/approval/{id}",method=RequestMethod.POST)
	public ResponseEntity<Events> approveEvents(@PathVariable("id") int id)
	{
		System.out.println("Starting of the method approveEvents!");
		
		Events events = null;
		events = eventDAO.getEvent(id);
		events.setStatus("APPROVED");
		
		eventDAO.updateEvent(events);
		
		return new ResponseEntity<Events>(events,HttpStatus.OK);
	}
}
}
