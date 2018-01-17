package controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dao.MessageDao;
import entity.Message;
import service.CommonService;
import service.SoapCurrenciesBrowser;

@Controller
public class MessagesController {
	@Autowired
	SoapCurrenciesBrowser browser;
	@Autowired
	CommonService commonService;
	@Autowired
	MessageDao messageDao;

	@RequestMapping(value = "messages", method = RequestMethod.GET)

	public String MessagesCont(HttpServletRequest request, Model model, Principal principal) {
		try {
			if (principal != null) {
				List<Message> list = messageDao.getAll();
				model.addAttribute("list", list);
				System.out.println("Your session is ok");
				return "messages";
			} else {
				System.out.println("You have loged out! Log in again,  please");
				return "redirect:login";
			}
		} catch (Exception e) {
			return commonService.handleException(e, model);
		}
	}

}