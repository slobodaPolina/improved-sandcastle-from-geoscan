package controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import service.CommonService;
import service.CookieUtils;
import service.DBConnector;

@Controller
public class IndexController {
	@Autowired
	private CommonService commonService;
	@Autowired
	private DBConnector connector;
	@Autowired
	private CookieUtils cookieUtils;

	@RequestMapping(value = { "/", "index" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String index(Model model, HttpServletRequest request, HttpServletResponse response)
			throws IOException, SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {

		System.out.println("I am in Index Controller");
		// if here is an exit parameter it means the user has just logged out, i dont
		// have to authorise him
		try {
			if (request.getSession().getAttribute("exit") != null) {
				System.out.println("You have exited just now, so i won`t try to authorise u again");
				return "index";
			}
			String[] array = cookieUtils.getUserCookies(request);
			String userName = array[0];
			String userPass = array[1];
			if (("").equals(userName) || ("").equals(userPass)) {
				System.out.println("No userName or userPass, I cant authorise you");
			} else {
				String dataPass = connector.findPassword(userName);
				if (dataPass.equals(userPass)) {
					System.out.println("I found the data to authorise you!");
					model = commonService.fillModel(userName, model);
					if (commonService.getRememberStatus(userName)) {
						cookieUtils.SetCookies(request, response);
						System.out.println("I have stored your data in your cookies");
					}
					return "hello";
				} else {
					System.out.println("Your password is incorrect, please try again");
				}
			}
			return "index";
		} catch (Exception e) {
			e.printStackTrace();
			return "exception";
		}
	}

}