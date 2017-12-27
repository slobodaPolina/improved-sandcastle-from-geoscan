package dao;

import java.util.concurrent.Callable;
import java.util.function.Consumer;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import entity.User;

@Repository
public class UserDao {

	@Autowired
	SessionFactory sessionFactory;

	private void proceedWithinTransaction(Consumer<Session> m) {
		Session session = getSession();
		session.beginTransaction();
		m.accept(session);
		session.getTransaction().commit();
	}

	public void create(User user) {
		// proceedWithinTransaction(new Consumer<Session>() {
		// public void accept(Session session){
		// session.save(user);
		// }
		// });
		// proceedWithinTransaction((session) -> {session.save(user);});

		proceedWithinTransaction((session) -> session.save(user));
	}

	public User getByName(String name) {
		Session session = getSession();
		session.beginTransaction();
		User user = session.get(User.class, name);
		session.getTransaction().commit();
		return user;
	}

	public int getCode(String name) {
		User user = getByName(name);
		return user.getCode();
	}

	public void setConfirmingStatus(String name) {
		User user = getByName(name);
		user.setConfirmed(1);
	}

	public boolean exists(String name, String email) {
		Session session = getSession();
		session.beginTransaction();
		User user = session.get(User.class, name);
		if (user == null) {
			Query query = session.createQuery("select email from User where email=:email");
			query.setParameter("email", email);
			// if there is no user with this email
			if (query.list().size() == 0) {
				session.getTransaction().commit();
				return false;
			}
		}
		session.getTransaction().commit();
		return true;
	}

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

}
