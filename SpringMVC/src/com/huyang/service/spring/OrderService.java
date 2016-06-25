package com.huyang.service.spring;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huyang.dao.spring.HibernateCommentDao;
import com.huyang.dao.spring.HibernateOrderDao;
import com.huyang.dao.spring.HibernateUserDao;
import com.huyang.model.spring.Address;
import com.huyang.model.spring.OrderDetail;
import com.huyang.model.spring.OrderProduct;
import com.huyang.model.spring.OrderTemp;

@Service
public class OrderService {
	@Autowired
	HibernateUserDao hibernateUserDao;
@Autowired
HibernateCommentDao hibernateCommentDao;
	@Autowired
	ProductService productService;
@Autowired
HibernateOrderDao hibernateOrderDao;
   public String saveOrder(int userID,String ids,String counts, int addressID)
   {
	   int a=0;
	   List<OrderProduct> list1=new ArrayList<OrderProduct>();
	   List<Integer> ids1=new ArrayList<Integer>();
	  List<Integer> counts1=new ArrayList<Integer>();
	     for (String s : ids.split(",")) {

				int i=Integer.parseInt(s);
				ids1.add(i);
				counts1.add(Integer.parseInt(counts.split(",")[a++]));




			}

	   return hibernateOrderDao.save(ids1, counts1, userID,addressID);
   }

   public List<OrderDetail> getAllOrdersByUserID(int userID,int page,int pagesize)
   {
	   return hibernateOrderDao.getAllOrdersByUserID(userID,page,pagesize);
   }

   public Address makeAddress(String consignee,String tel,String street,String region)
   {
	   Address address=new Address();
	   address.setConsigner(consignee);
	   address.setProvince(region.split(" ")[0]);
	   address.setCity(region.split(" ")[1]);
	   address.setTown(region.split(" ")[2]);
	   address.setTelephone(tel);
	   address.setZipCode(street.split("\\(")[1].substring(0, 6));
	   address.setDetailAddress(street.split("\\(")[0]);
	   return address;
   }

   public int getAllOrdersByUserIDCount(int userID)
   {
	   return hibernateOrderDao.getAllOrdersByUserIDCount(userID);
   }

   public List<OrderTemp> getAllOrderTempByUserID(int userID)
   {
	   return hibernateOrderDao.getOrderTemps(userID);
   }

    public List<OrderDetail> getAllOrders(int page,int pagesize)
    {
    	return hibernateOrderDao.getAllOrders(page,pagesize);
    }

    public String addComment(int productID,String commentContent,int userID,double rank)
    {
    	String userName=hibernateUserDao.getUserNameByUserID(userID);
           hibernateCommentDao.addComment(productID, commentContent, userName,rank);
           return "su";
    }

    public int getAllOrders()
    {
    	return hibernateOrderDao.getAllOrders();
    }

    public String updateOrder(String orderID,int productID,String status)
    {
    	if(hibernateOrderDao.updateOrder(productID, orderID, status)==true)
    		return "su";
    	return "fa";
    }
}
