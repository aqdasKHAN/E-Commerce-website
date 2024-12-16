package com.Practise.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Practise.DAO.CategoryRepo;
import com.Practise.Model.Category;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;
	
	public Optional<Category> getCategoryById(Long catId) {
		return categoryRepo.findById(catId);
	}
	
	public Category findingCatName(String name) {
		return categoryRepo.findByname(name);
	}
	
	public Category findingCategory(Long catId, String name) {
		return categoryRepo.findBycatIdAndName(catId, name);
	}
	
	public List<Category> getAllCategories(){
		return categoryRepo.findAll();
	}
	
	public void addingCat(Category category) {
		categoryRepo.save(category);
	}
	
	public void deletingById(Long catId) {
		categoryRepo.deleteById(catId);
	}
	
	
}
