package com.huyang.model.spring;

public class UserCart {
	private ProductDetail productDetail;
	private int Count;
	public ProductDetail getProductDetail() {
		return productDetail;
	}
	public void setProductDetail(ProductDetail productDetail) {
		this.productDetail = productDetail;
	}
	public int getCount() {
		return Count;
	}
	public void setCount(int count) {
		Count = count;
	}

}
