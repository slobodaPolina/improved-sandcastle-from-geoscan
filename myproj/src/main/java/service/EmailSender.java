package service;

import java.util.Properties;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class EmailSender {

	@Value(value = "classpath:props.properties")
	private Resource propsResource;

	public void send(String receiver, String name, int code) {
		try {
			Properties props = new Properties();
			props.load(propsResource.getInputStream());
			Email email = new SimpleEmail();

			email.setHostName(props.getProperty("mail.host"));
			email.setSmtpPort(587);
			// from where
			email.setAuthenticator(
					new DefaultAuthenticator(props.getProperty("mail.username"), props.getProperty("mail.password")));
			email.setStartTLSEnabled(true);
			email.setFrom(props.getProperty("mail.username"));
			email.setSubject(props.getProperty("mail.subject"));
			email.setMsg(props.getProperty("mail.text") + "http://localhost:8080/MyProject/confirm?name=" + name
					+ "&code=" + code);
			email.addTo(receiver);
			email.send();
			System.out.println("I have sent you an email!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}