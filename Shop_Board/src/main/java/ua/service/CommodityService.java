package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.entity.Commodity;
import ua.form.CommodityForm;
import ua.form.filter.CommodityFilterForm;

public interface CommodityService {
	void save(String title, double price, int count, String discription, int userId, int categoryId);
	Page<Commodity> findAll(CommodityFilterForm form, Pageable pageable);
	List<Commodity> findAll();
	Commodity findByTitle(String title);
	Commodity findByPrice(double price);
	Commodity findByCount(int count);
	Commodity findByDiscription(String discription);
	Commodity findById(int id);
	void delete(int id);
	void deleteAll();
	void save(CommodityForm form);
	CommodityForm findForForm(int id);
}
