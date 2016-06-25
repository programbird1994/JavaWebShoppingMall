 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">      
		<link href="resources/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet">		
		<link href="resources/themes/css/bootstrappage.css" rel="stylesheet"/>
		
		<!-- global styles -->
		<link href="resources/themes/css/flexslider.css" rel="stylesheet"/>
		<link href="resources/themes/css/main.css" rel="stylesheet"/>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
   
   <ul class="thumbnails listing-products">
   <c:forEach items="${products}" var="product">
							<li class="span3">
								<div class="product-box">
									<span class="sale_tag"></span>												
									<a href="product_detail?productID=${product.productID}"><img alt="" src="${product.image}" /></a><br/>
									<a href="product_detail?productID=${product.productID}" class="title">${product.productName}</a><br/>
									<a href="product_detail?productID=${product.productID}">${product.brand}</a><br/>
									<p class="price">${product.price}</p>
								</div>
							</li>  
							</c:forEach>     
							
						</ul>			
   
   
</body>
</html>