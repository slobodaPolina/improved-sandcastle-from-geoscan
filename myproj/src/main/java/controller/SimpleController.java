package controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import service.CommonService;

@Controller
public class SimpleController {
	@Autowired
	private CommonService commonService;

	@RequestMapping(value = { "animals", "animation", "bootstrappage", "cats", "flexpage", "hello", "scripts",
			"sandbox" }, method = { RequestMethod.GET })
	public String SimpleCont(HttpServletRequest request, Model model, Principal principal) {
		try {
			System.out.println("------ SIMPLE CONTROLLER ------");
			if (principal != null) {
				System.out.println("Your session is ok");
				return commonService.getRequestedPage(request);
			} else {
				System.out.println("You have loged out! Log in again,  please");
				return "redirect:login";
			}
		} catch (Exception e) {
			return commonService.handleException(e, model);
		}
	}

}
