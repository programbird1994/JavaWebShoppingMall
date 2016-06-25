<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="admin/scripts/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="admin/scripts/jquery.pagination.js"></script>
<link href="admin/scripts/pagination.css" rel="stylesheet" type="text/css" />
 
   
<title>Insert title here</title>
<script type="text/javascript">
$(document).ready(function(){
	
	var path = "${pageContext.request.contextPath}/jsp";
         //alert(path);
	function calculate(){
		
	   // var pageCount = 10;//总页数，需要在Calculate方法中为其赋值
	    $.ajax({
	    type:"GET",
	    data:"productID="+10,
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
	       data: "pageIndex=" + (pageIndex + 1) + "&pageSize=" + 1+"&productID="+10,  //提交两个参数：pageIndex(页面索引)，pageSize(显示条数)                
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
	        items_per_page: 1,  //显示条数
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

   
</script>
</head>

  <body>
   <!-- 求购信息显示位置 -->
   <div id="list"></div>
   <!-- 分页显示位置 -->
   <div id="pagination" style="text-align:center;" class="pagination" ></div> 


   
</body>

</html>