package com.spring.boot.security.userdata;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="user_details")
public class UserVO {
	
	public UserVO() {
		super();
	}
	
	public UserVO(UserVO userVO) {
	this.username=userVO.getUsername();
	this.id=userVO.getId();
	this.password=userVO.getPassword();
	this.active=userVO.getActive();
	this.roles=userVO.getRoles();
	}
	
	@Id
	private String username;
	@Column(name="user_id")
	private int id;
	private String password;
	private String active;
	
	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinTable(name="user_details_user_role",joinColumns=@JoinColumn(name="username"))
	private Set<UserRole> roles;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public Set<UserRole> getRoles() {
		return roles;
	}
	public void setRoles(Set<UserRole> roles) {
		this.roles = roles;
	}

}
