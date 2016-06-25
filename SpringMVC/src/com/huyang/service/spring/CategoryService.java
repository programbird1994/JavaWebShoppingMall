package com.huyang.service.spring;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huyang.dao.spring.HibernateCategoryDao;
import com.huyang.model.spring.Category;

@Service
public class CategoryService {
	@Autowired
	private HibernateCategoryDao hibernateCategoryDao;
 @Transactional
 public List<Category> getAllCategories()
 {
	return  hibernateCategoryDao.getAllCategories();
 }
 @Transactional
 public List<Category> getcategoriedByID(int categoryID)
 {
	 return hibernateCategoryDao.getSubCategoriesByID(categoryID);
 }
 
 @Transactional
 public List<Category> getSubcategories()
 {
	 return hibernateCategoryDao.getSubCategories();
 }
 
 @Transactional
 public int getCountBycategoryID(int categoryID)
 {
	 return hibernateCategoryDao.getCountBycategoryID(categoryID);
 }
}
