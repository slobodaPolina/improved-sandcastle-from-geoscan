package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import helpful.DBConnector;
import helpful.PasswordHasher;
import service.CommonService;
import service.EmailSender;
import service.SoapCurrenciesBrowser;

@Controller
public class RegisterController {
	@Autowired
	EmailSender sender;
	@Autowired
	SoapCurrenciesBrowser browser;
	@Autowired
	private CommonService commonService;

	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String logIn(@RequestParam String email, @RequestParam String name, @RequestParam String password1,
			@RequestParam Boolean remember, Model model) {
		try {
			DBConnector connector = new DBConnector();
			boolean exists = connector.exists(name, email);
			if (!exists) {
				PasswordHasher ph = new PasswordHasher("MD5");
				password1 = ph.hash(password1);
				connector.insertUser(name, email, password1, remember.toString());
				sender.send(email);
				commonService.fillModel(name, remember, model);
				System.out.println("It`s ok, i have created your account");
				return "hello";
			}
			System.out.println("Seems like your user name or your email is used already");
			return "index";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "exception";
	}

}