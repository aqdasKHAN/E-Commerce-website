package com.Practise.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Practise.Model.Category;
// import java.util.List;


@Repository
public interface CategoryRepo extends JpaRepository<Category, Long>{

//	Category findById(Long catId);
	
	Category findByname(String name);
	
	Category findBycatIdAndName(Long catId, String name);
	
}
