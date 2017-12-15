package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import service.CommonService;
import service.CookieUtils;
import service.DBConnector;
import service.PasswordHasher;

@Controller
public class LogInController {
	@Autowired
	private CommonService commonService;
	@Autowired
	private DBConnector connector;
	@Autowired
	private CookieUtils cookieUtils;
	@Autowired
	private PasswordHasher ph;

	@RequestMapping(value = "login", method = { RequestMethod.GET, RequestMethod.POST })
	public String logIn(HttpServletRequest request, HttpServletResponse response, Model model) {
		try {
			System.out.println("------ LOGIN CONTROLLER ------");
			// it is logging in
			if ((commonService.hasParameter(request, "name") && commonService.hasParameter(request, "password"))
					|| (request.getSession().getAttribute("name") != null
							&& request.getSession().getAttribute("password") != null)) {
				String userName, password, remember = "false";
				// it means the user is just logging in
				if (commonService.hasParameter(request, "name") && commonService.hasParameter(request, "password")) {
					userName = request.getParameter("name");
					password = request.getParameter("password");
					if (commonService.hasParameter(request, "remember"))
						remember = "true";
				} else {// he is registrating
					userName = (String) request.getSession().getAttribute("name");
					password = (String) request.getSession().getAttribute("password");
					remember = (String) request.getSession().getAttribute("remember");
				}
				String res = connector.findPassword(userName);
				password = ph.hash(password, "MD5");
				if (res.equals(password)) {
					System.out.println("You successfully have logged in");
					connector.storeRememberStatus(userName, remember);
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
				}
			} else {// it means the user is authorised and has pressed the button "main page"
				System.out.println("You are authorised");
				if (commonService.IsSessionActive(request))
					return "hello";
				else
					System.out.println("Has your session time ended?..");
			}
			return "redirect:index";
		} catch (Exception e) {
			e.printStackTrace();
			return "exception";
		}
		// наследование спп: одинаковые методы родителей?
	}

}