package com.ecommerce2.ecommerce2.model;

import java.security.Timestamp;
import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "verification_token")
@Component
public class VerificationToken {
	
	 @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "id", nullable = false)
	  private Long id;
	 @Lob
	 @Column(name = "token", nullable = false, unique = true)
	 private String token;
	 @Column(name = "createdTimestamp" ,nullable = false)
	  private java.sql.Timestamp createdTimestamp;
	 @ManyToOne(optional = false)
	 @JoinColumn(name= "user_id", nullable = false)
	 private LocalUser user;
	 
	 public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public java.sql.Timestamp getCreatedTimestamp() {
		return createdTimestamp;
	}
	public void setCreatedTimestamp(java.sql.Timestamp timestamp) {
		this.createdTimestamp = timestamp;
	}
	public LocalUser getUser() {
		return user;
	}
	public void setUser(LocalUser user) {
		this.user = user;
	}
	

}
