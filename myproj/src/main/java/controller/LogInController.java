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
import service.Logger;
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
	@Autowired
	private Logger logger;

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
				} else {// he is registrating or authorised from the cookies
					userName = (String) request.getSession().getAttribute("name");
					password = (String) request.getSession().getAttribute("password");
					remember = (String) request.getSession().getAttribute("remember");
				}
				String res = connector.findPassword(userName);
				String hashedPassword = ph.hash(password, "MD5");
				// !! I dont know exactly if the password here hashed or not. If the user is
				// authorised from the index by cookies password is hashed otherwise it is not
				if (res != "" && (res.equals(password) || res.equals(hashedPassword))) {
					logger.logSuccessfulAuthorisation(userName);
					connector.storeRememberStatus(userName, remember);
					commonService.fillModel(userName, model);
					HttpSession session = request.getSession(true);
					session.setAttribute("name", userName);
					commonService.saveSession(request, userName);
					if (commonService.getRememberStatus(userName)) {
						cookieUtils.SetCookies(request, response);
					}
					return "hello";
				} else {
					logger.logInvalidPassword(userName);
				}
			} else {// it means the user is authorised and has pressed the button "main page"
				if (commonService.IsSessionActive(request))
					return "hello";
			}
			return "redirect:index";
		} catch (Exception e) {
			e.printStackTrace();
			return "exception";
		}
		// наследование спп: одинаковые методы родителей?
	}

}