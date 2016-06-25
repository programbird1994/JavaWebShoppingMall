package com.huyang.dao.spring;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.huyang.model.spring.Address;
import com.huyang.model.spring.OrderDetail;
import com.huyang.model.spring.OrderTemp;
import com.huyang.model.spring.ProductDetail;
import com.huyang.model.spring.UserOrder;
@Repository
public class HibernateOrderDao {
	@Autowired
	  private SessionFactory sessionFactory;

	   public Session currentSession()
	   {
		   return sessionFactory.getCurrentSession();
	   }

//	   @Transactional
//	   public String addComment(String orderID,int productID,String commentcontent)
//	   {
//		   currentSession().
//	   }

	   @Transactional
	   public List<OrderDetail> getAllOrders(int page,int pagesize)
	   {
		   List<OrderDetail> list= currentSession().createQuery("from OrderDetail order by createTime desc ").setFirstResult((page-1)*pagesize).setMaxResults(pagesize).list();
		   for (OrderDetail orderDetail : list) {

			   ProductDetail productDetail=(ProductDetail) currentSession().get(ProductDetail.class, orderDetail.getProductID());
			orderDetail.setProductDetail(productDetail);
			Address address=(Address) currentSession().createQuery("from Address where addressID=?").setInteger(0, orderDetail.getAddressID()).uniqueResult();
			orderDetail.setAddress(address);
		}
		   return list;
	   }

	   @Transactional
	   public int getAllOrders()
	   {
		   int count =  ((BigInteger) currentSession().createSQLQuery("select count(*) from orderdetail ").uniqueResult()).intValue();


		   return count;
	   }

	   @Transactional
	   public List<OrderDetail> getAllOrdersByUserID(int userID,int page,int pagesize)
	   {
		  List<String> list= currentSession().createSQLQuery("select orderID from user_order where userID=? ").setInteger(0, userID).list();
		  List<OrderDetail> list2=new ArrayList<OrderDetail>();
		  List<OrderDetail> list3=new ArrayList<OrderDetail>();
		  if(list.size()==0)
		  {
			  System.out.println("list is null");
			  return null;
		  }

		  String hqlString="from OrderDetail where";
		  int size=0;
		  for (String s : list) {
			hqlString+=" orderID=? or";
			size++;
		}
		  System.out.println("list size"+list.size());
		  //System.out.println(hqlString);
		  if(size>0)
			  hqlString=hqlString.substring(0, hqlString.length()-3);

		  System.out.println(hqlString+"size:"+size);
     hqlString+="order by createTime desc";
		  Query query=currentSession().createQuery(hqlString);
		  for (int i=0;i<size;i++) {
			  query.setString(i, list.get(i));

			//list3=currentSession().createQuery("from OrderDetail where orderID=? order by createTime desc").setString(0, i).list();

			//list2.addAll(list3);
		}
		  query.setFirstResult((page-1)*pagesize);
		  query.setMaxResults(pagesize);
		  list3=query.list();
		  for (OrderDetail orderDetail : list3) {
				orderDetail.setProductDetail((ProductDetail) currentSession().get(ProductDetail.class, orderDetail.getProductID()));
			}
		  System.out.println("1:+"+list3.size());
		 // System.out.println("2+"+page*pagesize);
		  return list3;
	   }

	   @Transactional
	   public int getAllOrdersByUserIDCount(int userID)
	   {
		  List<String> list= currentSession().createSQLQuery("select orderID from user_order where userID=?").setInteger(0, userID).list();
		  List<OrderDetail> list2=new ArrayList<OrderDetail>();
		  List<OrderDetail> list3=new ArrayList<OrderDetail>();
		  for (String i : list) {
			list3=currentSession().createQuery("from OrderDetail where orderID=? order by createTime ").setString(0, i).list();
			for (OrderDetail orderDetail : list3) {
				orderDetail.setProductDetail((ProductDetail) currentSession().get(ProductDetail.class, orderDetail.getProductID()));
			}
			list2.addAll(list3);
		}
		  return list2.size();
	   }

	   @Transactional
	   public double getTotalPriceByOrderID(String orderID)
	   {
		   System.out.println("orderID"+orderID);
		  List<OrderDetail> list= currentSession().createQuery("from OrderDetail where orderID=?").setString(0, orderID).list();
		  double total=0;
		  System.out.println("size:"+list.size());
		//  ProductDetail productDetail=new ProductDetail();
		  for (OrderDetail orderDetail : list) {
			      // productDetail=(ProductDetail) currentSession().get(ProductDetail.class, orderDetail.getProductID());
			       total+=orderDetail.getCurrentPrice()*orderDetail.getCount();
		}
		  return total;
	   }

