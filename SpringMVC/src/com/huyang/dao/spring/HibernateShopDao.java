package com.huyang.dao.spring;

import org.apache.catalina.core.ApplicationContext;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.huyang.model.spring.shop;
@Repository
public class HibernateShopDao {
	@Autowired
  private SessionFactory sessionFactory;
  
//public HibernateShopDao(SessionFactory sessionFactory)
//{
//	this.sessionFactory=sessionFactory;
//	}
  private Session currentSession()
  {
	  return sessionFactory.getCurrentSession();
  }
  public void addShop(shop sh)
  {
	  currentSession().save(sh);
  }
  public SessionFactory getSessionFactory() {
	return sessionFactory;
}
public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
}

 public void saveShop(shop sh)
 {
	 currentSession().save(sh);
 }
 
 public static void main(String []args)
 {
	
 }
}
