package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LogoutController {
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logIn(HttpServletRequest request, Model model) {
		try {
			HttpSession session = request.getSession();
			if (session != null) {
				session.invalidate();
			}
			return "index";
		} catch (Exception e) {
			e.printStackTrace();
			return "exception";
		}
		// наследование спп: одинаковые методы родителей?
	}

}