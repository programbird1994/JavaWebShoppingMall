package com.huyang.service.spring;

import org.apache.catalina.core.ApplicationContext;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.huyang.dao.spring.HibernateShopDao;
import com.huyang.model.spring.shop;
@Transactional
public class AddShopService {
	
	private HibernateShopDao hibernateShopDao;
//	@Autowired
//	public AddShopService(SessionFactory sessionFactory)
//	{
//		this.sessionFactory=sessionFactory;
//	}
	
    public void addShop(shop sh)
    {
    	//HibernateShopDao hibernateShopDao=new HibernateShopDao(sessionFactory);
    	getHibernateShopDao().addShop(sh);
    }

	public HibernateShopDao getHibernateShopDao() {
		return hibernateShopDao;
	}

	public void setHibernateShopDao(HibernateShopDao hibernateShopDao) {
		this.hibernateShopDao = hibernateShopDao;
	}
}
