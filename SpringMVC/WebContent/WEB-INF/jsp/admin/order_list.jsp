<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="scripts/jquery/jquery-1.7.1.js"></script>
<link href="style/authority/basic_layout.css" rel="stylesheet" type="text/css">
<link href="style/authority/common_style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="scripts/authority/commonAll.js"></script>
<script type="text/javascript" src="scripts/fancybox/jquery.fancybox-1.3.4.js"></script>
<script type="text/javascript" src="scripts/fancybox/jquery.fancybox-1.3.4.pack.js"></script>
<link rel="stylesheet" type="text/css" href="style/authority/jquery.fancybox-1.3.4.css" media="screen"></link>
<script type="text/javascript" src="scripts/artDialog/artDialog.js?skin=default"></script>
<title>信息管理系统</title>
<script type="text/javascript">
	$(document).ready(function(){
		
		$("[name='update']").each(function(){
			
			$(this).click(function(){
				var path = "${pageContext.request.contextPath}/jsp/admin";
				var status=$(this).parent().parent().find("option:selected").text();
				var orderID=$(this).parent().parent().find("#orderID").text();
				alert(orderID);
				var productID=$(this).parent().parent().find("#productID").attr("value");
				$.ajax({
					type:"POST",
					data:"status="+status+"&orderID="+orderID+"&productID="+productID,
					dataType:"text",
					url:path+"/updateOrder",
					success:function(data){
						if(data=="su")
						alert("success")
					},
				error:function()
				{
					alert("error");
				},
				})
			})
			
		})
		/** 新增   **/
	    $("#addBtn").fancybox({
	    	'href'  : 'product_edit',
	    	'width' : 733,
	        'height' : 530,
	        'type' : 'iframe',
	        'hideOnOverlayClick' : false,
	        'showCloseButton' : true,
	        'onClosed' : function() { 
	        	window.location.href = 'product_list';
	        }
	    });
		
	    /** 导入  **/
	    $("#importBtn").fancybox({
	    	'href'  : '/xngzf/archives/importFangyuan.action',
	    	'width' : 633,
	        'height' : 260,
	        'type' : 'iframe',
	        'hideOnOverlayClick' : false,
	        'showCloseButton' : false,
	        'onClosed' : function() { 
	        	window.location.href = 'product_list';
	        }
	    });
		
	 
	    
	    $("a.scan").fancybox({
	    	'width' : 733,
	        'height' : 530,
	        'type' : 'iframe',
	        'hideOnOverlayClick' : false,
	        'showCloseButton' : true,
	        'onClosed' : function() { 
	        	window.location.href = 'product_list';
	        }
	    });
	    
	});
	/** 用户角色   **/
	var userRole = '';

	/** 用户角色   **/
	var userRole = '';

	/** 模糊查询来电用户  **/
	function search(){
		$("#submitForm").attr("action", "house_list.html?page=" + 1).submit();
	}

	/** 新增   **/
	function add(){
		$("#submitForm").attr("action", "/xngzf/archives/luruFangyuan.action").submit();	
	}
	 
	
	
	/** 删除 **/
	function del(fyID){
		// 非空判断
		if(fyID == '') return;
		if(confirm("您确定要删除吗？")){
			$("#submitForm").attr("action", "/SpringMVC/jsp/admin/delete?productID=" + fyID).submit();			
		}
	}
	
	/** 批量删除 **/
	function batchDel(){
		if($("input[name='IDCheck']:checked").size()<=0){
			art.dialog({icon:'error', title:'友情提示', drag:false, resize:false, content:'至少选择一条', ok:true,});
			return;
		}
		// 1）取出用户选中的checkbox放入字符串传给后台,form提交
		var allIDCheck = "";
		$("input[name='IDCheck']:checked").each(function(index, domEle){
			bjText = $(domEle).parent("td").parent("tr").last().children("td").last().prev().text();
// 			alert(bjText);
			// 用户选择的checkbox, 过滤掉“已审核”的，记住哦
			if($.trim(bjText)=="已审核"){
// 				$(domEle).removeAttr("checked");
				$(domEle).parent("td").parent("tr").css({color:"red"});
				$("#resultInfo").html("已审核的是不允许您删除的，请联系管理员删除！！！");
// 				return;
			}else{
				allIDCheck += $(domEle).val() + ",";
			}
		});
		// 截掉最后一个","
		if(allIDCheck.length>0) {
			allIDCheck = allIDCheck.substring(0, allIDCheck.length-1);
			// 赋给隐藏域
			$("#allIDCheck").val(allIDCheck);
			alert($("#allIDCheck").val());
			if(confirm("您确定要批量删除这些记录吗？")){
				// 提交form
				$("#submitForm").attr("action", "delete").submit();
			}
		}
	}

	/** 普通跳转 **/
	function jumpNormalPage(page){
		var totalPage='${page.totalPage}'
		if(page<1 |page >totalPage)
			{
			alert("已是首页或尾页");
			}
		else
			{
		$("#submitForm").attr("action", "order_list?page=" + page).submit();
			}
	}
	
	/** 输入页跳转 **/
	function jumpInputPage(pageNum){
		// 如果“跳转页数”不为空
		
		var totalPage='${page.totalPage}'
		//alert(totalPage);
		if(pageNum!= ''){
			//var pageNum = parseInt($("#jumpNumTxt").val());
			// 如果跳转页数在不合理范围内，则置为1
			if(pageNum<1 | pageNum>totalPage){
				art.dialog({icon:'error', title:'友情提示', drag:false, resize:false, content:'请输入合适的页数，\n自动为您跳到首页', ok:true,});
				//alert("已经到达首页")
				pageNum = 1;
			}
		
			$("#submitForm").attr("action", "product_list?page=" + pageNum).submit();
		}else{
			
			art.dialog({icon:'error', title:'友情提示', drag:false, resize:false, content:'请输入合适的页数，\n自动为您跳到首页', ok:true,});
			$("#submitForm").attr("action", "product_list?page=" + 1).submit();
			
		}
	}
