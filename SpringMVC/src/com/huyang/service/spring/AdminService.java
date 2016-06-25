package com.huyang.service.spring;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huyang.dao.spring.HibernateAdminDao;
import com.huyang.model.spring.ProductDetail;

@Service
public class AdminService {
    @Autowired
    HibernateAdminDao hibernateAdminDao;
    @Transactional
    public void delete(int productID)
    {
    	hibernateAdminDao.delete(productID);
    }

    @Transactional
    public Boolean checkProductExist(String brand,String productName,int shopID)
    {
    	return hibernateAdminDao.checkProductExist(brand,productName, shopID);
    }

    @Transactional
    public void save(ProductDetail productDetail)
    {
    	hibernateAdminDao.save(productDetail);

    }

    @Transactional
    public List<ProductDetail>  getProductsByshopID(int shopID)
    {
    	return hibernateAdminDao.getProductsByShopID(shopID);
    }
    @Transactional
    public List<ProductDetail> getProductsByshopID(int shopID,int page,int pagesize)
    {
    	return hibernateAdminDao.getProductsByShopID(shopID, page, pagesize);
    }

    @Transactional
    public String getImgByProductID(int productID)
    {
    	return hibernateAdminDao.getImgByproductID(productID);
    }
}
