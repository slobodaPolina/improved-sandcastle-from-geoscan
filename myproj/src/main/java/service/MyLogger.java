package service;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

@Service
public class MyLogger {

	private static Logger logger;

	public MyLogger() throws IOException {
		logger = Logger.getLogger(MyLogger.class.getName());
		FileHandler fh = new FileHandler(
				"C:\\Users\\Полина\\git\\myproj\\log.txt");
		logger.addHandler(fh);
	}

	public void logInvalidEmailLogin(String name, String email) {
		try {

			logger.log(Level.INFO,
					"Invalid registration try: login " + name + " or email " + email + "has already been used");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void logSuccessfulAuthorisation(String name) {
		try {
			logger.log(Level.INFO, "The user named " + name + " has successfully authorised");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void logSuccessfulRegistration(String name) {
		try {
			logger.log(Level.INFO, "The user named " + name + " has successfully registrated");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void logLogout(String name) {
		try {
			logger.log(Level.INFO, "The user named " + name + " has logged out");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void logInvalidPassword(String name) {
		try {
			logger.log(Level.INFO, "The user named " + name + " has entered the invalid password");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void logNoPassword(String name) {
		try {
			logger.log(Level.INFO, "The user named " + name + " has entered empty password during the registration");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
