package com.huyang.service.spring;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huyang.dao.spring.HibernateCartDao;
import com.huyang.model.spring.Cart;
import com.huyang.model.spring.ProductDetail;

@Service
public class CartService {
        @Autowired
	private HibernateCartDao hibernateCartDao;
   @Transactional
	public List<Cart> getCartByUserID(int userID,int page,int pagesize)
	{
		return hibernateCartDao.getCartByUserID(userID,page,pagesize);
	}
   
   @Transactional
	public List<Cart> getCartByUserID(int userID)
	{
		return hibernateCartDao.getCartByUserID(userID);
	}
   @Transactional
   public void  removeProduct(List<Integer> productIDs,int userID)
   {
	  hibernateCartDao.removeProduct(productIDs, userID);
   }
   
   @Transactional
   public void updateCart(int productID,int userID,int arrangecount)
   {
	   hibernateCartDao.updateProductCount(productID, userID, arrangecount);
   }
   
   @Transactional
   public void removeCart(List<Integer> productIDs,int userID)
   {
	   hibernateCartDao.removeProduct(productIDs, userID);
   }
   
   @Transactional
   public List<ProductDetail> getProductsByUserID(int userID,int page,int pagesize)
   {
	  return  hibernateCartDao.getProductsByUserID(userID,page,pagesize);
   }
   @Transactional
   public List<ProductDetail> getProductsByUserID(int userID)
   {
	  return hibernateCartDao.getProductsByUserID(userID);
   }
   
}
