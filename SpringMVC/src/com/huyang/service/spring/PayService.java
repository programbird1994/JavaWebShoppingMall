package com.huyang.service.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huyang.dao.spring.HibernateOrderDao;
import com.huyang.dao.spring.HibernateUserDao;

@Service
public class PayService {
	@Autowired
	HibernateUserDao hibernateUserDao;
	@Autowired
	HibernateOrderDao hibernateOrderDao;

	public String userPay(int userID,String orderID)
	{
		double orderPrice=hibernateOrderDao.getTotalPriceByOrderID(orderID);
		if(hibernateUserDao.getBalanceByUserID(userID)>=orderPrice)
		{
			//System.out.println("balance:"+hibernateUserDao.getBalanceByUserID(userID));
		//	System.out.println("orderPrice:"+hibernateOrderDao.getTotalPriceByOrderID(orderID));
			hibernateUserDao.updateBalanceByUserID(userID, -orderPrice);
			hibernateOrderDao.updateOrderStatus("paid", orderID);
			return "su";
		}
		return "fa";
	}

	public String userPay(int userID,String orderID,int productID)
	{
		double price=hibernateOrderDao.getOneProductPriceInOrder(orderID, productID);
		if(hibernateUserDao.getBalanceByUserID(userID)>=price)
		{
			hibernateUserDao.updateBalanceByUserID(userID, -price);
			hibernateOrderDao.updateOrderStatus("ÒÑ¸¶¿î", orderID,productID);
			return "su";
		}
		return "fa";
	}

	public String checkpassword(int userID,String password)
	{
		if(hibernateUserDao.checkPassword(userID,password))
		{
			return "su";
		}
		return "fa";
	}

}
