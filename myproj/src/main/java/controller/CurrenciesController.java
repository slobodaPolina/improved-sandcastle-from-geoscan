package controller;

import java.security.Principal;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import helpful.Currency;
import service.CommonService;
import service.SoapCurrenciesBrowser;

@Controller
public class CurrenciesController {
	@Autowired
	SoapCurrenciesBrowser browser;
	@Autowired
	CommonService commonService;

	@RequestMapping(value = "currencies", method = RequestMethod.GET)

	public String Currencies(HttpServletRequest request, Model model, Principal principal) {
		try {
			if (principal != null) {
				ArrayList<Currency> list = browser.getCbrInfo();
				model.addAttribute("list", list);
				System.out.println("Your session is ok");
				return "currencies";
			} else {
				System.out.println("You have loged out! Log in again,  please");
				return "redirect:login";
			}
		} catch (Exception e) {
			return commonService.handleException(e, model);
		}
	}

}