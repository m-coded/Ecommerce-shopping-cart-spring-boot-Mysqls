package com.ecommerce2.ecommerce2.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Web_Order")
@Component
public class WebOrder {

	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  @Column(name = "id", nullable = false)
	  private Long id;
	/** The user of the order. */
	  @ManyToOne(optional = false)
	  @JoinColumn(name = "user_id", nullable = false)
	  private LocalUser user;
	  /** The shipping address of the order. */
	  @ManyToOne(optional = false)
	  @JoinColumn(name = "address_id", nullable = false)
	  private Address address;
	  /** The quantities ordered. */
	  @OneToMany(mappedBy = "order", cascade = CascadeType.REMOVE, orphanRemoval = true)
	  private List<WebOrderQuantities> quantities = new ArrayList<>();
	  
	  public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalUser getUser() {
		return user;
	}
	public void setUser(LocalUser user) {
		this.user = user;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public List<WebOrderQuantities> getQuantities() {
		return quantities;
	}
	public void setQuantities(List<WebOrderQuantities> quantities) {
		this.quantities = quantities;
	}
	

}