</script>
<style>
	.alt td{ background:black !important;}
</style>
</head>
<body>
	<form id="submitForm" name="submitForm" action="" method="post">
		<input type="hidden" name="allIDCheck" value="" id="allIDCheck"/>
		<input type="hidden" name="fangyuanEntity.fyXqName" value="" id="fyXqName"/>
		<div id="container">
			<div class="ui_content">
				<div class="ui_text_indent">
					<div id="box_border">
						<div id="box_top">搜索</div>
						<div id="box_center">
							商品名
							<select name="fangyuanEntity.fyXqCode" id="fyXq" class="ui_select01" onchange="getFyDhListByFyXqCode();">
                                <option value=""
                                >--请选择--</option>
                                <option value="6">瑞景河畔</option>
                                <option value="77">蔚蓝小区</option>
                                <option value="83">和盛园小区</option>
                            </select>

							品牌
							<select name="fangyuanEntity.fyDhCode" id="fyDh" class="ui_select01">
                                <option value="">--请选择--</option>
                            </select>
							价格
							<select name="fangyuanEntity.fyHxCode" id="fyHx" class="ui_select01">
                                <option value="">--请选择--</option>
                                <option value="76">一室一厅一卫</option>
                                <option value="10">两室一厅一卫</option>
                                <option value="14">三室一厅一卫</option>
                                <option value="71">三室两厅一卫</option>
                            </select>
							库存
							<select name="fangyuanEntity.fyStatus" id="fyStatus" class="ui_select01">
                                <option value="">--请选择--</option>
                                <option value="26">在建</option>
                                <option value="25">建成待租</option>
                                <option value="13">已配租</option>
                                <option value="12">已租赁</option>
                                <option value="24">腾退待租</option>
                                <option value="23">欠费</option>
                                <option value="27">其他</option>
                            </select>

							描述&nbsp;&nbsp;<input type="text" id="fyZldz" name="fangyuanEntity.fyZldz" class="ui_input_txt02" />
						</div>
						<div id="box_bottom">
							<input type="button" value="查询" class="ui_input_btn01" onclick="search();" /> 
							
							
						</div>
					</div>
				</div>
			</div>
			<div class="ui_content">
				<div class="ui_tb">
					<table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
						<tr>
							
							
							<th>订单号</th>
							<th>订单商品</th>
							<th>商品数量</th>
							<th>商品品牌</th>
							<th>订单状态</th>
							<th>收货地址</th>
							
							<th>操作</th>
						</tr>
							<c:forEach items="${orders}" var="order"> 
							<tr>
							
								<td id="orderID">${order.orderID}</td>
								<td value="${order.productDetail.productID}" id="productID">
								${order.productDetail.productName}
								</td>
								<td>${order.count}</td>
								<td>${order.productDetail.brand}</td>
								<td name="status" value="1"><select>
								<option>${order.status}</option>
								<option>已发货</option>
								<option>已收货</option>
								</select</td>
								<td>${order.address}</td>
								
								
								
								
								
								
								<td value="1">
									<a  name="update" class="edit">确认修改</a> 
									
								</td>
							</tr>
						</c:forEach>
						
					</table>
				</div>
				<div class="ui_tb_h30">
					<div class="ui_flt" style="height: 30px; line-height: 30px;">
						共有
						<span class="ui_txt_bold04">${page.totalCount}</span>
						条记录，当前第
						<span class="ui_txt_bold04">${page.pageNumber}
						/
						${page.totalPage}</span>
						页
					</div>
					<div class="ui_frt">
						<!--    如果是第一页，则只显示下一页、尾页 -->
						
							<input type="button" value="首页" class="ui_input_btn01" onclick="jumpNormalPage(1);" />
							<input type="button" value="上一页" class="ui_input_btn01" onclick="jumpNormalPage(${page.pageNumber-1});" />
							<input type="button" value="下一页" class="ui_input_btn01"
								onclick="jumpNormalPage(${page.pageNumber+1});" />
							<input type="button" value="尾页" class="ui_input_btn01"
								onclick="jumpNormalPage(${page.totalPage});" />
						
						
						
						<!--     如果是最后一页，则只显示首页、上一页 -->
						
						转到第<input type="text" id="jumpNumTxt" class="ui_input_txt01" />页
							 <input type="button" class="ui_input_btn01" value="跳转" onclick="jumpInputPage(9);" />
					</div>
				</div>
			</div>
		</div>
	</form>

</body>
</html>
