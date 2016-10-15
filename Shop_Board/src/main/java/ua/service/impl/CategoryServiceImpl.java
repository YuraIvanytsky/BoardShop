package ua.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.entity.Category;
import ua.form.filter.CategoryFilterForm;
import ua.repository.CategoryRepository;
import ua.service.CategoryService;
import ua.service.impl.specification.CategoryFilterAdapter;
import ua.service.impl.specification.CityFilterAdapter;
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{
	@Autowired
	private CategoryRepository categoryRepository;
	@Override
	public void save(String name) {
		Category category = new Category();
		category.setName(name);
		categoryRepository.save(category);
	}

	@Override
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	@Override
	public Category findByName(String name) {
		return categoryRepository.findByName(name);
	}

	@Override
	public void delete(int id) {
		categoryRepository.delete(id);
	}

	@Override
	public void deleteAll() {
		categoryRepository.deleteAll();
	}

	@Override
	public Page<Category> findAll(Pageable pageable, CategoryFilterForm form) {
		return categoryRepository.findAll(new CategoryFilterAdapter(form), pageable);
	}

	@Override
	public void save(Category category) {
		categoryRepository.save(category);
	}

	@Override
	public Category findOne(int id) {
		return categoryRepository.findOne(id);
	}

}
