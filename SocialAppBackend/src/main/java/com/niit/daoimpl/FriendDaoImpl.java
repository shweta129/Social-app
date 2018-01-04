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
import com.niit.dto.Friend;
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

	public void addFriendRequest(Friend friend) {
		Session session = sessionFactory.getCurrentSession();
		session.save(friend); // insert into friend value(fromId,tiId,status)
		
		
	}

	public List<Friend> pendingRequests(String username) {
	Session session = sessionFactory.getCurrentSession();
	Query query = session.createQuery("from Friend where toId=? and status=?");
	query.setString(0, username);
	query.setCharacter(1, 'P');
	List<Friend> pendingRequests=query.list();
	return pendingRequests;
	}
	
    //friend -->friend .status='A'  for Accept , friend.status='D' for delete
	

	public void updatePendingRequest(Friend friend) {
		Session session =sessionFactory.getCurrentSession();
		//delete/update
		if(friend.getStatus()=='A')
			session.update(friend);//update friend set status
		else
			session.delete(friend);//delete friend where id=?
	}

	public List<User> listOfFriends(String username) {
		Session session =sessionFactory.getCurrentSession();
		SQLQuery query1=session.createSQLQuery("select * from user_detail where username in "
				+ "(select toId from Friend  where fromId=? and status='A')");
		
		SQLQuery query2=session.createSQLQuery("select * from user_detail where username in "
				+ "(select fromId from Friend  where toId=? and status='A')");
		
		query1.setString(0, username);
		query2.setString(0, username);
		query1.addEntity(User.class);
		query2.addEntity(User.class);
		List<User> list1=query1.list();
		List<User> list2=query2.list();
		list1.addAll(list2);
		return list1;
	}
}
