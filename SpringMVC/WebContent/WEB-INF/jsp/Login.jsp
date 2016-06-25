 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
	<head>
	<script type="text/javascript">
function test(){
var f = document.getElementsByTagName("form")[1];
f.action=f.action+"username="+document.getElementById("username").value+"&password="+document.getElementById("password").value;
//f.submit();
}
</script>
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
		<script>
		$(document).ready(function(){
			$("#submit1").click(function(){
				var path = "${pageContext.request.contextPath}/jsp";
			//	alert("in")
				$.ajax({
					type:"POST",
					dataType:"text",
					data:"username="+$("#username").val(),
					url:path+"/checkUserNameUsed",
					success:function(data){
						if(data=="exist")
					{
							$.ajax({
								type:"POST",
								dataType:"text",
								data:"username="+$("#username").val()+"&Userpassword="+$("#password").val(),
								url:path+"/validate",
								success:function(data)
								{
									//alert(data)
									if(data=="exist")
										{
										
										 $("#logintable").submit();
										}
									else
										{
										alert("密码错误");
										}
								}
								
							}
									)
					}
						else
						{
							//$("#checkusername").attr("check",true);
							alert("用户名不存在");
						}
					},
					error:function()
					{
						alert("fail")
					}
				})
				
				
			})
			
			$("#register").click(function(){
				if($("#checkusername").attr("check")=="true"&&$("#checkemail").attr("check")=="true")
					{
					    alert("into")
					var path = "${pageContext.request.contextPath}/jsp";
					$.ajax({
						type:"POST",
						dataType:"text",
						data:"username="+$("#rusername").attr("value")+"&email="+$("#remail").attr("value")+"&password="+$("#rpassword").attr("value"),
						url:path+"/register1",
						success:function(data){
							if(data=="su")
						{
								alert("注册成功");
								return false;
						}
							else
							{
								//$("#checkusername").attr("check",true);
								alert("注册失败");
							}
						},
						error:function()
						{
							//alert("fail")
						}
					})
					
					}
				else
					{
					    alert("请先检测是否可用");
					}
			})
			
			$("#checkusername").click(function(){
				//alert("1")
				var path = "${pageContext.request.contextPath}/jsp";
				$.ajax({
					type:"POST",
					dataType:"text",
					data:"username="+$("#rusername").attr("value"),
					url:path+"/checkUserNameUsed",
					success:function(data){
						if(data=="exist")
					{
							alert("用户名已存在");
							return false;
					}
						else
						{
							$("#checkusername").attr("check",true);
							alert("用户名可用");
						}
					},
				})
			});
			
			$("#checkemail").click(function(){
				var reg=new RegExp("[0-9a-zA-Z]{5,}@[0-9a-zA-Z]{2,}.com");
				if(reg.test($("#remail").attr("value"))==true)
					{
					alert("该邮箱格式正确");
					
					var path = "${pageContext.request.contextPath}/jsp";
					$.ajax({
						type:"POST",
						dataType:"text",
						data:"email="+$("#remail").attr("value"),
						url:path+"/checkEmailUsed",
						success:function(data){
							if(data=="exist")
						{
								alert("邮箱已存在");
								
								return false;
						}
							else
								{
								$("#checkemail").attr("check",true);
								alert("邮箱可注册");
								}
						},
					})
					}
				else
					{
					alert("该邮箱格式不正确");
					}
				
			})
			
		})
		</script>
	</head>
    <body>		
		<jsp:include page="/jsp/top"   />
			<section class="header_text sub">
			<img class="pageBanner" src="resources/themes/images/pageBanner.png" alt="New products" >
				<h4><span>注册与登录</span></h4>
			</section>			
			<section class="main-content">				
				<div class="row">
					<div class="span5">					
						<h4 class="title"><span class="text"><strong>登录</strong> 表</span></h4>
						<form action="./index" method="post" id="logintable">
							<input type="hidden" name="next" value="/">
							<fieldset>
								<div class="control-group">
									<label class="control-label">用户名</label>
									<div class="controls">
										<input type="text" placeholder="Enter your username" name="username" id="username" class="input-xlarge">
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">密码</label>
									<div class="controls">
										<input type="password" placeholder="Enter your password" name="Userpassword" id="password" class="input-xlarge">
									</div>
								</div>
								<div class="control-group">
									<input tabindex="3" class="btn btn-inverse large" id="submit1"  value="登录">
									<hr>
									<p class="reset">Recover your <a tabindex="4" href="#" title="Recover your username or password">username or password</a></p>
								</div>
							</fieldset>
						</form>				
					</div>
					<div class="span7">					
						<h4 class="title"><span class="text"><strong>注册</strong> 表</span></h4>
						<form action="#" method="post" class="form-stacked">
							<fieldset>
								<div class="control-group">
									<label class="control-label">用户名</label>
									<div class="controls">
										<input type="text" placeholder="Enter your username" class="input-xlarge" id="rusername">
										<input class="class="btn btn-inverse" value="check userName" type="button" check="false" id="checkusername"/>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">邮箱:</label>
									<div class="controls">
										<input type="text" placeholder="Enter your email" class="input-xlarge" id="remail">
										<input class="class="btn btn-inverse" value="check email" type="button" check="false" id="checkemail"/>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">登录密码:</label>
									<div class="controls">
										<input type="password" placeholder="Enter your password" class="input-xlarge" id="rpassword">
										
									</div>
								</div>							                            
								<div class="control-group">
									<p>Now that we know who you are. I'm not a mistake! In a comic, you know how you can tell who the arch-villain's going to be?</p>
								</div>
								<hr>
								<div class="actions"><input tabindex="9" class="btn btn-inverse large" type="submit" value="确认注册" id="register"></div>
							</fieldset>
						</form>					
					</div>				
				</div>
			</section>			
			<section id="footer-bar">
				<div class="row">
					<div class="span3">
						<h4>Navigation</h4>
						<ul class="nav">
							<li><a href="./index.html">Homepage</a></li>  
							<li><a href="./about.html">About Us</a></li>
							<li><a href="./contact.html">Contac Us</a></li>
							<li><a href="./cart.html">Your Cart</a></li>
							<li><a href="./register.html">Login</a></li>							
						</ul>					
					</div>
					<div class="span4">
						<h4>My Account</h4>
						<ul class="nav">
							<li><a href="#">My Account</a></li>
							<li><a href="#">Order History</a></li>
							<li><a href="#">Wish List</a></li>
							<li><a href="#">Newsletter</a></li>
						</ul>
					</div>
					<div class="span5">
						<p class="logo"><img src="resources/themes/images/logo.png" class="site_logo" alt=""></p>
						<p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. the  Lorem Ipsum has been the industry's standard dummy text ever since the you.</p>
						<br/>
						<span class="social_icons">
							<a class="facebook" href="#">Facebook</a>
							<a class="twitter" href="#">Twitter</a>
							<a class="skype" href="#">Skype</a>
							<a class="vimeo" href="#">Vimeo</a>
						</span>
					</div>					
				</div>	
			</section>
			<section id="copyright">
				<span>Copyright 2013 bootstrappage template  All right reserved.</span>
			</section>
		</div>
		<script src="resources/themes/js/common.js"></script>
		<script>
			$(document).ready(function() {
				$('#checkout').click(function (e) {
					document.location.href = "checkout.html";
				})
			});
		</script>		
    </body>
</html>