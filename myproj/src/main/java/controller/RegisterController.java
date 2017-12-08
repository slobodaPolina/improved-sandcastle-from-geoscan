package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	public String logIn(HttpServletRequest request, Model model) {
		try {
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String password = request.getParameter("password1");
			String remember = request.getParameter("remember");
			DBConnector connector = new DBConnector();
			boolean exists = connector.exists(name, email);
			if (!exists) {
				PasswordHasher ph = new PasswordHasher("MD5");
				password = ph.hash(password);
				connector.insertUser(name, email, password, remember.toString());
				HttpSession session = request.getSession(true);
				session.setAttribute("name", name);
				commonService.saveSession(request, name);
				sender.send(email);
				if ("true".equals(remember))
					commonService.fillModel(name, true, model);
				else
					commonService.fillModel(name, false, model);
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