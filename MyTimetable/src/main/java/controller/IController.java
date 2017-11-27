package controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IController {

	@RequestMapping(value = "/", method = RequestMethod.GET)

	public ModelAndView index(HttpServletRequest request) throws IOException {
		System.out.print("IController");
		ModelAndView model = new ModelAndView("index");
		return model;
	}

}