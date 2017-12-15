package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import service.CommonService;
import service.DBConnector;
import service.EmailSender;
import service.PasswordHasher;

@Controller
public class RegisterController {
	@Autowired
	EmailSender sender;
	@Autowired
	private DBConnector connector;
	@Autowired
	private PasswordHasher ph;
	@Autowired
	private CommonService commonService;

	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String Register(HttpServletRequest request, HttpServletResponse response, Model model) {
		System.out.println("------ REGISTER CONTROLLER ------");
		try {
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			String remember = "false";
			if (commonService.hasParameter(request, "remember"))
				remember = "true";
			
			boolean exists = connector.exists(name, email);
			if (!exists) {
				connector.insertUser(name, email, ph.hash(password, "MD5"));
//				sender.send(email);
				System.out.println("It`s ok, i have created your account");
				request.getSession().setAttribute("name", name);
				request.getSession().setAttribute("password", password);
				request.getSession().setAttribute("remember", remember);
				return "redirect:login";
			}
			System.out.println("Seems like your user name or your email is used already");
			return "redirect:/";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "exception";
	}

}