package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import helpful.CookieUtils;
import helpful.Currency;
import helpful.DBConnector;
import helpful.SoapCurrenciesBrowser;

@Controller
public class IndexController {

	@RequestMapping(value = "/", method = RequestMethod.GET)

	public ModelAndView index(HttpServletRequest request)
			throws IOException, SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		ModelAndView model;
		System.out.println("I am in Index Controller");
		String[] array = CookieUtils.hasIt(request);
		String userName = array[0];
		String userPass = array[1];
		if (userName.equals("") || userPass.equals("")) {
			model = new ModelAndView("index");
			System.out.println("No userName or userPass, I cant autorise you");
		} else {// Here are a lot of mistakes, so i am gonna change the code somehow..
			DBConnector connector = new DBConnector();
			String dataPass = connector.findpassword(userName);
			if (dataPass.equals(userPass)) {
				System.out.println("I found the data to autorise you!");
				model = new ModelAndView("hello");
				model.addObject("name", userName);
				SoapCurrenciesBrowser browser = new SoapCurrenciesBrowser();
				ArrayList<Currency> list = browser.getresult();
				model.addObject("list", list);
				model.addObject("remember", connector.remember(userName));

			} else {
				System.out.println("Hmm, incorrect password");
				model = new ModelAndView("index");
			}
		}
		return model;
	}

}