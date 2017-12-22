package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.UserDao;
import entity.User;

@Service
public class DBConnector {
	Connection conn;
	Statement stmt;
	ResultSet res;
	@Autowired
	private UserDao userDao;

	// NUMERATION OF COLUMNS in ResultSet FROM 1.
	// WHEN YOU START THE ACTIONS, YOU ARE BEFORE THE FIRST LINE
	// SO DO NEXT() AND GETSTRING(1) TO GET THE FIRST
	// i know here only 1 column (i ask only for the password)
	// and MUST be only 1 line
	public DBConnector() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		conn = DriverManager
				.getConnection("jdbc:mysql://localhost/world?user=root&password=1234&serverTimezone=Europe/Moscow");
		stmt = conn.createStatement();
	}

	public boolean exists(String name, String email) throws SQLException {
		// checkes if db includes a user with sch login or such email
		res = stmt.executeQuery("SELECT * FROM users WHERE login = \"" + name + "\" OR email = \"" + email + "\";");
		return res.next();
	}

	@Transactional(readOnly = false)
	public void insertUser(String name, String email, String password) {
		User user = new User(name, email, password);
		userDao.create(user);
	}

	public String findPassword(String name) throws SQLException {
		// returns user`s password
		ResultSet res = stmt.executeQuery("SELECT password FROM users WHERE login = \"" + name + "\";");
		if (res.next())
			return res.getString(1);
		else
			return "";
	}

	public boolean findRememberStatus(String name) throws SQLException {
		ResultSet res = stmt.executeQuery("SELECT remember FROM users WHERE login = \"" + name + "\";");
		if (res.next())
			return res.getBoolean("remember");
		return false;
	}

	// EDIT
	public void storeSession(String name, String id) throws SQLException {
		stmt.executeUpdate("UPDATE users SET session=\"" + id + "\" where login= \"" + name + "\";");
	}

	public void storeRememberStatus(String name, String remember) throws SQLException {
		stmt.executeUpdate("UPDATE users SET remember=\"" + remember + "\" where login= \"" + name + "\";");
	}

	public boolean IsTheSessionActive(String name, String id) throws SQLException {
		ResultSet res = stmt.executeQuery("SELECT session FROM users WHERE login = \"" + name + "\";");
		if (res.next()) {
			String str = res.getString("session");
			if (id.equals(str))
				return true;
			return false;
		}
		return false;
	}
}
