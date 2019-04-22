package service;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class EmailSender {

	@Value(value = "classpath:props.properties")
	private Resource propsResource;

	public void send(String receiver, String name, int code) throws IOException, EmailException {
		System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");
		Properties props = new Properties();
		props.load(propsResource.getInputStream());
		HtmlEmail email = new HtmlEmail();

		email.setHostName(props.getProperty("mail.host"));
		email.setSmtpPort(587);
		// from where
		email.setAuthenticator(
				new DefaultAuthenticator(props.getProperty("mail.username"), props.getProperty("mail.password")));
		email.setStartTLSEnabled(true);
		email.setFrom("jatsko.polina@gmail.com");
		//email.setFrom(props.getProperty("mail.username"));
		email.setSubject(props.getProperty("mail.subject"));
		email.setHtmlMsg("<html>" + props.getProperty("mail.text") + "<a>http://localhost:8080/MyProject/confirm?name="
				+ name + "&code=" + code + "</a></html>");
		email.addTo(receiver);
		email.send();
		System.out.println("I have sent you an email!");

	}

}
