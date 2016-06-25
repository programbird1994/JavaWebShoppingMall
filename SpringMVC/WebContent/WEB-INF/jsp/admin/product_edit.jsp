<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<title>信息管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type="text/javascript" src="scripts/jquery/jquery-1.7.1.js"></script>
<link href="style/authority/basic_layout.css" rel="stylesheet" type="text/css">
<link href="style/authority/common_style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="scripts/authority/commonAll.js"></script>
<script type="text/javascript" src="scripts/jquery/jquery-1.4.4.min.js"></script>
<script src="scripts/My97DatePicker/WdatePicker.js" type="text/javascript" defer="defer"></script>
<script type="text/javascript" src="scripts/artDialog/artDialog.js?skin=default"></script>
<script type="text/javascript">
	$(document).ready(function() {
		
		
		$("[name='category']").each(function(){
			if($(this).val()==$("#categoryID").val())
				{
				 $(this).attr("selected","selected");
				}
		})
		
	if($("#currentimage").val()=='')
		{
		//alert("12");
		$("#currentImage").hide();
		}
		
		$("#submitbutton").click(function() {
			if(validateForm()){
				var cate=$("#category  option:selected").val();
				
				$("#cate").val(cate);
				//alert("1")
				checkFyFhSubmit();
			}
		});
		
		/*
		 * 取消
		 */
		$("#cancelbutton").click(function() {
			/**  关闭弹出iframe  **/
			window.parent.$.fancybox.close();
		});
		
		var result = 'null';
		if(result =='success'){
			/**  关闭弹出iframe  **/
			window.parent.$.fancybox.close();
		}
	});
	
	/** 检测房源房号是否存在  **/
	function checkFyFh(){
		// 分别获取小区编号、栋号、层号、房号
		var productName = $('#productName').val();
		var price = $("#price").val();
		var brand = $("#brand").val();
		var count = $("#count").val();	
		var image = $("#image").val();
		
		if(productName!="" && price!="" && brand!="" && count!=""){
			// 给房屋坐落地址赋值
		//	$("#fyZldz").val($('#fyDh option:selected').text() + fyCh + "-" + fyFh);
			// 异步判断该房室是否存在，如果已存在，给用户已提示哦
			$.ajax({
				type:"POST",
				url:"jsp/admin/checkProductExist",
				data:{"productName":productName,"price":price, "brand":brand, "count":count, "image":image},
				dataType : "text",
				success:function(data){
// 					alert(data);
					// 如果返回数据不为空，更改“房源信息”
					if(data=="1"){
						 art.dialog({icon:'error', title:'友情提示', drag:false, resize:false, content:'该房室在系统中已经存在哦，\n请维护其他房室数据', ok:true,});
						 $("#fyFh").css("background", "#EEE");
						 $("#fyFh").focus();
						 return false;
					}
				}
			});
		}
	
	}
	
	/** 检测房源房号是否存在并提交form  **/
	function myclose()
	{
		window.parent.$.fancybox.close();
	}
	function checkFyFhSubmit(){
		// 分别获取小区编号、栋号、层号、房号
		var productName = $("#productName").val();
		var price = $("#price").val();
		var brand = $("#brand").val();
		var count = $("#count").val();	
		var image = $("#image").val();
		//alert("#currentimage"+$("#currentimage").val());
		if(productName!="" && price!="" && brand!="" && count!="" ){
			// 给房屋坐落地址赋值
			//alert("into ");
		
			$.ajax({
				type:"POST",
				url:"checkProductExist",
				data:{"productName":productName,"price":price, "brand":brand, "count":count, "image":image},
				dataType : "text",
				success:function(data){
 					//alert(data);
					// 如果返回数据不为空，更改“房源信息”
					if(data=="exist"){
						 art.dialog({icon:'error', title:'友情提示', drag:false, resize:false, content:'图片名重复，请重命名！', ok:true,});
						 $("#productName").css("background", "#EEE");
						 $("#productName").focus();
						 return false;
					}else{
						//$("#submitForm").attr("action", "SpringMVC/jsp/admin/saveOrUpdateProduct").submit();
						//alert("before")
					//	parent.$.fancybox.close();
						//$("#submitForm").submit();
						parent.$.fancybox.close();
						$("#submitForm").submit();
					
					}
				},
				error:function() {
					alert("系统ajax交互错误: ");
					}, 
			});
			
		}
		
		else{
			//alert("哈哈")
		//	$("#submitForm").attr("action", "SpringMVC/jsp/admin/saveOrUpdateProduct").submit();
			
			//parent.$.fancybox.close();
		     //   alert("before")
		//	$("#submitForm").attr("action", "SpringMVC/jsp/admin/saveOrUpdateProduct").submit();
		    $("#submitForm").submit();
		 
		   // window.parent.$.fancybox.close(function(){$("#submitForm").submit();});
		//	return false;
		//$(this).hide("slow",function(){$("#submitForm").submit();})
		
		}
		return false;
	}
	
	/** 表单验证  **/
	function validateForm(){
		if($("#productName").val()==""){
			art.dialog({icon:'error', title:'友情提示', drag:false, resize:false, content:'填写商品名', ok:true,});
			return false;
		}
		if($("#price").val()==""){
			art.dialog({icon:'error', title:'友情提示', drag:false, resize:false, content:'填写价格', ok:true,});
			return false;
		}
		if($("#brand").val()==""){
			art.dialog({icon:'error', title:'友情提示', drag:false, resize:false, content:'填写品牌', ok:true,});
			return false;
		}
		if($("#count").val()==""){
			art.dialog({icon:'error', title:'友情提示', drag:false, resize:false, content:'填写库存', ok:true,});
			return false;
		}
		if($("#image").val()==""&&$("#currentimage").val()==''){
			art.dialog({icon:'error', title:'友情提示', drag:false, resize:false, content:'上传图片', ok:true,});
			return false;
		}
		
		return true;
	}
