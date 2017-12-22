package service;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.springframework.stereotype.Service;

@Service
public class MyLogger {

	private static Logger logger;

	public MyLogger() throws IOException {
		logger = Logger.getLogger(MyLogger.class.getName());
//		FileHandler fh = new FileHandler(
//				"C:\\Users\\Полина\\git\\myproj\\log.txt");
		if (System.getProperty("debug") != null) {
			CustomOutputHandler handler = new CustomOutputHandler();
			handler.setFormatter(new SimpleFormatter());
			logger.addHandler(handler);
		}
	}

	public void logInvalidEmailLogin(String name, String email) {
		logger.log(Level.INFO,
					"Invalid registration try: login " + name + " or email " + email + "has already been used");
	}

	public void logSuccessfulAuthorisation(String name) {
		logger.log(Level.INFO, "The user named " + name + " has successfully authorised");
	}

	public void logSuccessfulRegistration(String name) {
		logger.log(Level.INFO, "The user named " + name + " has successfully registrated");
	}

	public void logLogout(String name) {
		logger.log(Level.INFO, "The user named " + name + " has logged out");
	}

	public void logInvalidPassword(String name) {
		logger.log(Level.INFO, "The user named " + name + " has entered the invalid password");
	}

	public void logNoPassword(String name) {
		logger.log(Level.INFO, "The user named " + name + " has entered empty password during the registration");
	}

	public void error(String message, Exception e) {
		logger.log(Level.SEVERE, message, e);
	}
}
