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

@Controller
public class LogInController {
	@Autowired
	private CommonService commonService;

	@RequestMapping(value = "hello", method = { RequestMethod.GET, RequestMethod.POST })
	public String logIn(HttpServletRequest request, Model model) {
		try {
			// it is logging in
			if (commonService.hasParameter(request, "name") && commonService.hasParameter(request, "password")) {
				String name = request.getParameter("name");
				String password = request.getParameter("password");
				HttpSession session = request.getSession(true);
				session.setAttribute("name", name);
				commonService.saveSession(request, name);
				DBConnector connector = new DBConnector();
				String res = connector.findPassword(name);
				PasswordHasher ph = new PasswordHasher("MD5");
				password = ph.hash(password);
				if (res.equals(password)) {
					commonService.fillModel(name, model);
					System.out.println("You successfully have logged in");
					return "hello";
				} else {
					System.out.println("Ysour password is wrong!");
					return "index";
				}
			} else {// it means the user is authorised and has pressed the button
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