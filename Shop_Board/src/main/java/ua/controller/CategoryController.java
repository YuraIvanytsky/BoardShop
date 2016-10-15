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

import ua.entity.Category;
import ua.form.filter.CategoryFilterForm;
import ua.service.CategoryService;
import ua.service.impl.validator.CategoryValidator;

@Controller
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@ModelAttribute("category")
	public Category getForm(){
		return new Category();
	}
	
	@ModelAttribute("filter")
	public CategoryFilterForm getFilter(){
		return new CategoryFilterForm();
	}
	
	@InitBinder("category")
	protected void initBinder(WebDataBinder binder){
	   binder.setValidator(new CategoryValidator(categoryService));
	}

	@RequestMapping("/admin/category")
	public String show(Model model,
			@PageableDefault(5) Pageable pageable,
			@ModelAttribute(value="filter") CategoryFilterForm form){
		model.addAttribute("page", categoryService.findAll(pageable, form));
		return "adminCategory";
	}
	
	@RequestMapping("/admin/category/delete/{id}")
	public String delete(@PathVariable int id,
			@PageableDefault(5) Pageable pageable,
			@ModelAttribute(value="filter") CategoryFilterForm form){
		categoryService.delete(id);
		return "redirect:/admin/category"+getParams(pageable, form);
	}
	
	@RequestMapping("/admin/category/update/{id}")
	public String update(Model model,
			@PathVariable int id,
			@PageableDefault(5) Pageable pageable,
			@ModelAttribute(value="filter") CategoryFilterForm form){
		model.addAttribute("category", categoryService.findOne(id));
		model.addAttribute("page", categoryService.findAll(pageable, form));
		return "adminCategory";
	}
	
	@RequestMapping(value= "/admin/category", method=RequestMethod.POST)
	public String save(@ModelAttribute("category") @Valid Category category,
			BindingResult br,
			@PageableDefault(5) Pageable pageable,
			@ModelAttribute(value="filter") CategoryFilterForm form,
			Model model){
		if(br.hasErrors()){
			model.addAttribute("page", categoryService.findAll(pageable, form));
			return "adminCategory";
		}
		categoryService.save(category);
		return "redirect:/admin/category"+getParams(pageable, form);
	}
	
	private String getParams(Pageable pageable, CategoryFilterForm form){
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
		buffer.append("&search=");
		buffer.append(form.getSearch());
		return buffer.toString();
	}
}
