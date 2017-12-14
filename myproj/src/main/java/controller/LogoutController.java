package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LogoutController {
	@RequestMapping(value = "logout", method = RequestMethod.POST)
	public String logout(HttpServletRequest request, HttpServletResponse response, Model model) {
		try {
			HttpSession session = request.getSession();
			if (session != null) {
				session.invalidate();
			}
			request.getSession().setAttribute("exit", "true");
			return "redirect:/";
		} catch (Exception e) {
			e.printStackTrace();
			return "exception";
		}
		// наследование спп: одинаковые методы родителей?
	}

}