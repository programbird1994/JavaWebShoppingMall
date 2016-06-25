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
        <link href="resources/themes/css/page.css" rel="stylesheet"/>
		<!-- scripts -->
		<script src="resources/themes/js/jquery-1.7.2.min.js"></script>
		<script src="resources/bootstrap/js/bootstrap.min.js"></script>	
		<script src="resources/themes/js/jquery.PageBar.js" type="text/javascript"></script>			
		<script src="resources/themes/js/superfish.js"></script>	
		<script src="resources/themes/js/jquery.scrolltotop.js"></script>
	
	<script language="JavaScript" type="text/JavaScript">
	$(document).ready(function() {  
		//alert("12")
		$("#order").click(
				function()
				{
					$("input[name='IDCheck']:checked").each(function(index, domEle){
						//alert(1)
						var temp=$(this);
						$("[name='aproductID']").each(function(){
							
							if($(this).val()==temp.val())
								{
								$(this).attr("check","1");
								}
						})
						
					});
					
					var ids="";
					var counts="";
					$("[name='aproductID']").each(
							function(){
							//	alert(2)
								if($(this).attr("check")=="1")
									{
									ids+=$(this).val()+",";
									counts+=$(this).parent().next().children().first().val()+",";
									}
							})
							ids=ids.substring(0,ids.length-1);
					counts=counts.substring(0,counts.length-1);
					 window.location.href="ceshi?ids="+ids+"&counts="+counts;
				}
				)
		var total=0;
		$("[name='acount']").each(
				function()
				{
					//alert($(this).parent().prev().children().first().val());
					total+=$(this).val()*$(this).parent().next().children().first().val();
				}
				)
		$("#total").text(total);
		
		$("[name='total']").each(
				function()
				{
					//alert("12");
					var a=$(this).prev().text();
					//alert("3")
					var b=$(this).prev().prev().children().first().val();
				//	alert("4");
					//alert(b);
					$(this).text(a*b);	
				}
				)
		
		$("[name='count']").bind("input propertychange", function() {
			
			var demo=$(this).parent().siblings().first().children().first();
			var co=$(this).val();
			var a=$(this).parent().siblings("[name='unitprice']");
			
			var price=a.text();
			var before=$(this).parent().siblings("[name='total']").text();
	
			var c=$(this).parent().siblings("[name='total']");
			c.text(($(this).val()*price));
		
			var p=parseInt($("#total").text())+parseInt(c.text())-parseInt(before);
	
                  var now=    $("#total").text()+c.text()-before;
                  $("#total").text(p);
			$("[name='aproductID']").each(function(){
		
				if($(this).val()==demo.val())
					{
					$(this).parent().next().children().first().val(co);
					}
			})
			})
		//ssalert("123");
		$("#update").click(
		function batchDel(){
			//alert("123");
			//alert($("input[name='IDCheck']:checked").size());
			if($("input[name='IDCheck']:checked").size()<=0){
				alert("请至少选中一条！");
				return;
			}
			//alert($("input[name='IDCheck']:checked").size());
			// 1）取出用户选中的checkbox放入字符串传给后台,form提交
			var allIDCheck = "";
			$("input[name='IDCheck']:checked").each(function(index, domEle){
				bjText = $(domEle).parent("td").parent("tr").last().children("td").last().prev().text();
//	 			alert(bjText);
				// 用户选择的checkbox, 过滤掉“已审核”的，记住哦
				if($.trim(bjText)=="已审核"){
//	 				$(domEle).removeAttr("checked");
					$(domEle).parent("td").parent("tr").css({color:"red"});
					$("#resultInfo").html("已审核的是不允许您删除的，请联系管理员删除！！！");
//	 				return;
				}else{
					//alert("demele:"+$(domEle).val());
					allIDCheck += $(domEle).val() + ",";
					//alert("all:"+allIDCheck);
				}
			});
			// 截掉最后一个","
			if(allIDCheck.length>0) {
				allIDCheck = allIDCheck.substring(0, allIDCheck.length-1);
				// 赋给隐藏域
				$("#allIDCheck").val(allIDCheck);
				//alert($("#allIDCheck").val());
				if(confirm("您确定要批量删除这些记录吗？")){
					// 提交form
					var path = "${pageContext.request.contextPath}/jsp";
				//	$("#submitForm").attr("action", "deleteCart").submit();
					  $.ajax({
						    type:"GET",
						    data:"productIDs="+allIDCheck,
						    dataType:"text",
						    async:false,//为js的全局变量赋值，如果设置为true则会赋值不成功
						    url:path+"/deleteCart",
						    success:function(data){
						    	alert("删除成功");
						    	location.reload();
						    	//alert("success"+data);
						   // pageCount = data;
						    },
					  error:function(data)
					  {
						//  alert(allIDCheck)
					  }
						    });
				}
			}
			return true;
		}
		
		)
	    
	    var pageOptions = {  
	        AllowPaging: true,  
	        PageIndex: 1,        
	        PageSize: 15,         
	        RecordCount: 1092,  
	        TotalPage: 73,        
	        showPageCount: 4,  
	        onPageClick: function(pageIndex) {  
	        alert("u click" + parseInt(pageIndex + 1) + "page");    //自定义您的翻页事件  
	            return false;  
	        }  
	    }  
	    
	    $('.g-pagerwp .g-pager').css('visibility', 'visible').pageBar(pageOptions);      
	})  
