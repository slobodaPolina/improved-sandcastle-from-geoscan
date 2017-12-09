package controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import helpful.CookieUtils;
import helpful.DBConnector;
import service.CommonService;
import service.SoapCurrenciesBrowser;

@Controller
public class IndexController {

	@Autowired
	SoapCurrenciesBrowser browser;
	@Autowired
	private CommonService commonService;

	@RequestMapping(value = "/", method = { RequestMethod.GET, RequestMethod.POST })
	public String index(Model model, HttpServletRequest request)
			throws IOException, SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {

		System.out.println("I am in Index Controller");
		// searching in request params this exit and if i found it return index. if
		// no exit there, try to authorise. Ofc when calling this controller send exit
		// on exit)))
		try {
			if (commonService.hasParameter(request, "exit")) {
				System.out.println("You have exited just now, so i won`t try to authorise u again");
				return "index";
			}
			String[] array = CookieUtils.hasIt(request);
			String userName = array[0];
			String userPass = array[1];
			if (("").equals(userName) || ("").equals(userPass)) {
				System.out.println("No userName or userPass, I cant autorise you");
			} else {
				DBConnector connector = new DBConnector();
				String dataPass = connector.findPassword(userName);
				if (dataPass.equals(userPass)) {
					System.out.println("I found the data to autorise you!");
					model = commonService.fillModel(userName, model);
					return "hello";
				} else {
					System.out.println("Hmm, incorrect password");
				}
			}
			return "index";
		} catch (Exception e) {
			e.printStackTrace();
			return "exception";
		}
	}

}