package controller;

import java.io.IOException;
import java.sql.SQLException;
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

	// TO DO: change the constructors so that the index is login one
	@RequestMapping(value = { "/", "index" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String index(Model model, HttpServletRequest request, HttpServletResponse response)
			throws IOException, SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		System.out.println("------ INDEX CONTROLLER ------");
		if (commonService.isTheUserAuthorised(request)) {//не видит здесь, что сессия активна. Не видит после разлогированияв соседней вкладке, что надо залогироваться
			return "hello";
		}
		return "redirect:login";
	}

}