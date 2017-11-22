package controller;

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
public class LogInController {

	@RequestMapping(value = "hello", method = RequestMethod.POST)
	public String logIn(@RequestParam String name, @RequestParam String password, Model model) throws SQLException,
			InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchAlgorithmException {
		DBConnector connector = new DBConnector();
		String res = connector.findpassword(name);
		PasswordHasher ph = new PasswordHasher("MD5");
		password = ph.hash(password);
		if (res.equals(password)) {
			System.out.println("IT s ok, you successfully have logged in");
			model.addAttribute("name", name);
			SoapCurrenciesBrowser browser = new SoapCurrenciesBrowser();
			ArrayList<Currency> list = browser.getresult();
			model.addAttribute("list", list);
			model.addAttribute("remember", connector.remember(name));
			return "hello";
		} else {
			System.out.println("OH, smth wrong!");
			return "index";
		}
		// наследование спп: одинаковые методы родителей?
	}

}