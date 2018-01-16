package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import dao.UserDao;
import service.CommonService;
import service.PasswordHasher;

@Controller
public class PassChangeController {
	@Autowired
	private CommonService commonService;
	@Autowired
	private UserDao userDao;

	@RequestMapping(value = "passchange", method = RequestMethod.POST)
	public String change(Model model, @RequestParam String oldPassword, @RequestParam String newPassword,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			System.out.println("------ PASSCHANGE CONTROLLER ------");
			String name = (String) request.getSession().getAttribute("name");
			if (name != null) {
				if (PasswordHasher.hash(oldPassword, "MD5").equals(userDao.getByName(name).getPassword())) {
					userDao.setNewPassword(name, PasswordHasher.hash(newPassword, "MD5"));
					System.out.println("Your password has been changed");
				} else {
					System.out.println("You have inputted incorrect password");
				}
			} else {
				System.out.println("Your session doesn`t contain your name..");
			}
			return "index";
		} catch (Exception e) {
			return commonService.handleException(e, model);
		}
	}

}