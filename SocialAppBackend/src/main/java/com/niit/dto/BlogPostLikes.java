package com.niit.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class BlogPostLikes {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
	
	@ManyToOne
    private BlogPost blogpost;
	@ManyToOne
    private User user;

//getter setter
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public BlogPost getBlogpost() {
	return blogpost;
}
public void setBlogpost(BlogPost blogpost) {
	this.blogpost = blogpost;
}
public User getUser() {
	return user;
}
public void setUser(User user) {
	this.user = user;
}


}
