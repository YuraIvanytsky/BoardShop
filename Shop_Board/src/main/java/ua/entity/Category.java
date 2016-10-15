package ua.entity;

import java.util.List;

import javax.persistence.*;

@Entity
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	@OneToMany(mappedBy="category")
	private List<Commodity> commodity;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Commodity> getCommodity() {
		return commodity;
	}
	public void setCommodity(List<Commodity> commodity) {
		this.commodity = commodity;
	}
}
