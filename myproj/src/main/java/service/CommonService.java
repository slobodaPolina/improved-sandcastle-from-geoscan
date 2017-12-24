package service;

import java.security.NoSuchAlgorithmException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class CommonService {
	@Autowired
	DBConnector connector;
	@Autowired
	PasswordHasher ph;
	@Autowired
	private MyLogger logger;

	public String getRequestedPage(HttpServletRequest request) {
		String[] requestArray = request.getRequestURL().toString().split("/");
		String view = requestArray[requestArray.length - 1];
		return view;
	}

	public boolean hasParameter(HttpServletRequest request, String param) {
		String tmp;
		Enumeration<String> en = request.getParameterNames();
		while (en.hasMoreElements()) {
			tmp = en.nextElement();
			if (tmp.equals(param)) {
				return true;
			}
		}
		return false;
	}

	public String login(HttpServletRequest request, String name, String password, String remember, Model model) {
		try {
			String hashedPassword = ph.hash(password, "MD5");
			String res = connector.findPassword(name);
			if (res.equals(hashedPassword)) {
				logger.logSuccessfulAuthorisation(name);
				HttpSession session = request.getSession(true);
				session.setAttribute("name", name);
				session.setAttribute("status", "authorised");
				if ("true".equals(remember))
					request.getSession().setMaxInactiveInterval(Integer.MAX_VALUE);
				else
					request.getSession().setMaxInactiveInterval(5);// 1800
				return "redirect:/";
			} else {
				logger.logInvalidPassword(name);
				return "redirect:login";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("exception", e.getMessage());
			return "exception";
		}
	}

	public boolean isTheUserAuthorised(HttpServletRequest request) {
		// here i wanna check if the user is authorised but not sure if
		// it is correct
		String status = (String) request.getSession().getAttribute("status");
		return (status == "authorised");
	}
}