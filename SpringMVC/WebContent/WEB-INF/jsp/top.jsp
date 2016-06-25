<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

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
<title>Insert title here</title>
<script language="javascript" type="text/javascript">
    $(function(){
    	//var jq = $.noConflict(true);
        $('#searchinput').bind('keypress',function(event){
            if(event.keyCode == "13")    
            {
              
                $('#searchform').submit();
            	
            }
        });
    });
</script>
</head>
<body>

   <div id="top-bar" class="container">
			<div class="row">
				<div class="span4">
					<form method="get" id="searchform" action="products">
						<input type="text" class="input-block-level search-query" name="searchinput" Placeholder="输入您想要的商品名">
					</form>
				</div>
				<div class="span8">
					<div class="account pull-right">
						<ul class="user-menu">	
						<li><a href="index">首页</a></li>	
						<li><a href="myorder">我的订单</a></li>		
							<li><a href="#">当前用户：${userName}</a></li>
							<li><a href="cart">我的购物车</a></li>
							<li><a href="Login">登录</a></li>		
						</ul>
					</div>
				</div>
			</div>
		</div>
		<div id="wrapper" class="container">
			<section class="navbar main-menu">
				<div class="navbar-inner main-menu">				
					<a href="index" class="logo pull-left"><img src="upload/logo.png" class="site_logo" alt=""></a>
					<nav id="menu" class="pull-right">
						<ul>
						<c:forEach items="${categories}" var="auser">
							
							
								
									<li><a href="./products?category=${auser.categoryID}">${auser.categoryName}</a>
									
									<ul>
							
								<c:forEach items="${auser.subCategories}" var="sub">
									<li><a href="./products?category=${sub.categoryID}">${sub.categoryName}</a></li>									
									</c:forEach>								
								</ul>
									
									</li>									
									</c:forEach>								
							
							
						</ul>
					</nav>
				</div>
			</section>
</html>