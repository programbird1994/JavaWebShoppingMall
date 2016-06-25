 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ page language="java" contentType="text/html;charset=utf-8" %>
<!DOCTYPE html>

<html lang="en">
	<head>

		<meta charset="utf-8">
		<title>Bootstrap E-commerce Templates</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="description" content="">
		<!--[if ie]><meta content='IE=8' http-equiv='X-UA-Compatible'/><![endif]-->
		<!-- bootstrap -->
		<link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">      
		<link href="resources/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet">
		
		<link href="resources/themes/css/bootstrappage.css" rel="stylesheet"/>
		
		<!-- global styles -->
		<link href="resources/themes/css/flexslider.css" rel="stylesheet"/>
		<link href="resources/themes/css/main.css" rel="stylesheet"/>

		<!-- scripts -->
		<script src="resources/themes/js/jquery-1.7.2.min.js"></script>
		<script src="resources/bootstrap/js/bootstrap.min.js"></script>				
		<script src="resources/themes/js/superfish.js"></script>	
		<script src="resources/themes/js/jquery.scrolltotop.js"></script>
		<!--[if lt IE 9]>			
			<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
			<script src="js/respond.min.js"></script>
		<![endif]-->
	</head>
	
    <body>		
  
		  <jsp:include page="/jsp/top"   />
		   
        
         
            
			<section  class="homepage-slider" id="home-slider">
				<div class="flexslider">
					<ul class="slides">
					
						<li>
							<img src="upload/carousel/banner-1.jpg" alt="" />
						</li>
						<li>
							<img src="upload/carousel/banner-2.jpg" alt="" />
							<div class="intro">
								<h1>季中打折</h1>
								<p><span>让利50%</span></p>
								<p><span>指定商品</span></p>
							</div>
						</li>
					</ul>
				</div>			
			</section>
		
			<section class="main-content">
				<div class="row">
					<div class="span12">													
						<div class="row">
							<div class="span12">
								<h4 class="title">
									<span class="pull-left"><span class="text"><span class="line">热卖<strong>商品</strong></span></span></span>
									<span class="pull-right">
										<a class="left button" href="#myCarousel" data-slide="prev"></a><a class="right button" href="#myCarousel" data-slide="next"></a>
									</span>
								</h4>
								<div id="myCarousel" class="myCarousel carousel slide">
									<div class="carousel-inner">
										<div class="active item">
											<ul class="thumbnails">	
											<c:forEach items="${best_sell_active}" var="product">											
												<li class="span3">
													<div class="product-box">
														<p><a href="product_detail?productID=${product.productID}"><img src="${product.image}" alt="" /></a></p>
														<a href="product_detail?productID=${product.productID}" class="title">${product.productName}</a><br/>
													<p class="price">${product.brand}</p>
														<p class="price">${product.price}</p>
													</div>
												</li>
												</c:forEach>																																	
											</ul>
										</div>
										
										<div class="item">
											<ul class="thumbnails">	
											<c:forEach items="${best_sell}" var="product">											
												<li class="span3">
													<div class="product-box">
														<p><a href="product_detail?productID=${product.productID}"><img src="${product.image}" alt="" /></a></p>
														<a href="product_detail?productID=${product.productID}" class="title">${product.productName}</a><br/>
													<p class="price">${product.brand}</p>
														<p class="price">${product.price}</p>
													</div>
												</li>
												</c:forEach>																																	
											</ul>
										</div>
										
									</div>							
								</div>
							</div>						
						</div>
						<br/>
						<div class="row">
							<div class="span12">
								<h4 class="title">
									<span class="pull-left"><span class="text"><span class="line">最新 <strong>商品</strong></span></span></span>
									<span class="pull-right">
										<a class="left button" href="#myCarousel-2" data-slide="prev"></a><a class="right button" href="#myCarousel-2" data-slide="next"></a>
									</span>
								</h4>
								<div id="myCarousel-2" class="myCarousel carousel slide">
									<div class="carousel-inner">
										<div class="active item">
											<ul class="thumbnails">	
											<c:forEach items="${last_sell_active}" var="product">	
											<li class="span3">
													<div class="product-box">
														<span class="sale_tag"></span>
														<p><a href="product_detail?productID=${product.productID}"><img src="${product.image}" alt="" /></a></p>
														<a href="product_detail?productID=${product.productID}" class="title">${product.productName}</a><br/>
														<p class="price">${product.brand}</p>
														<p class="price">${product.price}</p>
													</div>
												</li>
												</c:forEach>	
																						
												</ul>
										</div>
										<div class="item">
											<ul class="thumbnails">
										<c:forEach items="${last_sell}" var="product">
										<li class="span3">
													<div class="product-box">
														<span class="sale_tag"></span>
														<p><a href="product_detail?productID=${product.productID}"><img src="${product.image}" alt="" /></a></p>
														<a href="product_detail?productID=${product.productID}" class="title">${product.productName}</a><br/>
														<p class="price">${product.brand}</p>
														<p class="price">${product.price}</p>
													</div>
												</li>
										</c:forEach>
										</ul>
										</div>
									</div>							
								</div>
							</div>						
						</div>
						
		<script src="resources/themes/js/common.js"></script>
		<script src="resources/themes/js/jquery.flexslider-min.js"></script>
		<script type="text/javascript">
			$(function() {
				$(document).ready(function() {
					$('.flexslider').flexslider({
						animation: "fade",
						slideshowSpeed: 4000,
						animationSpeed: 600,
						controlNav: false,
						directionNav: true,
						controlsContainer: ".flex-container" // the container that holds the flexslider
					});
				});
			});
		</script>
    </body>
</html>