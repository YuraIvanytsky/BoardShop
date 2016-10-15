package ua.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.entity.City;
import ua.form.UserForm;
import ua.form.filter.UserFilterForm;
import ua.service.CityService;
import ua.service.UserService;
import ua.service.impl.editor.CityEditor;
import ua.service.impl.validator.UserFormValidator;


@Controller
public class AdminUserController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private CityService cityService;
	
	
	
	@InitBinder("form")
	protected void initBinder(WebDataBinder binder){
	   binder.registerCustomEditor(City.class, new CityEditor(cityService));
	   binder.setValidator(new UserFormValidator(userService));
	}
	
	@ModelAttribute("form")
	public UserForm getForm(){
		return new UserForm();
	}
	
	@ModelAttribute("filter")
	public UserFilterForm getFilter(){
		return new UserFilterForm();
	}
	
	@RequestMapping("/admin/user")
	public String show(Model model,
			@PageableDefault(5) Pageable pageable,
			@ModelAttribute(value="filter") UserFilterForm form){
		model.addAttribute("page", userService.findAll(form, pageable));
		model.addAttribute("cities", cityService.findAll());
		return "adminUser";
	}
	
	@RequestMapping(value="/admin/user", method=RequestMethod.POST)
	public String save(@ModelAttribute("form") @Valid UserForm form,
			BindingResult br, Model model,
			@PageableDefault(5) Pageable pageable,
			@ModelAttribute(value="filter") UserFilterForm filter){
		if(br.hasErrors()){
			model.addAttribute("page", userService.findAll(filter, pageable));
			model.addAttribute("cities", cityService.findAll());
			return "adminUser";
		}
		userService.save(form);
		return "redirect:/admin/user" + getParams(pageable, filter);
	}
	
	@RequestMapping(value="/admin/user/delete/{id}")
	public String delete(@PathVariable int id,
			@PageableDefault(5) Pageable pageable,
			@ModelAttribute(value="filter") UserFilterForm form){
		userService.delete(id);
		return "redirect:/admin/user"+getParams(pageable, form);
	}
	
	@RequestMapping(value="/admin/user/update/{id}")
	public String update(Model model, @PathVariable int id,
			@PageableDefault(5) Pageable pageable,
			@ModelAttribute(value="filter") UserFilterForm form){
		model.addAttribute("form", userService.findForForm(id));
		model.addAttribute("page", userService.findAll(form, pageable));
		model.addAttribute("cities", cityService.findAll());
		return "adminUser";
	}
	
	private String getParams(Pageable pageable, UserFilterForm form){
		StringBuilder buffer = new StringBuilder();
		buffer.append("?page=");
		buffer.append(String.valueOf(pageable.getPageNumber()+1));
		buffer.append("&size=");
		buffer.append(String.valueOf(pageable.getPageSize()));
		if(pageable.getSort()!=null){
			buffer.append("&sort=");
			Sort sort = pageable.getSort();
			sort.forEach((order)->{
				buffer.append(order.getProperty());
				if(order.getDirection()!=Direction.ASC)
				buffer.append(",desc");
			});
		}
		return buffer.toString();
	}
}
