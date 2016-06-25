 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
		   
           <c:import url="http://localhost:8080/SpringMVC/jsp/top" />
         
            
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
			<section class="header_text">
				 
				
			</section>
			<section class="main-content">
				<div class="row">
					<div class="span12">													
						<div class="row">
							<div class="span12">
								<h4 class="title">
									<span class="pull-left"><span class="text"><span class="line">特色<strong>商品</strong></span></span></span>
									<span class="pull-right">
										<a class="left button" href="#myCarousel" data-slide="prev"></a><a class="right button" href="#myCarousel" data-slide="next"></a>
									</span>
								</h4>
								<div id="myCarousel" class="myCarousel carousel slide">
									<div class="carousel-inner">
										<div class="active item">
											<ul class="thumbnails">												
												<li class="span3">
													<div class="product-box">
														<span class="sale_tag"></span>
														<p><a href="product_detail"><img src="upload/1.jpg" alt="" /></a></p>
														<a href="product_detail" class="title">Ut wisi enim ad</a><br/>
														<a href="products" class="category">Commodo consequat</a>
														<p class="price">$17.25</p>
													</div>
												</li>
												<li class="span3">
													<div class="product-box">
														<span class="sale_tag"></span>
														<p><a href="product_detail"><img src="upload/2.jpg" alt="" /></a></p>
														<a href="product_detail" class="title">Quis nostrud exerci tation</a><br/>
														<a href="products" class="category">Quis nostrud</a>
														<p class="price">$32.50</p>
													</div>
												</li>
												<li class="span3">
													<div class="product-box">
														<p><a href="product_detail"><img src="upload/3.jpg" alt="" /></a></p>
														<a href="product_detail" class="title">Know exactly turned</a><br/>
														<a href="products" class="category">Quis nostrud</a>
														<p class="price">$14.20</p>
													</div>
												</li>
												<li class="span3">
													<div class="product-box">
														<p><a href="product_detail"><img src="upload/4.jpg" alt="" /></a></p>
														<a href="product_detail" class="title">You think fast</a><br/>
														<a href="products" class="category">World once</a>
														<p class="price">$31.45</p>
													</div>
												</li>
											</ul>
										</div>
										<div class="item">
											<ul class="thumbnails">
												<li class="span3">
													<div class="product-box">
														<p><a href="product_detail"><img src="upload/5.jpg" alt="" /></a></p>
														<a href="product_detail" class="title">Know exactly</a><br/>
														<a href="products" class="category">Quis nostrud</a>
														<p class="price">$22.30</p>
													</div>
												</li>
												<li class="span3">
													<div class="product-box">
														<p><a href="product_detail"><img src="upload/6.jpg" alt="" /></a></p>
														<a href="product_detail" class="title">Ut wisi enim ad</a><br/>
														<a href="products" class="category">Commodo consequat</a>
														<p class="price">$40.25</p>
													</div>
												</li>
												<li class="span3">
													<div class="product-box">
														<p><a href="product_detail"><img src="upload/7.jpg" alt="" /></a></p>
														<a href="product_detail" class="title">You think water</a><br/>
														<a href="products" class="category">World once</a>
														<p class="price">$10.45</p>
													</div>
												</li>
												<li class="span3">
													<div class="product-box">
														<p><a href="product_detail"><img src="upload/8.jpg" alt="" /></a></p>
														<a href="product_detail" class="title">Quis nostrud exerci</a><br/>
														<a href="products" class="category">Quis nostrud</a>
														<p class="price">$35.50</p>
													</div>
												</li>																																	
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
												<li class="span3">
													<div class="product-box">
														<span class="sale_tag"></span>
														<p><a href="product_detail"><img src="upload/cloth/bootstrap-women-ware2.jpg" alt="" /></a></p>
														<a href="product_detail" class="title">Ut wisi enim ad</a><br/>
														<a href="products" class="category">Commodo consequat</a>
														<p class="price">$25.50</p>
													</div>
												</li>
												<li class="span3">
													<div class="product-box">
														<p><a href="product_detail"><img src="upload/cloth/bootstrap-women-ware1.jpg" alt="" /></a></p>
														<a href="product_detail" class="title">Quis nostrud exerci tation</a><br/>
														<a href="products" class="category">Quis nostrud</a>
														<p class="price">$17.55</p>
													</div>
												</li>
												<li class="span3">
													<div class="product-box">
														<p><a href="product_detail"><img src="upload/cloth/bootstrap-women-ware6.jpg" alt="" /></a></p>
														<a href="product_detail" class="title">Know exactly turned</a><br/>
														<a href="products" class="category">Quis nostrud</a>
														<p class="price">$25.30</p>
													</div>
												</li>
												<li class="span3">
													<div class="product-box">
														<p><a href="product_detail"><img src="upload/cloth/bootstrap-women-ware5.jpg" alt="" /></a></p>
														<a href="product_detail" class="title">You think fast</a><br/>
														<a href="products" class="category">World once</a>
														<p class="price">$25.60</p>
													</div>
												</li>
											</ul>
										</div>
										<div class="item">
											<ul class="thumbnails">
												<li class="span3">
													<div class="product-box">
														<p><a href="product_detail"><img src="upload/cloth/bootstrap-women-ware4.jpg" alt="" /></a></p>
														<a href="product_detail" class="title">Know exactly</a><br/>
														<a href="products" class="category">Quis nostrud</a>
														<p class="price">$45.50</p>
													</div>
												</li>
												<li class="span3">
													<div class="product-box">
														<p><a href="product_detail"><img src="upload/cloth/bootstrap-women-ware3.jpg" alt="" /></a></p>
														<a href="product_detail" class="title">Ut wisi enim ad</a><br/>
														<a href="products" class="category">Commodo consequat</a>
														<p class="price">$33.50</p>
													</div>
												</li>
												<li class="span3">
													<div class="product-box">
														<p><a href="product_detail"><img src="upload/cloth/bootstrap-women-ware2.jpg" alt="" /></a></p>
														<a href="product_detail" class="title">You think water</a><br/>
														<a href="products" class="category">World once</a>
														<p class="price">$45.30</p>
													</div>
												</li>
												<li class="span3">
													<div class="product-box">
														<p><a href="product_detail"><img src="upload/cloth/bootstrap-women-ware1.jpg" alt="" /></a></p>
														<a href="product_detail" class="title">Quis nostrud exerci</a><br/>
														<a href="products" class="category">Quis nostrud</a>
														<p class="price">$25.20</p>
													</div>
												</li>																																	
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