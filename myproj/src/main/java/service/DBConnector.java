package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import dao.UserDao;
import entity.User;

@Service
@Transactional(readOnly = true)
public class DBConnector {

	@Autowired
	private UserDao userDao;

	public boolean exists(String name, String email) {
		return userDao.exists(name, email);
	}

	@Transactional(readOnly = false)
	public void insertUser(String name, String email, String password) {
		User user = new User(name, email, password);
		userDao.create(user);
	}

	public int getCode(String name) {
		return userDao.getCode(name);
	}

	@Transactional(readOnly = false)
	public void setConfirmingStatus(String name) {
		userDao.setConfirmingStatus(name);
	}
}
