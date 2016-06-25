package com.huyang.model.spring;

public class shop {
     private int shopID;
     private String shopName;
     private String test;
     public shop()
     {
    	 this.shopID=1;
    	 this.shopName="huyang";
     }
	public int getShopID() {
		return shopID;
	}
	public void setShopID(int shopID) {
		this.shopID = shopID;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getTest() {
		return test;
	}
	public void setTest(String test) {
		this.test = test;
	}
     
}
