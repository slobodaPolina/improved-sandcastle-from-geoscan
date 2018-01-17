package controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import service.CommonService;
import service.MyLogger;

@Controller
public class AddPhotoController {
	@Autowired
	MyLogger logger;
	@Autowired
	CommonService commonService;

	@RequestMapping(value = "addphoto", method = RequestMethod.POST)
	public String logout(MultipartHttpServletRequest request, @RequestParam MultipartFile photo,
			HttpServletResponse response, Model model) {
		System.out.println("------ ADD PHOTO CONTROLLER ------");
		try {
			if ("jpg".equals(photo.getContentType())) {
				photo.transferTo(new File(request.getSession().getAttribute("name") + ".jpg"));
			}
			return "redirect:settings";
		} catch (Exception e) {
			return commonService.handleException(e, model);
		}
	}

}