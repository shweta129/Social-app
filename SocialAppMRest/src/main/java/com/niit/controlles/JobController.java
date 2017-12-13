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

import com.niit.dao.JobDao;
import com.niit.dao.UserDao;
import com.niit.dto.ErroClazz;
import com.niit.dto.Job;
import com.niit.dto.User;

@Controller
public class JobController {
	
	
	@Autowired
	JobDao jobDao;
	
	@Autowired
	UserDao userDao;
	
	
	
	@RequestMapping(value = "/savejob" ,method=RequestMethod.POST)
	public ResponseEntity<?> saveJob(@RequestBody Job job,HttpSession session){
		String username=(String)session.getAttribute("username");
		if(username==null){//login method is not executed, session.setAttribute("username") is not yet executed
			ErroClazz error = new ErroClazz(5, "Unauthorized access");
		    return new ResponseEntity<ErroClazz>(error,HttpStatus.UNAUTHORIZED);
	}
		//User is authanticated,so check for role (Authorization)
		User user=userDao.getUserByUsername(username);//select * from user where username="shweta"
		if(!user.getRole().equals("ADMIN")){
			ErroClazz error = new ErroClazz(6, "Access denied");
			return new ResponseEntity<ErroClazz>(error,HttpStatus.UNAUTHORIZED);
		}
		job.setPostedOn(new Date());
		try{
			jobDao.saveJob(job);
		}
		catch(Exception e){
			ErroClazz error = new ErroClazz(7,"Unable to insert job details" + e.getMessage());
			return new ResponseEntity<ErroClazz>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Job>(job,HttpStatus.OK);

}
	
	
	@RequestMapping(value="/alljobs",method=RequestMethod.GET)
	public ResponseEntity<?> getAllJobs(HttpSession session){
		String username=(String)session.getAttribute("username");
		if(username==null){
			ErroClazz error = new ErroClazz(5, "Unauthorized access");
		    return new ResponseEntity<ErroClazz>(error,HttpStatus.UNAUTHORIZED);
		}
		
	List<Job> jobs=jobDao.getAllJobs();
	return new ResponseEntity<List<Job>>(jobs,HttpStatus.OK);
	}
	
	@RequestMapping(value="/getjob/{id}",method=RequestMethod.GET)
	public ResponseEntity<?> getJob(@PathVariable int id,HttpSession session){
		String username=(String)session.getAttribute("username");
		if(username==null){
			ErroClazz error = new ErroClazz(5, "Unauthorized access");
		    return new ResponseEntity<ErroClazz>(error,HttpStatus.UNAUTHORIZED);
		}
		Job job=jobDao.getJob(id);
		return new ResponseEntity<Job>(job,HttpStatus.OK);
	}
	
	/*@RequestMapping(value = "/removeJob/{jobid}")
	public ResponseEntity<String> removeJob(@PathVariable("jobid") int )*/
	
}