package service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class EmailSender {
	
	@Value(value = "classpath:props.properties")
	private Resource propsResource;
	//heidisql 
	public void send(String receiver) throws EmailException, FileNotFoundException, IOException {
		Properties props = new Properties();
		props.load(propsResource.getInputStream());
				//"C:\\Users\\Полина\\git\\myproj\\src\\main\\resources\\props.properties"));
		Email email = new SimpleEmail();
		
		email.setHostName(props.getProperty("mail.host"));
		email.setSmtpPort(587);
		// from where
		email.setAuthenticator(
				new DefaultAuthenticator(props.getProperty("mail.username"), props.getProperty("mail.password")));
		email.setStartTLSEnabled(true);
		email.setFrom(props.getProperty("mail.username"));
		email.setSubject(props.getProperty("mail.subject"));
		email.setMsg(props.getProperty("mail.text"));
		email.addTo(receiver);
		email.send();
		System.out.println("I have sent you an email!");
	}

}