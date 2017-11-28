package com.niit.controlles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	@RequestMapping(value = "/getallpersons",method=RequestMethod.GET)
	public @ResponseBody List<Person> getAllPersons(){
		List<Person> persons = personDao.getAllPersons();
		return persons;
	}
	
	//output:- java to JSON conversion[add the data to the body of response]
	//input:- JSON to java[Http request body]
	
	@RequestMapping(value ="/saveperson",method=RequestMethod.POST)
	public @ResponseBody Person savePerson(@RequestBody Person person)
	{
	personDao.savePerson(person);
	return person;	
	}
	
	@RequestMapping(value = "/deleteperson/{id}",method=RequestMethod.DELETE)
	public @ResponseBody List<Person> deletePerson(@PathVariable int id){
		personDao.deletePerson(id);
		List<Person> persons = personDao.getAllPersons();
		return persons;
	}
}
