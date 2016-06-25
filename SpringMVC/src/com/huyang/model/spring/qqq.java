package com.huyang.model.spring;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the cart_detail database table.
 * 
 */
@Entity
@Table(name="cart_detail")
@NamedQuery(name="qqq.findAll", query="SELECT q FROM qqq q")
public class qqq implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int cartID;

	private String img;

	private int productCount;

	private int productID;

	private int userID;

	public qqq() {
	}

	public int getCartID() {
		return this.cartID;
	}

	public void setCartID(int cartID) {
		this.cartID = cartID;
	}

	public String getImg() {
		return this.img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public int getProductCount() {
		return this.productCount;
	}

	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}

	public int getProductID() {
		return this.productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public int getUserID() {
		return this.userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

}