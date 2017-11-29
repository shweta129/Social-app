package com.niit.controlles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.niit.dao.PersonDao;
import com.niit.dto.ErroClazz;
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
	public ResponseEntity<?> deletePerson(@PathVariable int id){
		try{
			
			personDao.deletePerson(id);
		}
		catch(Exception e){
			ErroClazz error=new ErroClazz(1, "Person with" + id+ "doesn't exists");
			return new ResponseEntity<ErroClazz>(error,HttpStatus.NOT_FOUND);
			//2nd call back function[Error function]
			//response.data = ErrorClazz obj in Json
			//response.status=HttpStatus not found (404)
		}
		
		List<Person> persons = personDao.getAllPersons();
		return new ResponseEntity<List<Person>>(persons,HttpStatus.OK);
		//1st call bck function [success function]
		//response.data=List<Person> in JSON
		//response.status = HttpStatus.OK [200]
	}
}
