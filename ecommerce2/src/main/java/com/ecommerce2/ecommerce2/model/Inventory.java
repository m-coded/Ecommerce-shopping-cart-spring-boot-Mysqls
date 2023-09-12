package com.ecommerce2.ecommerce2.model;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Inventory")
@Component
public class Inventory {
	
	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  @Column(name = "id", nullable = false)
	  private Long id;
	 @JsonIgnore
	  @OneToOne(optional = false, orphanRemoval = true)
	  @JoinColumn(name = "product_id", nullable = false, unique = true)
	  private Product product;
	  /** The quantity in stock. */
	  @Column(name = "quantity", nullable = false)
	  private Integer quantity;
	  
	  
	  public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	/** The product this inventory is of. */
	 

}
