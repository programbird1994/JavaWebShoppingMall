 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>锡货超市</title>
<link href="resources/tb/css/public.css" type="text/css" rel="stylesheet"/>
        <link rel="stylesheet" type="text/css" href="resources/tb/css/base.css"/>
        <script type="text/javascript" src="resources/tb/js/jquery_cart.js"></script>
        <link rel="stylesheet" type="text/css" href="resources/tb/css/buyConfirm.css" />
		<script src="resources/tb/js/jquery-1.11.1.min.js" type="text/javascript"></script>
   		 <script src="resources/tb/js/unslider.min.js" type="text/javascript"></script>
  	     <script src="resources/tb/js/index.js" type="text/javascript"></script>
<script type="text/javascript">


window.onload = function(){
	//alert($("#productID").attr("value"));
	var path = "${pageContext.request.contextPath}/jsp";
	$("#pay").click(function(){
		$.ajax({
			type:"POST",
			dataType:"text",
			data:"password="+$("#password").attr("value"),
			url:path+"/checkPassword",
			
			success:function(data){
				//alert("orderID"+$("#orderID").val());
				if(data=="su")
					{
					$.ajax({
						type:"POST",
						dataType:"text",
						data:"orderID="+$("#orderID").val()+"&productID="+$("#productID").attr("value"),
						url:path+"/topay",
						
						success:function(data){
							//alert("orderID"+$("#orderID").val());
							if(data=="su")
								alert("支付成功");
							else
								alert("余额不足");
						},
					})
					}
				else
					alert("密码错误");
			},
		})
	})
	
new tab('test4-input-input_tab1-input_tab2', '-');
}
function tab(o, s, cb, ev){ //tab换类
var $ = function(o){return document.getElementById(o)};
var css = o.split((s||'_'));
if(css.length!=4)return;
this.event = ev || 'onclick';
o = $(o);
if(o){
this.ITEM = [];
o.id = css[0];
var item = o.getElementsByTagName(css[1]);
var j=1;
for(var i=0;i<item.length;i++){
if(item[i].className.indexOf(css[2])>=0 || item[i].className.indexOf(css[3])>=0){
if(item[i].className == css[2])o['cur'] = item[i];
item[i].callBack = cb||function(){};
item[i]['css'] = css;
item[i]['link'] = o;
this.ITEM[j] = item[i];
item[i]['Index'] = j++;
item[i][this.event] = this.ACTIVE;
}
}
return o;
}
}
tab.prototype = {
ACTIVE:function(){
var $ = function(o){return document.getElementById(o)};
this['link']['cur'].className = this['css'][3];
this.className = this['css'][2];
try{
$(this['link']['id']+'_'+this['link']['cur']['Index']).style.display = 'none';
$(this['link']['id']+'_'+this['Index']).style.display = 'block';
}catch(e){}
this.callBack.call(this);
this['link']['cur'] = this;
}
}
</script>
</head>

<body>
 <input type="hidden" value="${orderID}" id="orderID"/>
<jsp:include page="/jsp/top"   />
<div class="border_top_cart">

      <label>请输入你的支付密码：</label> <input type="password" id="password"/>
      <hr/>
      <button id="pay"> 确认支付</button>
</div>
<input type="hidden" id="productID" value="${productID}"/>

    
</body>
</html>
