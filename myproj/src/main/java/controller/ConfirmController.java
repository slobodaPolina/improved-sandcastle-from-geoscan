package controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import service.CommonService;
import service.DBConnector;

@Controller
public class ConfirmController {
	@Autowired
	private CommonService commonService;
	@Autowired
	private DBConnector connector;

	@RequestMapping(value = "confirm", method = RequestMethod.GET)
	public String confirm(Model model, @RequestParam String name, @RequestParam int code,
			HttpServletResponse response) {
		try {
			System.out.println("------ CONFIRM CONTROLLER ------");
			if (code == connector.getCode(name)) {
				model.addAttribute("text",
						"Hello, " + name + "! Thanks for confirming your email. You can now log in.");
				connector.setConfirmingStatus(name);
			} else {
				model.addAttribute("text", "Hello, " + name
						+ "! I am sorry to tell you your confirming is not successfull, probably your confirming code is incorrect.");
			}
			return "confirm";
		} catch (Exception e) {
			return commonService.handleException(e, model);
		}
	}

}