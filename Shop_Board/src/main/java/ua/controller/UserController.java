package ua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import ua.entity.User;
import ua.service.UserService;
import ua.service.CityService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CityService cityService;

	@ModelAttribute("user")
	public User getForm(){
		return new User();
	}
	
	@RequestMapping("/registration")
	public String showCity(Model model){
		model.addAttribute("cities", cityService.findAll());
		return "registration";
	}

	@RequestMapping(value="/registration", method=RequestMethod.POST)
	public String save(@ModelAttribute("user") User user, @RequestParam int cityId){
		userService.save(user, cityId);
		return "redirect:/login";
	}
}
