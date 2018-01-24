package controller;

import java.io.File;

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
			if ("image/jpeg".equals(photo.getContentType())) {
				String filePath = request.getServletContext().getRealPath("/") + "uploads\\";
				photo.transferTo(new File(filePath + request.getSession().getAttribute("name") + ".jpg"));
				System.out.println("It is ok, the file was successfully uploaded!");
			} else {
				System.out.println("I do not support such file types, please choose another file");
			}
			return "redirect:settings";
		} catch (Exception e) {
			return commonService.handleException(e, model);
		}
	}

}