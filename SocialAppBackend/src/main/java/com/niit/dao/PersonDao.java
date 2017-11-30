package com.niit.dao;

import java.util.List;

import com.niit.dto.Person;

public interface PersonDao {

	public List<Person> getAllPersons();

	public void savePerson(Person person);

	public void deletePerson(int id);

	public Person getPerson(int id);

	public void updatePerson(Person person);
}
