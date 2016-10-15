package ua.form;

import org.springframework.web.multipart.MultipartFile;

import ua.entity.Category;
import ua.entity.User;

public class CommodityForm {
	private int id;
	private String title;
	private double price;
	private int count;
	private String discription;
	private String path;
	private int version;
	private Category category;
	private User user;
	private MultipartFile file;
	
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public MultipartFile getFile() {
		return file;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getDiscription() {
		return discription;
	}
	public void setDiscription(String discription) {
		this.discription = discription;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	
}
