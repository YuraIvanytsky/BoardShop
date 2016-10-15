package ua.service.impl.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.entity.City;
import ua.service.CityService;

public class CityValidator implements Validator {

	private final CityService cityService;

	public CityValidator(CityService cityService) {
		this.cityService = cityService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return City.class.equals(clazz);
	}
	

	@Override
	public void validate(Object target, Errors errors) {
		City form = (City) target;
		if(form.getId()==0)if(cityService.findByName(form.getName())!=null){
			errors.rejectValue("name", "", "City already exists");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "", "Can`t be empty");
	}

}
