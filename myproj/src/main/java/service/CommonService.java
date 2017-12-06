package service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import helpful.Currency;
import helpful.DBConnector;

@Service
public class CommonService {

	@Autowired
	private SoapCurrenciesBrowser browser;

	public Model fillModel(String userName, Model model) {
		return fillModel(userName, getRememberStatus(userName), model);
	}

	public Model fillModel(String userName, boolean remember, Model model) {
		ArrayList<Currency> list = browser.getCbrInfo();
		model.addAttribute("name", userName);
		model.addAttribute("list", list);
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