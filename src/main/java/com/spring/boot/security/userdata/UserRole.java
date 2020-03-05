package com.spring.boot.security.userdata;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_role")
public class UserRole {
	
	
	
	public UserRole() {
		super();
	}
	
	
	@Id
	@Column(name="user_role_id")
	private int id;
	private String username;
	private String role;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserame() {
		return username;
	}
	public void setUser_name(String username) {
		this.username = username;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
	

}
