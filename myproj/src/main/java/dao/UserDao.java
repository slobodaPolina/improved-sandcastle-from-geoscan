package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import entity.User;

@Repository
public class UserDao {

	@Autowired
	SessionFactory sessionFactory;
	
	public void create(User user) {
		Session session = getSession();
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
	}

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
}
