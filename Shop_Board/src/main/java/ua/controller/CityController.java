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
import ua.form.filter.CityFilterForm;
import ua.service.CityService;
import ua.service.impl.validator.CityValidator;


@Controller
public class CityController {
	
	@Autowired
	private CityService cityService;
	
	@ModelAttribute("city")
	public City getForm(){
		return new City();
	}
	
	@ModelAttribute("filter")
	public CityFilterForm getFilter(){
		return new CityFilterForm();
	}
	
	@InitBinder("city")
	protected void initBinder(WebDataBinder binder){
	   binder.setValidator(new CityValidator(cityService));
	}

	@RequestMapping("/admin/city")
	public String show(Model model,
			@PageableDefault(5) Pageable pageable,
			@ModelAttribute(value="filter") CityFilterForm form){
		model.addAttribute("page", cityService.findAll(pageable, form));
		return "adminCity";
	}
	
	@RequestMapping("/admin/city/delete/{id}")
	public String delete(@PathVariable int id,
			@PageableDefault(5) Pageable pageable,
			@ModelAttribute(value="filter") CityFilterForm form){
		cityService.delete(id);
		return "redirect:/admin/city"+getParams(pageable, form);
	}
	
	@RequestMapping(value= "/admin/city", method=RequestMethod.POST)
	public String save(@ModelAttribute("city") @Valid City city,
			BindingResult br,
			@PageableDefault(5) Pageable pageable,
			@ModelAttribute(value="filter") CityFilterForm form,
			Model model){
		if(br.hasErrors()){
			model.addAttribute("page", cityService.findAll(pageable, form));
			return "adminCity";
		}
		cityService.save(city);
		return "redirect:/admin/city"+getParams(pageable, form);
	}
	
	@RequestMapping("/admin/city/update/{id}")
	public String update(Model model,
			@PathVariable int id,
			@PageableDefault(5) Pageable pageable,
			@ModelAttribute(value="filter") CityFilterForm form){
		model.addAttribute("city", cityService.findOne(id));
		model.addAttribute("page", cityService.findAll(pageable, form));
		return "adminCity";
	}
	
	private String getParams(Pageable pageable, CityFilterForm form){
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
