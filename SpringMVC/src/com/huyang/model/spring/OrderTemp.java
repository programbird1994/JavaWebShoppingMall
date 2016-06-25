package com.huyang.model.spring;

import java.util.List;

public class OrderTemp {
	private List<OrderDetail> orderlist;
	private String orderID;

	public List<OrderDetail> getOrderlist() {
		return orderlist;
	}

	public void setOrderlist(List<OrderDetail> orderlist) {
		this.orderlist = orderlist;
	}

	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

}
