package service;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class Logger {
	FileWriter out;

	public Logger() throws IOException {
		out = new FileWriter("classpath:log.txt", true);
	}

	private void printData() {
		try {
			DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss ");
			Date now = new Date();
			String dateTime = df.format(now);
			out.write(dateTime);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void logInvalidEmailLogin(String name, String email) throws IOException {
		try {
			printData();
			out.write("Invalid registration try: login " + name + " or email " + email + "has already been used");
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void logSuccessfulAuthorisation(String name) {
		try {
			printData();
			out.write("The user named " + name + " has successfully authorised");
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void logSuccessfulRegistration(String name) {
		try {
			printData();
			out.write("The user named " + name + " has successfully registrated");
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void logLogout(String name) {
		try {
			printData();
			out.write("The user named " + name + " has logged out");
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void logInvalidPassword(String name) {
		try {
			printData();
			out.write("The user named " + name + " has entered the invalid password");
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void logNoPassword(String name) {
		try {
			printData();
			out.write("The user named " + name + " has entered empty password during the registration");
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
