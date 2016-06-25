package com.huyang.dao.spring;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.huyang.model.spring.Category;
@Repository
public class HibernateCategoryDao {
	   @Autowired
		private SessionFactory sessionFactory;

	        private Session currentSession()
	        {
	      	  return sessionFactory.getCurrentSession();
	        }

	        public List<Category>  getAllCategories()
	        {
	        	return currentSession().createQuery("from Category where parentCategoryID=0").list();
	        }

	        public List<Category>  getSubCategoriesByID(int categoryID)
	        {
	        	return currentSession().createQuery("from Category where parentCategoryID=?").setInteger(0, categoryID).list();
	        }

	        public List<Category>  getSubCategories()
	        {
	        	return currentSession().createQuery("from Category where parentCategoryID!=0").list();
	        }

	        public int getCountBycategoryID(int categoryID)
	        {
	        	List<Integer> subList=new ArrayList<Integer>();
	        	System.out.println("page function categoryID:"+categoryID);
	        	subList=currentSession().createSQLQuery("select categoryID from category where parentCategoryID=?").setInteger(0, categoryID).list();
	        	System.out.println("list size:"+subList.size());
	        	String s=" ";
	        	for (Integer integer : subList) {
					s+=" or categoryID="+integer;
				}
	        	return currentSession().createQuery("from ProductDetail where categoryID=?"+s).setInteger(0, categoryID).list().size();
	        }
}
