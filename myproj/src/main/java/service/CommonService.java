package service;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import helpful.DBConnector;

@Service
public class CommonService {

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
			DBConnector conn = new DBConnector();
			return conn.IsTheSessionActive((String) (session.getAttribute("name")), session.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public void saveSession(HttpServletRequest request, String name) {
		try {
			HttpSession session = request.getSession();
			DBConnector conn = new DBConnector();
			conn.storeSession(name, session.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Model fillModel(String userName, Model model) {
		return fillModel(userName, getRememberStatus(userName), model);
	}

	public Model fillModel(String userName, boolean remember, Model model) {
		model.addAttribute("name", userName);
		model.addAttribute("remember", remember);
		return model;
	}

	private boolean getRememberStatus(String userName) {
		try {
			DBConnector connector = new DBConnector();
			return connector.findRememberStatus(userName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}