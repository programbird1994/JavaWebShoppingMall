package com.huyang.service.spring;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huyang.dao.spring.HibernateAddressDao;
import com.huyang.model.spring.Address;

@Service
public class AddressService {
       @Autowired
      HibernateAddressDao hibernateAddressDao;

       public List<Address> getAddressListByUserID(int userID)
       {
    	   return hibernateAddressDao.getAddressListByUserID(userID);
       }

       public int saveAddress(Address address,Integer userID)
       {
    	  return  hibernateAddressDao.saveAddress(address,userID);
       }
}
