package controller;

import java.sql.*;
import java.util.ArrayList;

public class DBConnector {
	private Connection conn;
	private Statement stmt;
	private Statement stmt2;
	private ResultSet res;
	private ResultSet tmp;
	private ArrayList<Lesson> result;

	// NUMERATION OF COLUMNS in ResultSet FROM 1.
	// WHEN YOU START THE ACTIONS, YOU ARE BEFORE THE FIRST LINE
	// SO DO NEXT() AND GETSTRING(1) TO GET THE FIRST COLUMN
	public DBConnector() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		// HERE I HAVE REAL PROBLEMS WITH MYSQL SERVER CONNECTION, SO CONNECTED TO MYSQL
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		conn = DriverManager.getConnection(
				"jdbc:mysql://localhost/mytimetable?user=root&password=1234&serverTimezone=Europe/Moscow");
		stmt = conn.createStatement();
		stmt2 = conn.createStatement();
	}

	public ArrayList<Lesson> Search(String name) throws SQLException {
		result = new ArrayList<Lesson>();
		int groupp_id = SearchByGroup(name);
		if (groupp_id == -1) {
			ArrayList<Integer> teachers = SearchByTeacher(name);
			if (teachers.size() == 0) {
				// nothing at all
				return result;
			} else {
				for (int i = 0; i < teachers.size(); i++) {
					res = stmt.executeQuery("SELECT * FROM lesson WHERE teacher_id = \"" + teachers.get(i) + "\";");
					fill(res);
				}
			}
		} else {
			res = stmt.executeQuery("SELECT * FROM lesson WHERE groupp_id = \"" + groupp_id + "\";");
			fill(res);
		}
		return result;
	}

	private void fill(ResultSet r) throws NumberFormatException, SQLException {// i is the number of the row to fill it
																				// with string info
		while (res.next()) {
			Lesson l = new Lesson();

			l.hall_id = Integer.parseInt(res.getString("hall_id"));
			tmp = stmt2.executeQuery("SELECT * FROM hall WHERE hall_id = \"" + l.hall_id + "\";");
			if (tmp.next()) {
				l.hall_number = tmp.getString("number");
				l.hall_type = tmp.getString("type");
				l.housing = tmp.getString("housing");
				l.spaciousness = tmp.getString("spaciousness");
			}

			l.hour_id = Integer.parseInt(res.getString("hour_id"));
			tmp = stmt2.executeQuery("SELECT * FROM hour WHERE hour_id = \"" + l.hour_id + "\";");
			if (tmp.next()) {
				l.evenity = tmp.getString("evenity");
				l.day = tmp.getString("day");
				l.time = tmp.getString("time");
			}
			
			l.teacher_id = Integer.parseInt(res.getString("teacher_id"));
			tmp = stmt2.executeQuery("SELECT * FROM teacher WHERE teacher_id = \"" + l.teacher_id + "\";");
			if (tmp.next()) {
				l.teacher_name = tmp.getString("name");
				l.patronymic = tmp.getString("patronymic");
				l.surname = tmp.getString("surname");
				l.experience = tmp.getString("experience");
				l.degree = tmp.getString("degree");
				l.photo = tmp.getString("photo");
			}
			
			l.groupp_id = Integer.parseInt(res.getString("groupp_id"));
			tmp = stmt2.executeQuery("SELECT * FROM groupp WHERE groupp_id = \"" + l.groupp_id + "\";");
			if (tmp.next()) {
				l.groupp_number = tmp.getString("number");
			}
			
			l.type_id = Integer.parseInt(res.getString("type_id"));
			tmp = stmt2.executeQuery("SELECT * FROM type WHERE type_id = \"" + l.type_id + "\";");
			if (tmp.next()) {
				l.type_name = tmp.getString("name");
			}
			
			l.subject_id = Integer.parseInt(res.getString("subject_id"));
			tmp = stmt2.executeQuery("SELECT * FROM subject WHERE subject_id = \"" + l.subject_id + "\";");
			if (tmp.next()) {
				l.subject_name = tmp.getString("name");
			}
			result.add(l);
		}
	}

	// searches this string in group names.. returns id or -1 in case where is not
	// such group
	private int SearchByGroup(String name) throws SQLException {
		name = (name.charAt(0) + "").toUpperCase() + name.substring(1);
		res = stmt.executeQuery("SELECT groupp_id FROM groupp WHERE number = \"" + name + "\";");
		if (res.next())
			return Integer.parseInt((res.getString("groupp_id")));
		return -1;
	}

	// searches this string in teachers. The list might be empty!
	private ArrayList<Integer> SearchByTeacher(String name) throws SQLException {
		name = (name.charAt(0) + "").toUpperCase() + name.substring(1).toLowerCase();
		ResultSet res = stmt.executeQuery("SELECT teacher_id FROM teacher WHERE surname = \"" + name + "\";");
		ArrayList<Integer> answer = new ArrayList<Integer>();
		while (res.next())
			answer.add(Integer.parseInt(res.getString("teacher_id")));
		return answer;
	}
}
