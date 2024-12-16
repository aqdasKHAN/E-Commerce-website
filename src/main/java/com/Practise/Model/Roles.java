package com.Practise.Model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;


@Entity
@Table(name="roles")
public class Roles {

	@Id
	@GeneratedValue(strategy =  GenerationType.AUTO)
	private Integer id;
	
	@Column(nullable = false, unique = true)
	@NotEmpty
	private String name;
	
	
	@ManyToMany(mappedBy = "roles")
	private List<User> user;


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public List<User> getUser() {
		return user;
	}


	public void setUser(List<User> user) {
		this.user = user;
	}


	
	
}
