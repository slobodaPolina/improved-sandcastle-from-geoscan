package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import service.CommonService;

@Controller
public class LogInController {
	@Autowired
	private CommonService commonService;

	@RequestMapping(value = "login", method = { RequestMethod.GET })
	public String logIn(HttpServletRequest request, HttpServletResponse response, Model model) {
		try {
			System.out.println("------ LOGIN CONTROLLER ------");
			/*String userName, password, remember = "false";
			// TODO check if here are params?
			if (commonService.hasParameter(request, "name") && commonService.hasParameter(request, "password")) {
				userName = request.getParameter("name");
				password = request.getParameter("password");
				if (commonService.hasParameter(request, "remember"))
					remember = "true";
				return commonService.login(request, userName, password, remember, model);
			}
			return "index";*/
			return "index";
		} catch (Exception e) {
			return commonService.handleException(e, model);
		}
	}

}