package com.core_java_practice.standalone.order.model;

import java.math.BigDecimal;
import java.util.Date;

public class Product {

	
	private Integer id;
	private String productCode;
	private String productionName;
	private String units;
	private BigDecimal price;
	private Date expiryDate;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductionName() {
		return productionName;
	}
	public void setProductionName(String productionName) {
		this.productionName = productionName;
	}
	public String getUnits() {
		return units;
	}
	public void setUnits(String units) {
		this.units = units;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", productCode=" + productCode + ", productionName=" + productionName + ", units="
				+ units + ", price=" + price + ", expiryDate=" + expiryDate + "]";
	}
	public Product(Integer id, String productCode, String productionName, String units, BigDecimal price,
			Date expiryDate) {
		super();
		this.id = id;
		this.productCode = productCode;
		this.productionName = productionName;
		this.units = units;
		this.price = price;
		this.expiryDate = expiryDate;
	}
	
	
	
	
	
	
}
