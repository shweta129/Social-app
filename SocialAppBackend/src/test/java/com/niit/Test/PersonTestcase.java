/*package com.niit.Test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.dao.PersonDao;
import com.niit.dto.Person;

public class PersonTestcase {

	
private static AnnotationConfigApplicationContext context;
	
	private static PersonDao personDao;
	
	private Person person;
	
	
	@BeforeClass
	public static void init() {
    context = new AnnotationConfigApplicationContext();
    context.scan("com.niit");
    context.refresh();
    
    personDao = (PersonDao) context.getBean("personDao");
	}
	
	
    @Test
    public void testsave(){
    
    	person =new Person();
    	person.setFirstname("sandy");
    	person.setLastname("patil");
    	person.setEmail("sp@gmail.com");
    	person.setPhonenumber("1234567890");
    	
    

ass
    }
	
	
}
*/