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
public class IndexController {
	@Autowired
	private CommonService commonService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model, HttpServletRequest request, HttpServletResponse response) {
		try {
			System.out.println("------ INDEX CONTROLLER ------");
			if (commonService.isTheUserAuthorised(request)) {
				return "hello";
			}
			return "redirect:login";
		} catch (Exception e) {
			return commonService.handleException(e, model);
		}
	}

}