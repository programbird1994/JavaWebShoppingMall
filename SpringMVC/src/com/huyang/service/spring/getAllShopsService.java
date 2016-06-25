package com.huyang.service.spring;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.huyang.dao.spring.HibernateShopDao;
import com.huyang.dao.spring.jdbcUtils;
import com.huyang.model.spring.shop;
@Component
public class getAllShopsService {
	@Autowired
	private HibernateShopDao hibernateShopDao;
	@Transactional
    public Collection<shop> getShopList() throws ClassNotFoundException, SQLException
    {
    	
    	Collection<shop> shops = new ArrayList<shop>();
		hibernateShopDao.saveShop(new shop());
		

		shops.add(new shop());
    	return shops;
    }
}
