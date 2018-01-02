package com.niit.daoimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.dao.FriendDao;
import com.niit.dto.User;

@Repository
@Transactional
public class FriendDaoImpl implements FriendDao {

	@Autowired
	private SessionFactory sessionFactory;

	public List<User> suggestedUsersList(String username) {
		Session session = sessionFactory.getCurrentSession();
		SQLQuery query=session.createSQLQuery("(select * from user_detail where username in "
				+ " (select username from user_detail where username!=?"
				+ " minus "
				+ " (select fromId from Friend where toId=? "
				+ " union select toId from Friend where fromId=? ))) ");
		
		query.setString(0, username);
		query.setString(1, username);
		query.setString(2, username);
		query.addEntity(User.class);
		List<User> suggestedUsers=query.list();
		return suggestedUsers;
	}
}
