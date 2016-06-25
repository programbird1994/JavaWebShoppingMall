package com.huyang.dao.spring;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.huyang.model.spring.Comment;
@Repository
public class HibernateCommentDao {

	@Autowired
	private SessionFactory sessionFactory;
@Transactional
        private Session currentSession()
        {
      	  return sessionFactory.getCurrentSession();
        }
@Transactional
        public List<Comment> getCommentListByProductID(int index,int pageSize,int productID)
        {
        	Query query= currentSession().createQuery("from Comment where productID=? order by createTime desc");
        	query.setInteger(0, productID);
        	query.setFirstResult((index-1)*pageSize);
        	query.setMaxResults(pageSize);
        	return query.list();
        }
@Transactional
        public int getCommentCountByProductID(int productID)
        {
        	return currentSession().createQuery("from Comment where productID=? order by createTime desc").setInteger(0, productID).list().toArray().length;
        }
@Transactional
        public boolean addComment(int productID,String commentContent, String userName,Double rank)
        {
        	Comment comment=new Comment();
        	comment.setCommentContent(commentContent);
        	comment.setProductID(productID);
        	comment.setUserName(userName);
        	comment.setRank(rank);
        	currentSession().save(comment);
        	return true;
        }

}
