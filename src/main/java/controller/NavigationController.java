package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NavigationController {
	
	@RequestMapping(value= {"/index","/"})
	public String index() {
		return "index";	
	}
	
	@RequestMapping(value= "/add")
	public String survey() {
		return "add";
	}
}
