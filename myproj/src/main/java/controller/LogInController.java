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

@Controller
public class LogInController {
	@Autowired
	private CommonService commonService;
	@Autowired
	private DBConnector connector;
	@Autowired
	private CookieUtils cookieUtils;

	@RequestMapping(value = "login", method = { RequestMethod.GET, RequestMethod.POST })
	public String logIn(HttpServletRequest request, HttpServletResponse response, Model model) {
		try {
			System.out.println("LOGIN CONTROLLER");
			// it is logging in
			if (commonService.hasParameter(request, "name") && commonService.hasParameter(request, "password1")) {
				String userName = request.getParameter("name");
				String password = request.getParameter("password1");
				String res = connector.findPassword(userName);
				PasswordHasher ph = new PasswordHasher("MD5");
				password = ph.hash(password);
				if (res.equals(password)) {
					System.out.println("You successfully have logged in");
					commonService.fillModel(userName, model);
					HttpSession session = request.getSession(true);
					session.setAttribute("name", userName);
					commonService.saveSession(request, userName);
					if (commonService.getRememberStatus(userName)) {
						cookieUtils.SetCookies(request, response);
						System.out.println("I have stored your data in your cookies");
					}
					return "hello";
				} else {
					System.out.println("Your password is wrong!");
					return "index";
				}
			} else {// it means the user is authorised and has pressed the button "main page"
				if (commonService.IsSessionActive(request)) {
					return "hello";
				} else {
					return "index";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "exception";
		}
		// наследование спп: одинаковые методы родителей?
	}

}