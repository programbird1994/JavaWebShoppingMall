package com.huyang.service.spring;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huyang.dao.spring.HibernateUserDao;
@Service
public class LoginService {
	@Autowired
 private HibernateUserDao hibernateUserDao;

public HibernateUserDao getHibernateUserDao() {
	return hibernateUserDao;
}
public LoginService(){}
public void setHibernateUserDao(HibernateUserDao hibernateUserDao) {
	this.hibernateUserDao = hibernateUserDao;
}

public LoginService(HibernateUserDao hibernateUserDao)
{
	this.hibernateUserDao=hibernateUserDao;
	}

@Transactional
public boolean validate(String name,String password,HttpServletRequest req)
{
	if( hibernateUserDao.validate(name, password))
	{

	req.getSession().setAttribute("userID", hibernateUserDao.getUserIDByUserName(name));

	return true;
	}
	return false;

	}

@Transactional
public boolean validateAdmin(String name,String password)
{
	return hibernateUserDao.validateAdmin(name, password);
	}

@Transactional
public int getShopIDByAdminname(String name)
{
	return hibernateUserDao.getShopIDbyAdminName(name);
	}
}
