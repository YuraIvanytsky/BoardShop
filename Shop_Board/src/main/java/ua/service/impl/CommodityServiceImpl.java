package ua.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.entity.Category;
import ua.entity.Commodity;
import ua.entity.User;
import ua.form.CommodityForm;
import ua.form.filter.CommodityFilterForm;
import ua.repository.CategoryRepository;
import ua.repository.CommodityRepository;
import ua.repository.UserRepository;
import ua.service.CommodityService;
import ua.service.FileWriter;
import ua.service.FileWriter.Folder;
import ua.service.impl.specification.CommodityFilterAdapter;

@Service
@Transactional
public class CommodityServiceImpl implements CommodityService{
	@Resource
	private CommodityRepository commodityRepository;
	@Autowired
	private UserRepository personRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private FileWriter fileWriter;
	@Override
	public void save(String title, double price, int count, String discription, int userId, int categoryId) {
		Commodity commodity = new Commodity();
		User user = personRepository.findOne(userId);
		Category category = categoryRepository.findOne(categoryId);
		commodity.setUser(user);
		commodity.setCategory(category);
		commodity.setTitle(title);
		commodity.setPrice(price);
		commodity.setCount(count);
		commodity.setDiscription(discription);
		commodityRepository.save(commodity);
	}
	@Override
	public Commodity findByTitle(String title) {
		return commodityRepository.findByTitle(title);
	}
	@Override
	public Commodity findByPrice(double price) {
		return commodityRepository.findByPrice(price);
	}
	@Override
	public Commodity findByCount(int count) {
		return commodityRepository.findByCount(count);
	}
	@Override
	public Commodity findByDiscription(String discription) {
		return commodityRepository.findByDiscription(discription);
	}
	@Override
	public void delete(int id) {
		commodityRepository.delete(id);
	}
	@Override
	public void deleteAll() {
		commodityRepository.deleteAll();
	}
	@Override
	public Page<Commodity> findAll(CommodityFilterForm form, Pageable pageable) {
		return commodityRepository.findAll(new CommodityFilterAdapter(form), pageable);
	}
	@Override
	public List<Commodity> findAll() {
		return commodityRepository.findAll();
	}
	@Override
	public void save(CommodityForm form) {
		Commodity commodity = new Commodity();
		commodity.setCategory(form.getCategory());
		commodity.setUser(form.getUser());
		commodity.setTitle(form.getTitle());
		commodity.setCount(form.getCount());
		commodity.setDiscription(form.getDiscription());
		commodity.setPrice(form.getPrice());
		commodity.setPath(form.getPath());
		commodityRepository.saveAndFlush(commodity);
		String extension = fileWriter.write(Folder.COMMODITY, form.getFile(), commodity.getId());
		if(extension!=null){
			commodity.setVersion(form.getVersion()+1);
			commodity.setPath(extension);
			commodityRepository.save(commodity);
		}
	}
	@Override
	public CommodityForm findForForm(int id) {
		Commodity commodity = commodityRepository.findOneCommodityInited(id);
		CommodityForm form = new CommodityForm();
		form.setId(commodity.getId());
		form.setCategory(commodity.getCategory());
		form.setUser(commodity.getUser());
		form.setTitle(commodity.getTitle());
		form.setCount(commodity.getCount());
		form.setDiscription(commodity.getDiscription());
		form.setPrice(commodity.getPrice());
		form.setPath(commodity.getPath());
		form.setVersion(commodity.getVersion());
		return form;
	}
	@Override
	public Commodity findById(int id) {
		return commodityRepository.findOne(id);
	}
}
