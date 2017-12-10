package controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import service.CommonService;

@Controller
public class BootstrappageController {
	@Autowired
	private CommonService commonService;

	@RequestMapping(value = "bootstrappage", method = { RequestMethod.GET, RequestMethod.POST })
	public String BootstrapContr(HttpServletRequest request, Model model) {
		try {
			if (commonService.IsSessionActive(request)) {
				System.out.println("Your session is ok");
				return "bootstrappage";
			} else {
				System.out.println("You have loged out! Log in again,  please");
				return "index";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "exception";
		}
	}

}