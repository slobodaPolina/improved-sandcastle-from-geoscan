package service;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class CommonService {
	@Autowired
	private DBConnector connector;

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

	public boolean IsSessionActive(HttpServletRequest request) {
		try {
			HttpSession session = request.getSession();
			return connector.IsTheSessionActive((String) (session.getAttribute("name")), session.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public void saveSession(HttpServletRequest request, String name) {
		try {
			HttpSession session = request.getSession();
			connector.storeSession(name, session.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//it is not such useful method now
	public Model fillModel(String userName, Model model) {
		model.addAttribute("name", userName);
		return model;
	}


	public boolean getRememberStatus(String userName) {
		try {
			return connector.findRememberStatus(userName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}