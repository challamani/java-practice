package com.javacorepractice.standalone.ordermanagement.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class OrderDetails {

	private List<OrderItem> orderItems;
	private User user;
	private BigDecimal cumulativePrice;
	private Date orderDate;
	private String orderCode;
		
	
	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public BigDecimal getCumulativePrice() {
		return cumulativePrice;
	}

	public void setCumulativePrice(BigDecimal cumulativePrice) {
		this.cumulativePrice = cumulativePrice;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	@Override
	public String toString() {
		return "OrderDetails [orderItems=" + orderItems + ", user=" + user + ", cumulativePrice=" + cumulativePrice
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orderCode == null) ? 0 : orderCode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderDetails other = (OrderDetails) obj;
		if (orderCode == null) {
			if (other.orderCode != null)
				return false;
		} else if (!orderCode.equals(other.orderCode))
			return false;
		return true;
	}
	
	
	
}


