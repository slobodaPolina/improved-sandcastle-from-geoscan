package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SearchController {

	@RequestMapping(value = "search", method = RequestMethod.POST)

	public ModelAndView index(@RequestParam String req) throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		System.out.println("Search controller");
		ModelAndView model = new ModelAndView("index");
		DBConnector connector = new DBConnector();
		ArrayList<Lesson> resp = connector.Search(req);
		if(resp.size()==0) {
			System.out.println("oh it might be your spelling mistake..");
		}
		model.addObject("list", resp);
		return model;
	}

}