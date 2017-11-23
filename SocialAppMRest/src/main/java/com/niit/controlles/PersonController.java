package com.niit.controlles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.niit.dao.PersonDao;
import com.niit.dto.Person;
@Controller
public class PersonController {

	@Autowired
	private PersonDao personDao;
	public PersonController(){
		System.out.println("CREATING INSTANCE FOR PERSONCONTROLLER");
	}
	//@ResponseBody = data conversion
	//@ResponseBody = convert list of java object to array of JSON
	@RequestMapping(value = "/getallpersons")
	public @ResponseBody List<Person> getAllPersons(){
		List<Person> persons = personDao.getAllPersons();
		return persons;
	}
}
