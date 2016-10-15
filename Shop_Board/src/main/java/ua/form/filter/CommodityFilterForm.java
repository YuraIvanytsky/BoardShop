package ua.form.filter;

import java.util.ArrayList;
import java.util.List;

public class CommodityFilterForm {
	private String search = "";

	private List<Integer> catId = new ArrayList<>();
	
	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public List<Integer> getCatId() {
		return catId;
	}

	public void setCatId(List<Integer> catId) {
		this.catId = catId;
	}
	
	
}
