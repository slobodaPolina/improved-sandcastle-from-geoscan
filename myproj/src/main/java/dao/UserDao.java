package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
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

	public String getPassword(String name) {
		Session session = getSession();
		session.beginTransaction();
		User user = session.get(User.class, name);
		session.getTransaction().commit();
		return user.getPassword();
	}

	public boolean exists(String name, String email) {
		Session session = getSession();
		session.beginTransaction();
		User user = session.get(User.class, name);
		if (user == null) {
			Query query = session.createQuery("select email from User where email=:email");
			query.setParameter("email", email);
			//if there is no user with this email
			if (query.list().size() == 0)
				return false;
		}
		return true;
	}

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

}
