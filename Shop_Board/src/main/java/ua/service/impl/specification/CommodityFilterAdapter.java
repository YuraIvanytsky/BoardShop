package ua.service.impl.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import ua.entity.Commodity;
import ua.form.filter.CommodityFilterForm;

public class CommodityFilterAdapter implements Specification<Commodity>{
	private String search = "";
	private CommodityFilterForm form;
	private List<Specification<Commodity>> filters = new ArrayList<>();

	
	public CommodityFilterAdapter(CommodityFilterForm form) {
		search = form.getSearch();
		this.form = form;
//		if (form != null) {
//			this.form = form;
//		} else {
//			this.form = new CommodityFilterForm();
//		}
	}
	
	private void findByCat(){
		if(!form.getCatId().isEmpty()){
			filters.add((root, query, cb) -> root.get("category").in(form.getCatId()));
		}
	}
	
	@Override
	public Predicate toPredicate(Root<Commodity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		if (query.getResultType() != Long.class && query.getResultType() != long.class) {
			root.fetch("category", JoinType.LEFT);
		}
		findByCat();
		if(!filters.isEmpty()){
			Specifications<Commodity> spec = Specifications.where(filters.get(0));
			for(Specification<Commodity> s : filters.subList(1, filters.size())){
				spec = spec.and(s);
			}
			return spec.toPredicate(root, query, cb);
		}
		Expression<String> exp = root.get("title");
		return cb.like(cb.upper(exp), search.toUpperCase()+"%");
	}

}
