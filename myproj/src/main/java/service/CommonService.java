package service;

import java.security.NoSuchAlgorithmException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import dao.UserDao;
import entity.User;

@Service
public class CommonService {
	@Autowired
	UserDao userDao;
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

	public String login(HttpServletRequest request, String name, String password, String remember, Model model)
			throws NoSuchAlgorithmException {
			String hashedPassword = ph.hash(password, "MD5");
			User user = userDao.getByName(name);
			if (user == null) {
				return "redirect:login";
			}
			String res = user.getPassword();
			if (res.equals(hashedPassword)) {
				logger.logSuccessfulAuthorisation(name);
				HttpSession session = request.getSession(true);
				session.setAttribute("name", name);
				if ("true".equals(remember))
					request.getSession().setMaxInactiveInterval(Integer.MAX_VALUE);
				else
					request.getSession().setMaxInactiveInterval(5);// 1800
				return "redirect:/";
			} else {
				logger.logInvalidPassword(name);
				return "redirect:login";
			}
	}

	public boolean isTheUserAuthorised(HttpServletRequest request) {
		String status = (String) request.getSession().getAttribute("status");
		return (status == "authorised");
	}

	public String handleException(Exception e, Model model) {
		logger.error("internal error occured", e);
		model.addAttribute("exception", e.getMessage());
		return "exception";
	}
}