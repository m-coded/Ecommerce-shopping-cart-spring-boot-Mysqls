package com.ecommerce2.ecommerce2.model;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Address")
@Component
public class Address {
	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Column(name = "id", nullable = false)
	private Long id;
	@Column(name = "address_line_1", nullable = false, length = 512)
	private String addressLine1;
	@Column(name = "address_line_2", length = 512)
	private String addressLine2;
	@Column(name = "country", nullable = false, length = 75)
	private String country;
	@JsonIgnore
	@ManyToOne(optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	private LocalUser user;
	  
	
	
	  public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public LocalUser getUser() {
		return user;
	}
	public void setUser(LocalUser user) {
		this.user = user;
	}
	


}
