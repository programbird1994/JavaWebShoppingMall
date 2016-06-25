package com.huyang.dao.spring;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.huyang.model.spring.Cart;
import com.huyang.model.spring.ProductDetail;
import com.huyang.model.spring.UserCart;
@Repository
public class HibernateCartDao {
        @Autowired
	private SessionFactory sessionFactory;

        private Session currentSession()
        {
      	  return sessionFactory.getCurrentSession();
        }

        public List<Cart> getCartByUserID(int userID,int page,int pagesize)
        {
        	Query query=currentSession().createQuery("from Cart where userID=? ");
        	query.setInteger(0, userID);
        	query.setFirstResult((page-1)*pagesize);
        	query.setMaxResults(pagesize);
        	List<Cart> carts=query.list();
        	for (Cart cart : carts) {
        		ProductDetail productDetail=(ProductDetail) currentSession().createQuery("from ProductDetail where productID=?").setInteger(0, cart.getProductID()).uniqueResult();
				cart.setProductDetail(productDetail);
			}
        	return carts;
        }

        public List<Cart> getCartByCartID(int cartID)
        {
        	List<Cart> carts=currentSession().createQuery("from Cart where cartID=? ").setInteger(0, cartID).list();
        	return carts;
        }

        public List<Cart> getCartByUserID(int userID)
        {
        	Query query=currentSession().createQuery("from Cart where userID=? ");
        	query.setInteger(0, userID);


        	List<Cart> carts=query.list();
        	for (Cart cart : carts) {
        		ProductDetail productDetail=(ProductDetail) currentSession().createQuery("from ProductDetail where productID=?").setInteger(0, cart.getProductID()).uniqueResult();
				cart.setProductDetail(productDetail);
			}
        	return carts;
        }

        public void removeProduct(List<Integer> productIDs,int userID)
        {
        	for (Integer productID : productIDs) {
        		Query query=currentSession().createQuery("delete from Cart where userID=? and productID=?");
            	query.setInteger(0, userID);
            	query.setInteger(1, productID);
            	query.executeUpdate();
			}


        }

        public void updateProductCount(int productID,int userID,int arangeCount)
        {
        	Query query=currentSession().createQuery(" from Cart where userID=? and productID=?");
        	query.setInteger(0, userID);
        	query.setInteger(1, productID);
        	Cart cart=(Cart) query.uniqueResult();
        //	List<Cart> carts=query.list();
        	if(cart!=null)
        	{

        		cart.setCount(cart.getCount()+arangeCount);
        		currentSession().update(cart);
        	}
        	else
        	{
        		 cart=new Cart();
        		cart.setCount(arangeCount);
        		cart.setUserID(userID);
        		cart.setProductID(productID);
        		//cart.setImg(((ProductDetail)currentSession().get(ProductDetail.class, productID)).getImage());
        		currentSession().save(cart);
        	}
        }

        public List<ProductDetail> getProductsByUserID(int userID,int page,int pagesize)
        {
        	Query query= currentSession().createQuery("select p from ProductDetail as p,Cart as c where  p.productID=c.productID and c.userID=?");
        	query.setInteger(0, userID);
        	query.setFirstResult((page-1)*pagesize);
        	query.setMaxResults(pagesize);
        	List<ProductDetail> list=query.list();
        	List<UserCart> list1=new ArrayList<UserCart>();
        	for (ProductDetail productDetail : list) {
        		UserCart userCart=new UserCart();
        		userCart.setProductDetail(productDetail);
			//	list1.add(e)
			}
        	return query.list();
        }

        public List<ProductDetail> getProductsByUserID(int userID)
        {
        	Query query= currentSession().createQuery("select p from ProductDetail as p,Cart as c where  p.productID=c.productID and c.userID=?");
        	query.setInteger(0, userID);
        	//query.setFirstResult((page-1)*pagesize);
        	//query.setMaxResults(pagesize);
        	return query.list();
        }


}
