package com.Practise.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Practise.DAO.ProductRepo;
import com.Practise.Model.Product;

@Service
public class ProductService {

	@Autowired
	private ProductRepo productRepo;  
	 
	public List<Product> getAllProduct(){
		return  productRepo.findAll();
	}
	
	public void addingProduct(Product product) {
		productRepo.save(product);
	}
	
	public void deletingProductById(Long productId) {
		productRepo.deleteById(productId);
	}
	
	public Optional<Product> getProductById(Long productId){
		return productRepo.findById(productId); 
	}
	
	public List<Product> getProductByCategoryId(Long cat_id){
		return productRepo.findByCategory_catId(cat_id);
	}
}
