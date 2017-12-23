package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import service.CommonService;
import service.DBConnector;
import service.EmailSender;
import service.MyLogger;
import service.PasswordHasher;

@Controller
public class RegisterController {
	@Autowired
	EmailSender sender;
	@Autowired
	private DBConnector connector;
	@Autowired
	private PasswordHasher ph;
	@Autowired
	private CommonService commonService;
	@Autowired
	MyLogger logger;

	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String Register(HttpServletRequest request, HttpServletResponse response, Model model) {
		System.out.println("------ REGISTER CONTROLLER ------");
		try {
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String password = request.getParameter("password");

			String remember = "false";
			if (commonService.hasParameter(request, "remember"))
				remember = "true";

			if (!connector.exists(name, email)) {
				if (!"".equals(password)) {
					connector.insertUser(name, email, ph.hash(password, "MD5"));
					// sender.send(email);
					logger.logSuccessfulRegistration(name);
					return commonService.login(request, name, password, remember, model);
				}
				logger.logNoPassword(name);
				return "redirect:login";
			}
			logger.logInvalidEmailLogin(name, email);
			return "redirect:login";
		} catch (Exception e) {
			logger.error("registration failed", e);
			return "exception";
		}
	}

}