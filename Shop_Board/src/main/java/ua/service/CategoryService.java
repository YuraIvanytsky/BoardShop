package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.entity.Category;
import ua.form.filter.CategoryFilterForm;

public interface CategoryService {
	void save(String name);
	List<Category> findAll();
	Category findByName(String name);
	void delete(int id);
	void deleteAll();
	Page<Category> findAll(Pageable pageable, CategoryFilterForm form);
	void save(Category category);
	Category findOne(int id);
}
