package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import helpful.Currency;
import helpful.DBConnector;
import helpful.PasswordHasher;
import helpful.SoapCurrenciesBrowser;

@Controller
public class RegisterController {

	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String logIn(@RequestParam String email, @RequestParam String name, @RequestParam String password1,
			@RequestParam String remember, Model model) throws SQLException, InstantiationException,
			IllegalAccessException, ClassNotFoundException, NoSuchAlgorithmException,
			FileNotFoundException, IOException{
		DBConnector connector = new DBConnector();
		boolean exists = connector.exists(name, email);
		if (!exists) {
			PasswordHasher ph = new PasswordHasher("MD5");
			password1 = ph.hash(password1);
			connector.insert(name, email, password1, remember);
			//EmailSender.send(email);
			model.addAttribute("name", name);
			model.addAttribute("remember", remember);
			System.out.println("It`s ok, i have created your account");
			SoapCurrenciesBrowser browser = new SoapCurrenciesBrowser();
			ArrayList<Currency> list = browser.getresult();
			model.addAttribute("list", list);
			return "hello";
		}
		System.out.println("Seems like your user name or your email is used already");
		return "index";
	}

}