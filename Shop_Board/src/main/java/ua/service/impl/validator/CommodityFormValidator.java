package ua.service.impl.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.form.CommodityForm;
import ua.service.CommodityService;

public class CommodityFormValidator implements Validator{
	
	private final CommodityService commodityService;
	
	public CommodityFormValidator(CommodityService commodityService) {
		this.commodityService = commodityService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return CommodityForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		CommodityForm form = (CommodityForm) target;
		if(form.getId()==0)if(commodityService.findByTitle(form.getTitle())!=null){
			errors.rejectValue("title", "", "Commodity already exists");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "", "Can`t be empty");
	}
}
