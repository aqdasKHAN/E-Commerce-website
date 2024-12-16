package com.Practise.DAO;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Practise.Model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	User findUsernameByEmail(String username);
	
	Optional<User> findUserByEmail(String email);

}
