package com.niit.daoimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.dao.PersonDao;
import com.niit.dto.Person;
@Repository
@Transactional
public class PersonDaoImpl implements PersonDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public List<Person> getAllPersons() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Person");
		return query.list();
		
		
	}

	//@Override
	public void savePerson(Person person) {
		Session session = sessionFactory.getCurrentSession();
		session.save(person);
		
	}

	//@Override
	public void deletePerson(int id) {
		Session session =sessionFactory.getCurrentSession();
		Person person = session.get(Person.class, id);
		session.delete(person);
		
	}

	
	
}

	
	