	   @Transactional
	   public double getOneProductPriceInOrder(String orderID,int productID)
	   {
		  OrderDetail orderDetail=(OrderDetail) currentSession().createQuery("from OrderDetail where orderID=? and productID=?").setString(0, orderID).setInteger(1, productID).uniqueResult();
		  return orderDetail.getCurrentPrice()*orderDetail.getCount();
	   }

	   @Transactional
	   public void updateOrderStatus(String newstatus,String orderID)
	   {
		 List<OrderDetail> list=  currentSession().createQuery("from OrderDetail where orderID=?").setString(0, orderID).list();
		 for (OrderDetail orderDetail : list) {
			orderDetail.setStatus(newstatus);
			currentSession().update(orderDetail);
		}
	   }

	   @Transactional
	   public void updateOrderStatus(String newstatus,String orderID,int productID)
	   {
		OrderDetail orderDetail=  (OrderDetail) currentSession().createQuery("from OrderDetail where orderID=? and productID=?").setString(0, orderID).setInteger(1, productID).uniqueResult();

			orderDetail.setStatus(newstatus);
			currentSession().update(orderDetail);

	   }

	   @Transactional
	   public String save(List<Integer> ids,List<Integer> counts,int userID, int addressID)
	   {
		   Date current=new Date();
		   SimpleDateFormat formatter=new SimpleDateFormat("yyyyMMddhhmmss");
		  String orderID=formatter.format(current)+userID;

		  System.out.println("orderID:"+orderID);
		 // orderID="1";
//		   String orderID=current.toString().f+"userID";
		   UserOrder userOrder=new UserOrder();
		   userOrder.setOrderID(orderID);
		   userOrder.setUserID(userID);
		   currentSession().save(userOrder);
		 // currentSession().createQuery("insert into user_order VALUES(?,?)").setString(1, orderID).setInteger(0, userID);
		   int size=ids.size();
		   for(int i=0;i<size;i++)
		   {
			   ProductDetail productDetail=new ProductDetail();
			   OrderDetail orderDetail=new OrderDetail();
			   productDetail=(ProductDetail) currentSession().get(ProductDetail.class, ids.get(i));
			   orderDetail.setProductDetail(productDetail);
			   orderDetail.setCount(counts.get(i));
			   orderDetail.setOrderID(orderID);
			   orderDetail.setCurrentPrice(orderDetail.getProductDetail().getPrice());
			   orderDetail.setProductID(ids.get(i));
			   orderDetail.setAddressID(addressID);
               orderDetail.setStatus("Î´¸¶¿î");
			   currentSession().saveOrUpdate(orderDetail);


		   }
		   return orderID;
	   }

	   @Transactional
	   public List<OrderTemp> getOrderTemps(int userID)
	   {
		   List<OrderTemp> list1=new ArrayList<OrderTemp>();

		   List<OrderDetail> list2=new ArrayList<OrderDetail>();
		   List<ProductDetail > list3=new ArrayList<ProductDetail>();
		   List<String> list= currentSession().createSQLQuery("select orderID from user_order where userID=?").setInteger(0, userID).list();
		   System.out.println("size of list"+list.size());
		   for (String s : list)
		   {
			   OrderTemp orderTemp=new OrderTemp();
			   System.out.println("orderID:   "+s);
			        list2=currentSession().createQuery("from OrderDetail where orderID=?").setString(0, s).list();
			                 for (OrderDetail orderDetail : list2)
			                 {
				                  System.out.println("orderdetailprice"+orderDetail.getCurrentPrice());
				                   orderDetail.setProductDetail((ProductDetail) currentSession().get(ProductDetail.class, orderDetail.getProductID()));
			                  }
			               orderTemp.setOrderlist(list2);
			                orderTemp.setOrderID(s);
			                System.out.println("order temp id"+orderTemp.getOrderID());
			                list1.add(orderTemp);
		}
		   return list1;
	   }

	   @Transactional
	   public boolean updateOrder(int productID,String orderID,String status)
	   {
		   OrderDetail orderDetail=(OrderDetail) currentSession().createQuery("from OrderDetail where OrderID=? and productID=?").setString(0, orderID).setInteger(1, productID).uniqueResult();
		   orderDetail.setStatus(status);
		   currentSession().update(orderDetail);
		   return true;
	   }

}
