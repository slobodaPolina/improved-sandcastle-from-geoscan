package helpful;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnector {
	Connection conn;
	Statement stmt;
	ResultSet res;

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
		//checkes if db includes a user with sch login or such email
		res = stmt.executeQuery("SELECT * FROM users WHERE login = \"" + name + "\" OR email = \"" + email + "\";");
		return res.next();
	}

	public void insert(String name, String email, String password, String remember) throws SQLException {
		//inserts new user in database
		stmt.executeUpdate("INSERT INTO users (login, email, password, remember) VALUES (\"" + name + "\", \"" + email + "\", \""
				+ password + "\", \"" + remember + "\");");
	}

	public String findpassword(String name) throws SQLException {
		//returns user`s password
		ResultSet res = stmt.executeQuery("SELECT password FROM users WHERE login = \"" + name + "\";");
		if (res.next())
			return res.getString(1);
		else
			return "";
	}

	public String remember(String name) throws SQLException {
		ResultSet res = stmt.executeQuery("SELECT remember FROM users WHERE login = \"" + name + "\";");
		if (res.next())
			return res.getString("remember");
		return "false";
	}
}
