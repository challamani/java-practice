package com.core_java_practice.standalone.order.model;

import java.math.BigDecimal;
import java.util.Date;

public class OrderItem {

	private Product product;
	private Integer quantity;
	private Date orderDate;
	private Date deliveryDate;
	private BigDecimal lineItemPrice;
	
	
	public OrderItem(Product product, Integer quantity, Date orderDate, Date deliveryDate, BigDecimal lineItemPrice) {
		super();
		this.product = product;
		this.quantity = quantity;
		this.orderDate = orderDate;
		this.deliveryDate = deliveryDate;
		this.lineItemPrice = lineItemPrice;
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
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public BigDecimal getLineItemPrice() {
		return lineItemPrice;
	}
	public void setLineItemPrice(BigDecimal lineItemPrice) {
		this.lineItemPrice = lineItemPrice;
	}

	@Override
	public String toString() {
		return "OrderItem [product=" + product + ", quantity=" + quantity + ", orderDate=" + orderDate
				+ ", deliveryDate=" + deliveryDate + ", lineItemPrice=" + lineItemPrice + "]";
	}
	
	
}
