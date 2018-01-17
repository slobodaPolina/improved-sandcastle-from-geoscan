package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import dao.MessageDao;
import service.CommonService;

@Controller
public class NewMessageController {
	@Autowired
	private CommonService commonService;
	@Autowired
	private MessageDao messageDao;

	@RequestMapping(value = "newmessage", method = RequestMethod.GET)
	public String confirm(Model model, @RequestParam String message, HttpServletResponse response,
			HttpServletRequest request) {
		try {
			System.out.println("------ NEW MESSAGE CONTROLLER ------");
			// TODO: now there is no possibility to send the message to somebody certain, it
			// is like the post, but i would like to add it
			response.setContentType("text/html; charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			if (!"".equals(message))
				messageDao.create((String) request.getSession().getAttribute("name"), message);
			return "redirect:messages";
		} catch (Exception e) {
			return commonService.handleException(e, model);
		}
	}
}