/*package helpful;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class EmailSender {
	public static void send(String receiver) throws EmailException, FileNotFoundException, IOException {
		Properties props = new Properties();
		props.load(new FileInputStream("props.properties"));
		Email email = new SimpleEmail();
		email.setHostName(props.getProperty("mail.host"));
		email.setSmtpPort(587);
		// from where
		email.setAuthenticator(
				new DefaultAuthenticator(props.getProperty("mail.username"), props.getProperty("mail.password")));
		email.setTLS(true);
		email.setFrom(props.getProperty("mail.username"));
		email.setSubject(props.getProperty("mail.subject"));
		email.setMsg(props.getProperty("mail.text"));
		email.addTo(receiver);
		email.send();
		System.out.println("I have sent you an email!");
	}

}*/