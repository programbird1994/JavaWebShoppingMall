 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		
		
		<!--[if ie]><meta content='IE=8' http-equiv='X-UA-Compatible'/><![endif]-->
		
		<!-- bootstrap -->
		<link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">      
		<link href="resources/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet">		
		<link href="resources/themes/css/bootstrappage.css" rel="stylesheet"/>
		
		<!-- global styles -->
		<link href="resources/themes/css/main.css" rel="stylesheet"/>
		<link href="resources/themes/css/jquery.fancybox.css" rel="stylesheet"/>
				
		<!-- scripts -->
		<script src="resources/themes/js/jquery-1.7.2.min.js"></script>
		<script type="text/javascript" src="resources/themes/js/jquery.pagination.js"></script>
		<script src="resources/bootstrap/js/bootstrap.min.js"></script>				
		<script src="resources/themes/js/superfish.js"></script>	
		<script src="resources/themes/js/jquery.scrolltotop.js"></script>
		<script src="resources/themes/js/jquery.fancybox.js"></script>
		
<link href="admin/scripts/pagination.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="admin/scripts/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="admin/scripts/jquery.pagination.js"></script>
<link href="admin/scripts/pagination.css" rel="stylesheet" type="text/css" />

		<script src="resources/themes/js/jquery.flexslider-min.js"></script>
		<script type="text/javascript">
		
		$(document).ready(function(){
			var jq = $.noConflict(true);
		$("#add").click(	function add()
			{
			
				var count=parseInt($("#count").val())+1;
				if(count>$("#store").val())
					{
					    alert("已经是最大数量1");
					    return false;
					}
				$("#count").val(count);
			})
			
			$("#plus").click(function plus()
			{
				var count=$("#count").val()-1;
				if(count==0)
				{
				    alert("请至少选择一件");
				    return false;
				}
				$("#count").val(count);
			})
			
			var path = "${pageContext.request.contextPath}/jsp";
			var jq = $.noConflict(true);
			$("#addcart").click(
					function()
					{
						//alert($("#userID1").val())
						if($("#userID1").val()=='null')
						{
							
						         alert("请先登录")
						         window.location.href="Login";
						         return false;
						}
						
						if($("#count").val()>$("#store").val())
							{
							alert("库存不足");
							return false;
							}
						
						if($("#count").val()==0)
						{
						alert("请至少选择一件");
						return false;
						}
						//alert("productID:"+$("#productID").val());
						$.ajax({
							type:"POST",
							data:"productID="+$("#productID").val()+"&userID="+$("#user").val()+"&count="+$("#count").val(),
							dataType:"text",
							url:path+"/addcart",
							success:function(data)
							{
								alert("添加成功");
							},
						error:function()
						{
							alert("cuo le");
						}
						});
						return false;
					}
					
					
					);
			var productid=$("#storeID").val();
			
			
		         //alert(path);
			function calculate(){
				
			   // var pageCount = 10;//总页数，需要在Calculate方法中为其赋值
			    $.ajax({
			    type:"GET",
			    data:"productID="+productid,
			    dataType:"text",
			    async:false,//为js的全局变量赋值，如果设置为true则会赋值不成功
			    url:path+"/infoBuy/pageCount",
			    success:function(data){
			    	//alert("success"+data);
			    pageCount = data;
			    }
			    });
			   
			    return pageCount;
			    }
			    
			    
			function InitTable(pageIndex) {
				//alert("into table");
			    $.ajax({
			        type: "GET",
			        dataType: "text",
			        url: path+"/infoBuy/listPage",  //提交到一般处理程序请求数据
			       data: "pageIndex=" + (pageIndex + 1) + "&pageSize=" + 2+"&productID="+productid,  //提交两个参数：pageIndex(页面索引)，pageSize(显示条数)                
			        success: function (data) {
			            $("#list").empty();   
			            $("#list").html(data); 
			        }
			    });//ajax结尾
			}//请求数据结尾     


			function initPagination(){
			    //分页相关配置
			     $("#pagination").pagination(calculate(), {
			        callback: PageCallback,
			        prev_text: '上一页',       //上一页按钮里text
			        next_text: '下一页',       //下一页按钮里text
			        items_per_page: 2,  //显示条数
			        num_display_entries: 6,    //连续分页主体部分分页条目数
			        current_page: 0,   //当前页索引
			        num_edge_entries: 2        //两侧首尾分页条目数
			    });
			   
			};    

			//翻页调用
			     function PageCallback(index , jq) {
			         InitTable(index);
			     }


			initPagination();//初始化页码

			InitTable(0);
			
			
		});
		
			$(function () {
				$('#myTab a:first').tab('show');
				$('#myTab a').click(function (e) {
					e.preventDefault();
					$(this).tab('show');
				})
			})
			$(document).ready(function() {
				$('.thumbnail').fancybox({
					openEffect  : 'none',
					closeEffect : 'none'
				});
				
				$('#myCarousel-2').carousel({
                    interval: 2500
                });								
			});
		</script>




   


	</head>
	
    <body>	
   <jsp:include page="/jsp/top"   />
     <input type="hidden" id="storeID" value="${product.productID}"/>
    
		
		
		
		
			
		
			<section class="header_text sub">
			<img class="pageBanner" src="resources/themes/images/pageBanner.png" alt="New products" >
				<h4><span>商品详情</span></h4>
			</section>
			<section class="main-content">				
				<div class="row">						
					<div class="span9">
						<div class="row">
							<div class="span4">
								<a href="${product.image}" class="thumbnail" data-fancybox-group="group1" title="Description 1"><img alt="" src="${product.image}"></a>												
								<ul class="thumbnails small">								
									<li class="span1">
										<a href="${product.image}" class="thumbnail" data-fancybox-group="group1" title="Description 2"><img src="${product.image}" alt=""></a>
									</li>								
									<li class="span1">
										<a href="${product.image}" class="thumbnail" data-fancybox-group="group1" title="Description 3"><img src="${product.image}" alt=""></a>
									</li>													
									<li class="span1">
										<a href="${product.image}" class="thumbnail" data-fancybox-group="group1" title="Description 4"><img src="${product.image}" alt=""></a>
									</li>
									<li class="span1">
										<a href="${product.image}" class="thumbnail" data-fancybox-group="group1" title="Description 5"><img src="${product.image}" alt=""></a>
									</li>
								</ul>
							</div>
							<div class="span5">
							<input type="hidden" value="${product.productID}" id="productID"/>
								<address>
									<strong>品牌:</strong> <span>${product.brand}</span><br>
									
									<strong>商品名:</strong> <span>${product.productName}</span><br>
									<strong>库存:</strong> <span>${storeCount}</span><br>
										<input value="${storeCount}" id="store" type="hidden"/>							
								</address>									
								<h4><strong>单价: ${product.price}</strong></h4>
							</div>
							<div class="span5">
								<form class="form-inline">
									
									<p>&nbsp;</p>
									<label>数量:</label>
									<input type="hidden" class="span1" placeholder="1"  id="user" value=<%=request.getSession().getAttribute("userID") %>>
									<input type=button value="-" id="plus">
									<input type="text" class="span1" value="1" name="amount" id="count">
									<input type=button value="+" id="add">

									<button class="btn btn-inverse" type="submit" id="addcart">加入到购物车</button>
								</form>
							</div>							
						</div>
						<input type="hidden" value="<%= request.getSession().getAttribute("userID") %>" id="userID1"/>
						<div class="row">
							<div class="span9">
								<ul class="nav nav-tabs" id="myTab">
									<li class="active"><a href="#home">商品描述</a></li>
									<li class=""><a href="#profile">商品评价<strong color="red">(${averank})</strong></a></li>
								</ul>							 
								<div class="tab-content">
									<div class="tab-pane active" id="home">${product.description}</div>
									<div class="tab-pane" id="profile">
										<!-- 求购信息显示位置 -->
   <div id="list"></div>
   <!-- 分页显示位置 -->
   <div id="pagination" style="text-align:center;" class="pagination" ></div> 
									</div>
								</div>							
							</div>						
							
			
		</div>
		<script src="resources/themes/js/common.js"></script>
		<script>
			$(function () {
				$('#myTab a:first').tab('show');
				$('#myTab a').click(function (e) {
					e.preventDefault();
					$(this).tab('show');
				})
			})
			$(document).ready(function() {
				$('.thumbnail').fancybox({
					openEffect  : 'none',
					closeEffect : 'none'
				});
				
				$('#myCarousel-2').carousel({
                    interval: 2500
                });								
			});
		</script>
    </body>
</html>