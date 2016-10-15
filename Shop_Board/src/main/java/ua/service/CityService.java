package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.entity.City;
import ua.form.filter.CityFilterForm;

public interface CityService {
	void save(String name);
	List<City> findAll();
	City findByName(String name);
	void delete(int id);
	void deleteAll();
	Page<City> findAll(Pageable pageable, CityFilterForm form);
	void save(City city);
	City findOne(int id);
}
