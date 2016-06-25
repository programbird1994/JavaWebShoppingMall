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
		
		<link href="admin/scripts/pagination.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="admin/scripts/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="admin/scripts/jquery.pagination.js"></script>
<link href="admin/scripts/pagination.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">
		
		$(document).ready(function(){
			var jq = $.noConflict(true);
			var categoryid=$("#storeID").val();
			var keyword=$("#keyword").val();
			var path = "${pageContext.request.contextPath}/jsp";
		         //alert(path);
			function calculate(){
				
			   // var pageCount = 10;//总页数，需要在Calculate方法中为其赋值
			    $.ajax({
			    type:"GET",
			    data:"categoryID="+categoryid+"&keyword="+keyword,
			    dataType:"text",
			    async:false,//为js的全局变量赋值，如果设置为true则会赋值不成功
			    url:path+"/infoBuy/pageCount1",
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
			        url: path+"/infoBuy/listPage1",  //提交到一般处理程序请求数据
			       data: "pageIndex=" + (pageIndex + 1) + "&pageSize=" + 3+"&categoryID="+categoryid+"&keyword="+keyword,  //提交两个参数：pageIndex(页面索引)，pageSize(显示条数)                
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
			        items_per_page: 3,  //显示条数
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
    <input type="hidden" id="storeID" value="${categoryID}"/>
		<jsp:include page="/jsp/top"   />
			<section class="header_text sub">
			<img class="pageBanner" src="resources/themes/images/pageBanner.png" alt="New products" >
				<h4><span>${category}</span></h4>
			</section>
			<section class="main-content">
				
				<div class="row">						
					<div class="span9">								
						<ul class="thumbnails listing-products">
						    <div id="list"></div>
						</ul>								
						<hr>
															
   
  
   <div id="pagination" style="text-align:center;" class="pagination" ></div> 
					</div>
					
			</section>
			
		</div>
		<input type="hidden" id="keyword" value="${keyword}"/>
		<script src="resources/themes/js/common.js"></script>	
    </body>
</html>