package com.Practise.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Practise.Model.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

	public List<Product> findByCategory_catId(Long cat_id);
	
}
