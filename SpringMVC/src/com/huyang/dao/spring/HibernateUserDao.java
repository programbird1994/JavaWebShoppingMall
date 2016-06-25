package com.huyang.dao.spring;

import java.util.Collection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.huyang.model.spring.Admin;
import com.huyang.model.spring.User;
import com.huyang.model.spring.User1;
import com.huyang.model.spring.UserInfo;
@Repository
public class HibernateUserDao {
	@Autowired
	private SessionFactory sessionFactory;

	  private Session currentSession()
	  {
		  return getSessionFactory().getCurrentSession();
	  }
@Transactional
public boolean checkPassword(int userID,String password)
{
	return null!=currentSession().createQuery("from UserInfo where userID=? and password=?").setInteger(0, userID).setString(1, password).uniqueResult();
	}
	  public int getUserIDByUserName(String name)
	  {
		  return (int) currentSession().createQuery(" select userID from User1 where userName=?").setString(0, name).uniqueResult();
	  }
	  @Transactional
	  public boolean validate(String name,String password)
	  {


         String hqlString="FROM User1 WHERE userName=? AND userPassword=?";
         Object result=this.sessionFactory.getCurrentSession().createQuery(hqlString).setString(0, name).setString(1, password).uniqueResult();
		  if(result==null)
		return false;
		  return true;
	  }
	  @Transactional
		 public boolean addUser(User1 user)
		 {

			 String hqlString="insert into user(userName,userPassword,email,status) values(?,?,?,'ok')";
			int flag= this.sessionFactory.getCurrentSession().createSQLQuery(hqlString).setString(0, user.getUserName()).setString(1, user.getUserPassword()).setString(2, user.getEmail()).executeUpdate();
			if(flag==0)
			return false;
			return true;
		 }
@Transactional
		 public double getBalanceByUserID(int userID)
		 {
			UserInfo userInfo= (UserInfo) currentSession().get(UserInfo.class, userID);
			return userInfo.getBalance();
		 }

@Transactional
public void updateBalanceByUserID(int userID,double arrange)
{

	UserInfo userInfo= (UserInfo) currentSession().get(UserInfo.class, userID);
	userInfo.setBalance(userInfo.getBalance()+arrange);
	currentSession().update(userInfo);
	//return userInfo.getBalance();
}
        @Transactional
		 public boolean checkUserNameUsed(String userName)
		 {
			 Object result=currentSession().createQuery("from User1 where userName=?").setString(0, userName).uniqueResult();
			 if(result==null)
				 return false;
			 return true;
		 }

        @Transactional
		 public boolean checkEmailUsed(String email)
		 {
			 Object result=currentSession().createQuery("from User1 where email=?").setString(0, email).uniqueResult();
			 if(result==null)
				 return false;
			 return true;
		 }

		  public boolean validateAdmin(String name,String password)
		  {

	         String hqlString="FROM Admin WHERE adminName=? AND adminPassword=?";
	          Collection <User> list=this.sessionFactory.getCurrentSession().createQuery(hqlString).setString(0, name).setString(1, password).list();
			   if(list.isEmpty())
			   {

				   return false;
			   }

			   return true;
		  }

		  public int getShopIDbyAdminName(String name)
		  {
			  return ((Admin)(this.sessionFactory.getCurrentSession().get(Admin.class, name))).getShopID();
		  }

	  public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
}

	@Transactional
	public String getUserNameByUserID(int userID)
	{
		return (String) currentSession().createSQLQuery("select userName from user where userID=?").setInteger(0, userID).uniqueResult();
	}
	}