</script>


</head>
<body>
<form id="submitForm"  action="saveOrUpdateProduct" method="post" enctype="multipart/form-data">

	<input type="hidden" name="fyID" value="" id="fyID"/>
	<input type="hidden" name="pid" value="${product.productID}"/>
	<input type="hidden" name="cate" value="" id="cate"/>
	<input type="hidden" name="currentimage" value="${product.image}" id="currentimage"/>
	<div id="container">
		<div id="nav_links">
			当前位置：商品管理&nbsp;>&nbsp;<span style="color: #1A5CC6;">添加商品</span>
			<div id="page_close">
				<a href="javascript:myclose();">
					<img src="images/common/page_close.png" width="20" height="20" style="vertical-align: text-top;"/>
				</a>
			</div>
		</div>
		<div class="ui_content">
			<table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
				<tr>
					<td class="ui_text_rt" width="80">商品名</td>
					<td class="ui_text_lt">
						<input type="text" id="productName" name="productName" value="${product.productName}"  style="border-style:none;height:100%;width:100%"/>
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt">库存</td>
					<td class="ui_text_lt">
						<input type="text" id="count" name="count"  value="${product.storeCount}" style="border-style:none;height:100%;width:100%"/>
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt">价格</td>
					<td class="ui_text_lt">
						<input type="text" id="price" name="price" value="${product.price}"  style="border-style:none;height:100%;width:100%"/>
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt">品牌</td>
					<td class="ui_text_lt">
						<input type="text" id="brand" name="brand"  value="${product.brand}"  style="border-style:none;height:100%;width:100%"/>
					</td>
				</tr>
				
				<tr>
					<td class="ui_text_rt">关键字</td>
					<td class="ui_text_lt">
						<input type="text" id="keyword" name="keyword"  value="${product.keyword}" style="border-style:none;height:100%;width:100%"/>
					</td>
				</tr>
				
				<tr>
					<td class="ui_text_rt">商品描述</td>
					<td class="ui_text_lt">
						<input type="text" id="description" name="description"  value="${product.description}" style="border-style:none;height:100%;width:100%"/>
					</td>
				</tr>
				<div >
			<tr id="currentImage">
					<td class="ui_text_rt">当前商品图片</td>
					<td class="ui_text_lt">
						<input type="image"    src="../${product.image}" /><br/>
					
					</td>
				</tr>
				</div>
				<tr>
					<td class="ui_text_rt">上传商品图片</td>
					<td class="ui_text_lt">
						<input type="file" id="image" name="image"  value="${product.image}" /><br/>
					
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt">上传图片名</td>
					<td class="ui_text_lt">
						<input type="text" id="filename" name="filename"  value="" class="ui_input_txt01" "/>
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt">类别</td>
					<td>
								<select name="category" id="category" >
								
                                
                                <c:forEach items="${categories}" var="category">
                                <option name="category" value="${category.categoryID}">${category.categoryName}</option>
                                </c:forEach>
                            </select>
        </td>
				</tr>
				<input type="hidden" value="${product.categoryID}" id="categoryID"/>
				<tr>
					<td>&nbsp;</td>
					<td class="ui_text_lt">
						&nbsp;<input id="submitbutton" type="button" value="提交" class="ui_input_btn01"/>
						&nbsp;<input id="cancelbutton" type="button" value="取消" class="ui_input_btn01"/>
					</td>
				</tr>
			</table>
		</div>
	</div>
</form>
<div style="display:none"><script  language='JavaScript' charset='gb2312'></script></div>
</body>
</html>