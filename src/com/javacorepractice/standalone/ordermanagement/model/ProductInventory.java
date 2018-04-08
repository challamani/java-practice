package com.javacorepractice.standalone.ordermanagement.model;

import java.util.Date;

public class ProductInventory {

	private Product product;
	private Integer quantity;
	private Date lastUpdate;
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
	public Date getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	public ProductInventory(Product product, Integer quantity, Date lastUpdate) {
		super();
		this.product = product;
		this.quantity = quantity;
		this.lastUpdate = lastUpdate;
	}
	
	
	
}
