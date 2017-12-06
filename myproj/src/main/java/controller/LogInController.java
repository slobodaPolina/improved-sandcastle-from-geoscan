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
import service.SoapCurrenciesBrowser;

@Controller
public class LogInController {
	@Autowired
	SoapCurrenciesBrowser browser;
	@Autowired
	private CommonService commonService;

	@RequestMapping(value = "hello", method = RequestMethod.POST)
	public String logIn(@RequestParam String name, @RequestParam String password, Model model) {
		try {
			DBConnector connector = new DBConnector();
			String res = connector.findPassword(name);
			PasswordHasher ph = new PasswordHasher("MD5");
			password = ph.hash(password);
			if (res.equals(password)) {
				commonService.fillModel(name, model);
				System.out.println("IT s ok, you successfully have logged in");
				return "hello";
			} else {
				System.out.println("OH, your password is wrong!");
				return "index";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "exception";
		// наследование спп: одинаковые методы родителей?
	}

}