</script>

		
		<!--[if lt IE 9]>			
			<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
			<script src="resources/themes/js/respond.min.js"></script>
		<![endif]-->
	</head>
    <body>	
    <input id="allIDCheck" type="hidden"/>	
		<jsp:include page="/jsp/top"   />
			<section class="header_text sub">
			<img class="pageBanner" src="resources/themes/images/pageBanner.png" alt="New products" >
				<h4><span>Shopping Cart</span></h4>
			</section>
			<section class="main-content">				
				<div class="row">
					<div class="span9">					
						<h4 class="title"><span class="text"><strong>Your</strong> Cart</span></h4>
						<table class="table table-striped">
							<thead>
								<tr>
									<th>Remove</th>
									<th>Image</th>
									<th>Product Name</th>
									<th>Quantity</th>
									<th>Unit Price</th>
									<th>Total</th>
								</tr>
							</thead>
							<tbody>
							
							<c:forEach items="${carts}" var="cart">
							<tr>
									<td><input type="checkbox" name="IDCheck"  value="${cart.productDetail.productID}"></td>
									<td><a href="product_detail?productID=${cart.productDetail.productID}"><img alt="" src="${cart.productDetail.image}"></a></td>
									<td>${cart.productDetail.productName}</td>
									<td name="pcount"><input type="text" name="count" class="input-mini" value="${cart.count}"></input></td>
									<td name="unitprice">${cart.productDetail.price}</td>
									<td id="subtotal" name="total"></td>
								</tr>	
							</c:forEach>
								
							</tbody>
						</table>
						<div class="yahoo"> 
						


</div> 
    <a href="?page=1" class="aaa" title="首页">first</a>   
<a href="?page=${pagehelper.getPageNumber()-1}" class="aaa" title="上一页">pre</a>   
<a href="?page=${pagehelper.getPageNumber()+1}"  class="aaa" title="下一页">next</a>   
<a href="?page=${pagehelper.getTotalPage()}" class="aaa" title="末页">end</a> 

<div class="g-cf g-pagerwp">  
    <div style="" class="g-pager"> 
    </div>  
</div>  
						<hr>
						<p class="cart-total right">
							<strong>总价：</strong>:<span id="total">	$100.00</span><br>
							
						</p>
						<hr/>
						<p class="buttons center">				
							<button class="btn" type="button" id="update">修改订单</button>
						
							<button class="btn btn-inverse" type="submit" id="order">一键下单</button>
						</p>					
					</div>
					
		</div>
		<script src="resources/themes/js/common.js"></script>
		<script>
			$(document).ready(function() {
				$('#checkout').click(function (e) {
					document.location.href = "checkout.html";
				})
			});
		</script>
		
		<table id="product" >
							<c:forEach items="${allcart}" var="cart">
							<tr>
							<td>
							<input  value="${cart.productDetail.productID}" name="aproductID" check="" type="hidden"/>
							</td>
							<td >
							<input  value="${cart.count}" name="acount" type="hidden"/>
							</td>
							<td >
							<input  value="${cart.productDetail.price}" name="aprice" type="hidden"/>
							</td>
							</tr>
							</c:forEach>
							</table>
				
    </body>
</html>