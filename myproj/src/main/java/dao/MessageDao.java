package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import entity.Message;

@Repository
@Transactional(readOnly = true)
public class MessageDao {

	@Autowired
	SessionFactory sessionFactory;

	private void proceedWithinTransaction(Consumer<Session> m) {
		Session session = getSession();
		session.beginTransaction();
		m.accept(session);
		session.getTransaction().commit();
	}

	@Transactional(readOnly = false)
	public void create(String receiver, String sender, String text) {
		Message message = new Message(receiver, sender, text);
		proceedWithinTransaction((session) -> session.save(message));
	}

	@Transactional(readOnly = false)
	public void create(String sender, String text) {
		create("", sender, text);
	}

	public List<Message> getAll() {
		List<Message> list = new ArrayList<Message>();
		Session session = getSession();
		session.beginTransaction();
		Query query = session.createQuery("from Message");// it is hql syntax for select * from Message
		list = query.list();
		session.getTransaction().commit();
		return list;
	}

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

}
