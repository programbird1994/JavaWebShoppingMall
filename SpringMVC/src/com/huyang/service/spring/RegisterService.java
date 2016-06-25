package com.huyang.service.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huyang.dao.spring.HibernateUserDao;
import com.huyang.model.spring.User1;
@Service
public class RegisterService {
	@Autowired
private HibernateUserDao hibernateUserDao;

	public String checkUserNameUsed(String userName)
	{
		return hibernateUserDao.checkUserNameUsed(userName)==true? "exist":"not exist";
	}

	public String checkEmailUsed(String email)
	{
		return hibernateUserDao.checkEmailUsed(email)==true? "exist":"not exist";
	}

	public String register(String userName, String email, String password) {
		User1 user=new User1();
		user.setEmail(email);
		user.setUserPassword(password);
		user.setUserName(userName);
		return hibernateUserDao.addUser(user)==true? "su":"fa";
	}
}
