package com.sn.entity;


import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER_TABLE")
public class User {
    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
  
    @Column(name = "USERNAME", unique = true)
    private String username;
  
    @Column(name = "PASSWORD")
    private String password;
    
    @Column(name = "USER_EMAIL", unique = true)
    private String email;
    
    @Column(name = "FIRST_NAME")
    private String firstName;
    
    @Column(name = "LAST_NAME")
    private String lastName;
    
    @Column(name="PICTURE_LINK", nullable = true, columnDefinition="BLOB")
    private byte[] picLink;
    
    @Column(name="USER_BIO")
    private String bio;

  
    protected User() {
    }


	public User(String username, String password, byte[] picLink) {
		super();
		this.username = username;
		this.password = password;
		this.picLink = picLink;
	}


	public User(String username) {
		super();
		this.username = username;
	}


	public User(String username, String password, String email, String firstName, String lastName) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
	}


	public User(String username, String password, String email, String firstName, String lastName, byte[] picLink,
			String bio) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.picLink = picLink;
		this.bio = bio;
	}


	public User(Integer id, String username, String password, String email, String firstName, String lastName,
			byte[] picLink) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.picLink = picLink;
	}

	
	
	

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


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


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public byte[] getPicLink() {
		return picLink;
	}


	public void setPicLink(byte[] picLink) {
		this.picLink = picLink;
	}


	public String getBio() {
		return bio;
	}


	public void setBio(String bio) {
		this.bio = bio;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", bio=" + bio + "]";
	}

    
	
	


	

    
  
}
