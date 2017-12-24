package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import dao.UserDao;
import entity.User;

@Service
public class DBConnector {

	@Autowired
	private UserDao userDao;

	@Transactional(readOnly = false)
	public boolean exists(String name, String email) {
		return userDao.exists(name, email);
	}

	@Transactional(readOnly = false)
	public void insertUser(String name, String email, String password) {
		User user = new User(name, email, password);
		userDao.create(user);
	}

	@Transactional(readOnly = false)
	public String findPassword(String name) {
		return userDao.getPassword(name);
	}
}
