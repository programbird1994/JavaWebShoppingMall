package com.huyang.model.spring;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the product_detail database table.
 *
 */
@Entity
@Table(name="product_detail")
@NamedQuery(name="ProductDetail.findAll", query="SELECT p FROM ProductDetail p")
public class ProductDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int productID;

	private String brand;

	private String keyword;

	private int categoryID;

	private int commentID;

	private String image;

	private double price;

	private String productName;

	private int sellCount;

	private int storeCount;

	private int shopID;

	private int typeID;

	private Date createTime;

	private String description;

	public ProductDetail() {
	}

	public int getProductID() {
		return this.productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public String getBrand() {
		return this.brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getCategoryID() {
		return this.categoryID;
	}

	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}

	public int getCommentID() {
		return this.commentID;
	}

	public void setCommentID(int commentID) {
		this.commentID = commentID;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getSellCount() {
		return this.sellCount;
	}

	public void setSellCount(int sellCount) {
		this.sellCount = sellCount;
	}

	public int getShopID() {
		return this.shopID;
	}

	public void setShopID(int shopID) {
		this.shopID = shopID;
	}

	public int getTypeID() {
		return this.typeID;
	}

	public void setTypeID(int typeID) {
		this.typeID = typeID;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public boolean equals(Object obj) {
	      if (obj == null) return false;
	      if (!this.getClass().equals(obj.getClass())) return false;

	      ProductDetail obj2 = (ProductDetail)obj;
	      if((this.productID == obj2.getProductID()) && (this.productName.equals(obj2.getProductName())))
	      {
	         return true;
	      }
	      return false;
	   }
	   @Override
	public int hashCode() {
	      int tmp = 0;
	      tmp = ( productID + productName ).hashCode();
	      return tmp;
	   }

	public int getStoreCount() {
		return storeCount;
	}

	public void setStoreCount(int storeCount) {
		this.storeCount = storeCount;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}



}