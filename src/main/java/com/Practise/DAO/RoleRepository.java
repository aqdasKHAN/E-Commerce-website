package com.Practise.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Practise.Model.Roles;
 
@Repository
public interface RoleRepository extends JpaRepository<Roles, Integer>{

	
	
}
