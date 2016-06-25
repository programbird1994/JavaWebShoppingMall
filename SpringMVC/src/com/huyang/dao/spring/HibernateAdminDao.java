package com.huyang.dao.spring;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.huyang.model.spring.ProductDetail;

@Repository
public class HibernateAdminDao {
   @Autowired
  private SessionFactory sessionFactory;

   public Session currentSession()
   {
	   return sessionFactory.getCurrentSession();
   }

   public void delete(int productID)
   {
	   currentSession().createQuery("delete from ProductDetail where productID=? ").setInteger(0, productID).executeUpdate();
   }

   public Boolean checkProductExist(String brand,String productName,int shopID)
   {
	   System.out.println("hehe");
	  return currentSession().createQuery("from ProductDetail where brand=? and productName=? and shopID=?").setString(0, brand).setString(1, productName).setInteger(2, shopID).uniqueResult()==null;
   }

   public void save(ProductDetail productDetail)
   {
	   currentSession().saveOrUpdate(productDetail);
   }

   public List<ProductDetail> getProductsByShopID(int shopID)
   {
	  return currentSession().createQuery("from ProductDetail where shopID=?").setInteger(0, shopID).list();
   }

   public List<ProductDetail> getProductsByShopID(int shopID,int page,int pagesize)
   {
   	Query query=currentSession().createQuery("from ProductDetail where shopID=? ");
   	query.setInteger(0, shopID);
   	query.setFirstResult((page-1)*pagesize);
   	query.setMaxResults(pagesize);
   	List<ProductDetail> products=query.list();
   	return products;
   }

   public String getImgByproductID(int productID)
   {
	  return ((ProductDetail)(currentSession().get(ProductDetail.class, productID))).getImage();
   }

   public   static void main(String [] args)
   {

}
}
