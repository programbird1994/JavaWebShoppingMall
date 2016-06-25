package com.huyang.model.spring;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the user_order database table.
 *
 */
@Entity
@Table(name="user_order")
@NamedQuery(name="UserOrder.findAll", query="SELECT u FROM UserOrder u")
public class UserOrder implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String orderID;

	private int userID;

	public UserOrder() {
	}

	public String getOrderID() {
		return this.orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public int getUserID() {
		return this.userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

}