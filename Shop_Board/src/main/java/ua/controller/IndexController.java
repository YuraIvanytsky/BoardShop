package ua.controller;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.form.filter.CommodityFilterForm;
import ua.service.CategoryService;
import ua.service.CommodityService;
import ua.service.UserService;

@Controller
public class IndexController {
	
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private CommodityService commodityService;
	@Autowired
	private UserService userService;
	
	
	@RequestMapping("/")
	public String showIndex(Principal principal, Model model,
			@PageableDefault(5) Pageable pageable,
			@ModelAttribute(value="filter") CommodityFilterForm filter){
		System.out.println(principal);
		System.out.println(System.getProperty("catalina.home"));
		model.addAttribute("categories", categoryService.findAll())
		.addAttribute("page", commodityService.findAll(filter, pageable));
		return "index";
	}
	@RequestMapping("/user/commodity/{id}")
	public String showIndex(Model model, @PathVariable int id){
		model.addAttribute("commodity", commodityService.findById(id));
		return "allInformation";
	}
	@RequestMapping("/admin")
	public String showAdmin() {
		return "adminPanel";
	}
	
	@RequestMapping("/user")
	public String showUser() {
		return "userPanel";
	}
	
	@RequestMapping("/login")
	public String showLogin() {
		return "login";
	}
	
}
