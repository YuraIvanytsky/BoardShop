package ua.controller;

import java.security.Principal;

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

import ua.entity.Category;
import ua.entity.User;
import ua.form.CommodityForm;
import ua.form.filter.CommodityFilterForm;
import ua.service.CategoryService;
import ua.service.CommodityService;
import ua.service.UserService;
import ua.service.impl.editor.CategoryEditor;
import ua.service.impl.editor.UserEditor;
import ua.service.impl.validator.CommodityFormValidator;

@Controller
public class UserPanelController {
	@Autowired
	private CommodityService comService;
	
	@Autowired
	private CategoryService catService;
	
	@Autowired
	private UserService userService;
	
	@InitBinder("form")
	protected void initBinder(WebDataBinder binder){
	   binder.registerCustomEditor(Category.class, new CategoryEditor(catService));
	   binder.registerCustomEditor(User.class, new UserEditor(userService));
	   binder.setValidator(new CommodityFormValidator(comService));
	}
	
	@ModelAttribute("form")
	public CommodityForm getForm(){
		return new CommodityForm();
	}
	
	@ModelAttribute("filter")
	public CommodityFilterForm getFilter(){
		return new CommodityFilterForm();
	}
	
	@RequestMapping("/user/commodity")
	public String showCommodity(Model model,
			@PageableDefault(5) Pageable pageable,
			@ModelAttribute(value="filter") CommodityFilterForm form){
		model.addAttribute("page", comService.findAll(form, pageable));
		model.addAttribute("categories", catService.findAll());
		return "userCommodity";
	}
	
	@RequestMapping(value="/user/commodity", method=RequestMethod.POST)
	public String save(@ModelAttribute("form") @Valid CommodityForm form, Principal principal,
			BindingResult br, Model model,
			@PageableDefault(5) Pageable pageable,
			@ModelAttribute(value="filter") CommodityFilterForm filter){
		if(br.hasErrors()){
			model.addAttribute("page", comService.findAll(filter, pageable));
			model.addAttribute("categories", catService.findAll());
			return "userCommodity";
		}
		String name = principal.getName();
		User user = userService.findById(Integer.valueOf(name));
		form.setUser(user);
		comService.save(form);
		return "redirect:/user/commodity" + getParams(pageable, filter);
	}
	
	@RequestMapping(value="/user/commodity/update/{id}")
	public String update(Model model, @PathVariable int id,
			@PageableDefault(5) Pageable pageable,
			@ModelAttribute(value="filter") CommodityFilterForm form){
		model.addAttribute("form", comService.findForForm(id));
		model.addAttribute("page", comService.findAll(form, pageable));
		model.addAttribute("users", userService.findAll());
		model.addAttribute("categories", catService.findAll());
		return "userCommodity";
	}
	
	@RequestMapping(value="/user/commodity/delete/{id}")
	public String delete(@PathVariable int id,
			@PageableDefault(5) Pageable pageable,
			@ModelAttribute(value="filter") CommodityFilterForm form){
		comService.delete(id);
		return "redirect:/user/commodity"+getParams(pageable, form);
	}
	
	private String getParams(Pageable pageable, CommodityFilterForm form){
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
