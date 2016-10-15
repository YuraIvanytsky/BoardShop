package ua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ua.service.UserService;
import ua.service.impl.MailSender;


@Controller
public class MailController {
	@Autowired
	private MailSender mailSender;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/admin/send")
	public String showSend(Model model){
		model.addAttribute("users", userService.findAll());
		return "adminSend";
	}
	
	@RequestMapping(value= "/admin/send", method=RequestMethod.POST)
	public String send(@RequestParam String content, @RequestParam String mail, @RequestParam String mailBody){
		mailSender.sendMail(content, mail, mailBody);
		return "redirect:/admin/send";
	}
}
