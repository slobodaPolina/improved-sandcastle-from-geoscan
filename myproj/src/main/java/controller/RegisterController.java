package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import helpful.PasswordHasher;
import service.CommonService;
import service.CookieUtils;
import service.DBConnector;
import service.EmailSender;

@Controller
public class RegisterController {
	@Autowired
	EmailSender sender;
	@Autowired
	private CommonService commonService;
	@Autowired
	private DBConnector connector;
	@Autowired
	private CookieUtils cookieUtils;

	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String Register(HttpServletRequest request, HttpServletResponse response, Model model) {
		try {
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String password = request.getParameter("password1");
			String remember = request.getParameter("remember");
			boolean exists = connector.exists(name, email);
			if (!exists) {
				PasswordHasher ph = new PasswordHasher("MD5");
				password = ph.hash(password);
				connector.insertUser(name, email, password, remember.toString());
				HttpSession session = request.getSession(true);
				session.setAttribute("name", name);
				commonService.saveSession(request, name);
				sender.send(email);
				if ("true".equals(remember)) {
					cookieUtils.SetCookies(request, response);
					System.out.println("I have stored your data in your cookies");
				}
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