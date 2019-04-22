package service;

import java.security.NoSuchAlgorithmException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import dao.UserDao;

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

	public void successfulLogin(HttpServletRequest request, String name) throws NoSuchAlgorithmException {
		logger.logSuccessfulAuthorisation(name);
		HttpSession session = request.getSession(true);
		session.setAttribute("name", name);
	}

	public String handleException(Exception e, Model model) {
		logger.error("internal error occured", e);
		model.addAttribute("exception", e.getMessage());
		return "exception";
	}
}