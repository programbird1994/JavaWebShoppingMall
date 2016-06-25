package com.huyang.service.spring;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huyang.dao.spring.HibernateProductDao;
import com.huyang.model.spring.Comment;
import com.huyang.model.spring.ProductDetail;
@Service
public class ProductService {
	@Autowired
	private HibernateProductDao hibernateProductDao;
	@Transactional
	public List<ProductDetail> getProducts(int shopID)
	{
		return hibernateProductDao.getProductList(shopID);
	}

	@Transactional
	public int getStoreCount(int productID)
	{
		return hibernateProductDao.getStoreCount(productID);
	}

	public String getAveRankByProductID(int productID)
	{
		return hibernateProductDao.getAveRankByProductID(productID);
	}

	@Transactional
	public List<ProductDetail> getProductsByKeyword(String keyword,boolean pricehigher,int index,int pageSize)
	{
		String priceorder;
		if(pricehigher)
		{
			 priceorder=" ";
		}
		else {
			 priceorder="order by price desc";

		}
		return hibernateProductDao.getProductsByKeyword(keyword,priceorder,index, pageSize);
	}

	@Transactional
	public List<ProductDetail> getProductsByKeyword(String keyword,boolean pricehigher)
	{
		String priceorder;
		if(pricehigher)
		{
			 priceorder=" ";
		}
		else {
			 priceorder="order by price desc";

		}
		return hibernateProductDao.getProductsByKeyword(keyword,priceorder);
	}

	@Transactional
	public List<ProductDetail> getProductsByCategoryID(int categoryID)
	{
		return hibernateProductDao.getProductListByCategoryID(categoryID);
	}

	@Transactional
	public int getproductsByKeywordcount(String keyword,String priceorder)
	{
		return hibernateProductDao.getProductsByKeywordCount(keyword, priceorder);
	}
	@Transactional
	public void updateStoreCount(String orderID)
	{
		 hibernateProductDao.updateStoreCount(orderID);
	}
	@Transactional
	public void updateSellCount(int productID,String orderID)
	{
               hibernateProductDao.updateSellCount(productID,orderID);
	}

	@Transactional
	public ProductDetail getProductsByProductID(int productID)
	{
		return hibernateProductDao.getproductByproductID(productID);
	}

	@Transactional
	public List<ProductDetail>  getTenBestSellProducts()
	{
		return hibernateProductDao.getTenBestSellProducts();
	}
	@Transactional
	public List<ProductDetail>  getTenLastSellProducts()
	{
		return hibernateProductDao.getTenLastSellProducts();
	}

	@Transactional
	public List<Comment> getCommentsByProductID(int productID)
	{
		return hibernateProductDao.getCommentsByProductID(productID);
	}
	@Transactional
	public List<ProductDetail> getProductsByCategoryID(int index,int pagesize,int categoryID)
	{
		return hibernateProductDao.getProductsBycategory(index, pagesize, categoryID);
	}

}
