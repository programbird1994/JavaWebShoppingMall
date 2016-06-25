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
<script  language="javascript" type="text/javascript">

$(document).ready(function() {
	//alert("11")
})
window.onload = function(){
	$("#payBtn").click(function(){
		window.location.href="pay1?orderID="+$("#orderID").val();
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

        <div class="container payment-con">
    <form  target="_blank" action="#" id="pay-form" method="post">
            <div class="order-info">
                <div class="msg">
                    <h3>您的订单已提交成功！付款咯～</h3>
                    <p></p>
                    
                                                                    <p class="post-date">成功付款后，7天发货</p>
                                    </div>
                <div class="info">
                    <p>
                        金额：<span class="pay-total">${price}元</span>
                    </p>
                    <p>
                        订单：${orderID}                    </p>
                    <p>
                        配送：${consignee}                                   <span class="line">/</span>
                                    ${tel}                                    <span class="line">/</span>
                                    ${region}, ${street}                                                                <span class="line">/</span>
                                                                    不限送货时间                                    <span class="line">/</span>
                                    个人电子发票                                                    </p>
                </div>
                <div class="icon-box">
                    <i class="iconfont"><img src="resources/tb/images/yes_ok.png"></i>
                </div>
            </div>
            
     

            <div class="box-ft clearfix">
                    <input  class="btn btn-primary" value="下一步" id="payBtn">
                    
                    <span class="tip"></span>
                </div>
            </div>
</form>  
</div>

    
</body>
</html>
