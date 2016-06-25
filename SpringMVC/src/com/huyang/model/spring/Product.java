package com.huyang.model.spring;


public class Product {
  private int productID;
  private double price;
  private String category;
  private String productName;
  public String getCategory() {
	return category;
}
public void setCategory(String category) {
	this.category = category;
}

public String getProductName() {
	return productName;
}
public void setProductName(String productName) {
	this.productName = productName;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
private String type;
public int getProductID() {
	return productID;
}
public void setProductID(int productID) {
	this.productID = productID;
}
public double getPrice() {
	return price;
}
public void setPrice(double price) {
	this.price = price;
}
}
