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
	/*
	 * public boolean findRememberStatus(String name) throws SQLException {
	 * ResultSet res =
	 * stmt.executeQuery("SELECT remember FROM users WHERE login = \"" + name +
	 * "\";"); if (res.next()) return res.getBoolean("remember"); return false; }
	 * 
	 * public void storeSession(String name, String id) throws SQLException {
	 * stmt.executeUpdate("UPDATE users SET session=\"" + id + "\" where login= \""
	 * + name + "\";"); }
	 * 
	 * public void storeRememberStatus(String name, String remember) throws
	 * SQLException { stmt.executeUpdate("UPDATE users SET remember=\"" + remember +
	 * "\" where login= \"" + name + "\";"); }
	 * 
	 * public boolean IsTheSessionActive(String name, String id) throws SQLException
	 * { ResultSet res =
	 * stmt.executeQuery("SELECT session FROM users WHERE login = \"" + name +
	 * "\";"); if (res.next()) { String str = res.getString("session"); if
	 * (id.equals(str)) return true; return false; } return false; }
	 */
}
