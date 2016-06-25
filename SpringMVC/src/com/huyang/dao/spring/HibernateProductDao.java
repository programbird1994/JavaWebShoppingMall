package com.huyang.dao.spring;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.huyang.model.spring.Comment;
import com.huyang.model.spring.OrderDetail;
import com.huyang.model.spring.ProductDetail;
@Repository
public class HibernateProductDao {

	   @Autowired
		private SessionFactory sessionFactory;
	//   @Autowired

       public int getStoreCount(int productID)
                 {
	            return ((ProductDetail)currentSession().get(ProductDetail.class, productID)).getStoreCount();
	             }

       private Session currentSession()
	        {
	      	  return sessionFactory.getCurrentSession();
	        }

	        public void updateSellCount(int productID,String orderID)
	        {
	        	int count=((OrderDetail)currentSession().createQuery("from OrderDetail where orderID=? and productID=?").setString(0, orderID).setInteger(1, productID).uniqueResult()).getCount();
	        	ProductDetail productDetail=(ProductDetail) currentSession().get(ProductDetail.class, productID);
	        	productDetail.setSellCount(productDetail.getSellCount()+count);
	        	currentSession().save(productDetail);
	        }

	        public List<ProductDetail> getProductsByKeyword(String keyword,String priceorder,int index, int pageSize)
	        {
	        	List<ProductDetail> allDetails=currentSession().createQuery("from ProductDetail where productName like ? or keyword like ?"+priceorder).setString(0, "%"+keyword+"%").setString(1, "%"+keyword+"%").setFirstResult((index-1)*pageSize).setMaxResults(pageSize).list();
	        	return  allDetails;
	        }

	        public int getProductsByKeywordCount(String keyword,String priceorder)
	        {
	        	List<ProductDetail> allDetails=currentSession().createQuery("from ProductDetail where productName like ? or keyword like ?"+priceorder).setString(0, "%"+keyword+"%").setString(1, "%"+keyword+"%").list();
	        	return  allDetails.size();
	        }

	        public List<ProductDetail> getProductsByKeyword(String keyword,String priceorder)
	        {
	        	List<ProductDetail> allDetails=currentSession().createQuery("from ProductDetail where productName like ? or keyword like ?"+priceorder).setString(0, "%"+keyword+"%").setString(1, "%"+keyword+"%").list();
	        	return  allDetails;
	        }

	        public List<ProductDetail> getProductList(int shopID)
	        {
	        	Query query=currentSession().createQuery("from ProductDetail where shopID=?");
	        	query.setInteger(0, shopID);
	        	//query.setFirstResult((page-1)*pagesize);
	        	//query.setMaxResults(pagesize);
	        	List<ProductDetail> products=query.list();
	        	return products;
	        }

          public void updateStoreCount(String orderID)
          {
        	  List<OrderDetail> list=currentSession().createQuery("from OrderDetail where orderID=?").setString(0, orderID).list();
        	  for (OrderDetail orderDetail : list) {
        		  ProductDetail productDetail=(ProductDetail) currentSession().get(ProductDetail.class, orderDetail.getProductID());
  	        	productDetail.setStoreCount(productDetail.getStoreCount()-orderDetail.getCount());
  	        	currentSession().save(productDetail);
			}
          }
	        public List<ProductDetail> getProductListByCategoryID(int categoryID)
	        {
	        	System.out.println("id:"+categoryID);
	        	List<Integer> subList=new ArrayList<Integer>();
	        	subList=currentSession().createSQLQuery("select categoryID from Category where parentCategoryID=?").setInteger(0, categoryID).list();
	        	String s="";
	        	System.out.println("size: "+subList.size());
	        	for (Integer integer : subList) {
					s+=" or categoryID="+integer;
				}
	        	Query query=currentSession().createQuery("from ProductDetail where categoryID=? "+s);
	        	query.setInteger(0, categoryID);
	        //	query.setInteger(1, categoryID);
	        	//query.setFirstResult((page-1)*pagesize);
	        	//query.setMaxResults(pagesize);
	        	List<ProductDetail> products=query.list();
	        	return products;
	        }

	        public ProductDetail getproductByproductID(int productID)
	        {

	        	return (ProductDetail) currentSession().get(ProductDetail.class, productID);
	        }

	        public List<ProductDetail>  getTenBestSellProducts()
	        {
	        	return currentSession().createQuery("from ProductDetail order by sellCount desc").list().subList(0, 10);
	        }

	        public List<ProductDetail>  getTenLastSellProducts()
	        {
	        	return currentSession().createQuery("from ProductDetail order by createTime desc").list().subList(0, 10);
	        }

	        public List<Comment>  getCommentsByProductID(int productID)
	        {
	        	return currentSession().createQuery("from Comment where productID=?").setInteger(0, productID).list();
	        }


	        public List<ProductDetail> getProductsBycategory(int index,int pageSize,int categoryID)
	        {
	        	List<Integer> subList=new ArrayList<Integer>();
	        	System.out.println("page function categoryID:"+categoryID);
	        	subList=currentSession().createSQLQuery("select categoryID from category where parentCategoryID=?").setInteger(0, categoryID).list();
	        	System.out.println("list size:"+subList.size());
	        	String s=" ";
	        	for (Integer integer : subList) {
					s+=" or categoryID="+integer;
				}

	        	Query query= currentSession().createQuery("from ProductDetail where categoryID=? "+s);
	        	System.out.println("s: "+  s);
	        	query.setInteger(0, categoryID);
	        //	query.setInteger(1, categoryID);
	        	query.setFirstResult((index-1)*pageSize);
	        	query.setMaxResults(pageSize);
	        	System.out.println("size:"+query.list().size());
	        	return query.list();
	        }
            @Transactional
			public String getAveRankByProductID(int productID) {
				List<Double> ranks=currentSession().createSQLQuery("select rank from comment where productID=?").setInteger(0, productID).list();
				double sum=0;
				DecimalFormat dfDecimalFormat=new DecimalFormat("0.0");
				for (double doub : ranks) {
					sum+=doub;
				}
				if(ranks.size()==0)
					return "0";
				double k=sum/ranks.size();
                 //  String kkString=Double.p
				return dfDecimalFormat.format(k);
			//	dfDecimalFormat.f
				//dfDecimalFormat.f
			}

	      //  public int getCountByCategory

}
