package ua.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ua.entity.City;
import ua.form.filter.CityFilterForm;
import ua.repository.CityRepository;
import ua.service.CityService;
import ua.service.impl.specification.CityFilterAdapter;
@Service
public class CityServiceImpl implements CityService{
	@Autowired
	private CityRepository cityRepository;
	
	@Override
	public void save(String name) {
		City city = new City();
		city.setName(name);
		cityRepository.save(city);
	}
	@Override
	public List<City> findAll() {
		return cityRepository.findAll();
	}
	@Override
	public City findByName(String name) {
		return cityRepository.findByName(name);
	}
	@Override
	public void delete(int id) {
		cityRepository.delete(id);
	}
	@Override
	public void deleteAll() {
		cityRepository.deleteAll();
	}
	@Override
	public Page<City> findAll(Pageable pageable, CityFilterForm form) {
		return cityRepository.findAll(new CityFilterAdapter(form), pageable);
	}
	@Override
	public void save(City city) {
		cityRepository.save(city);
	}
	@Override
	public City findOne(int id) {
		return cityRepository.findOne(id);
	}
}
