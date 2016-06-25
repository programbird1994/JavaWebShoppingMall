package com.huyang.dao.spring;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.huyang.model.spring.Address;
@Repository
public class HibernateAddressDao {

	 @Autowired
	  private SessionFactory sessionFactory;

	   public Session currentSession()
	   {
		   return sessionFactory.getCurrentSession();
	   }
@Transactional
	   public List<Address> getAddressListByAddressIDList(List<Integer> addressIDList)
	   {
		   List<Address> list=new ArrayList<Address>();
		   for (Integer addressID : addressIDList) {
			list.add((Address) currentSession().createQuery("from Address where addressID=?").setInteger(0, addressID).uniqueResult());
		}
		   System.out.println("size"+list.size());
		   //return currentSession().createQuery("from Address where addressID=?").setInteger(0, addressID).list();
		   return list;
	   }
@Transactional
	   public List<Integer> getAddressIDListByUserID(int userID)
	   {
		  return  currentSession().createSQLQuery("select addressID from user_address where userID=?").setInteger(0, userID).list();
	   }
@Transactional
	   public List<Address> getAddressListByUserID(int userID)
	   {
	List<Integer> list=this.getAddressIDListByUserID(userID);
	for (Integer integer : list) {
		System.out.println("id:"+integer);
	}
		   return this.getAddressListByAddressIDList(list);
	   }
@Transactional
          public int saveAddress(Address address,Integer userID)
          {
	                currentSession().save(address);
	                int addressID=address.getAddressID();
	                System.out.println("userID:"+userID);
	                System.out.println("addressID"+addressID);
	                currentSession().createSQLQuery("insert into user_address (userID,addressID) values(?,?)").setInteger(0, userID).setInteger(1, addressID).executeUpdate();
	               return address.getAddressID();

           }
}
