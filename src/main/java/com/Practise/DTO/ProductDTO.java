package com.Practise.DTO;


// import lombok.Data;

//@Data
public class ProductDTO {

	private Long productId;
	private String name;
	private Long cat_id;
	private double price;
	private double weight;
	private String description;
	private String imageName;
	
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setCat_id(Long cat_id) {
		this.cat_id = cat_id;
	}
	public Long getCat_id() {
		return cat_id;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	
	
	
}
