package com.huyang.service.spring;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huyang.dao.spring.HibernateCommentDao;
import com.huyang.model.spring.Comment;

@Service
public class CommentService {
	
	@Autowired
	HibernateCommentDao hibernateCommentDao;
	
	@Transactional
	public List<Comment> getCommentsByproductID(int index,int pageSize,int productID)
	{
		return hibernateCommentDao.getCommentListByProductID(index, pageSize, productID);
	}
	
	@Transactional
	public int getCommentCountByProductID(int productID)
	{
		return hibernateCommentDao.getCommentCountByProductID(productID);
	}

}
