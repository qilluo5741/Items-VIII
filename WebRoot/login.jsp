<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>后台登录</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="shortcut icon" href="img/favicon.html">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/bootstrap-reset.css" rel="stylesheet">
    <link href="assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
    <link href="css/style.css" rel="stylesheet">
    <link href="css/style-responsive.css" rel="stylesheet" />
    <script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
	<style>
		html,body{width:100%;}
	</style>
 </head>
 <body class="login-body">
	<div class="container" >
		<form class="form-signin" action="" method="post">
       			 <h2 class="form-signin-heading">sign in now</h2>
        			<div class="login-wrap">
		            <input type="text" id="userName" class="userName form-control" name="userName" placeholder="UserName">
		            <input type="password" id="userPwd" class="userPwd form-control" name="userPwd" placeholder="Password" >
		            <label class="checkbox">
		                <input type="checkbox" value="remember-me">记住密码
		                <span class="pull-right"> <a href="javascript:void();">忘记密码？</a></span>
		            </label>
		            <button type="button" id="signin" class="btn btn-lg btn-login btn-block">Sign in</button>
            		<div id="msg" style="font-size: 12px;color: red" align="center"></div>
            		<div align="center"><a href="reguser.jsp">如还没账户，去注册一个!</a></div>
            		<div class="login-social-link">
                	<a href="javascript:void();" class="facebook">
                    <i class="icon-facebook"></i>Facebook</a>
               		<a href="javascript:void();" class="twitter">
                    <i class="icon-twitter"></i>Twitter</a> </div>
        	</div>
     	</form>
	</div>
	 <script type="text/javascript">
        	$(function(){
        		$("#signin").click(function(){
        			//得到用户名密码
        			var userName=$("#userName").val();
        			var userPwd=$("#userPwd").val();
        			if(userName==""){
        					//验证登录
        					$("#userName").css("borderColor","red");
        					$("#userName").hide(100);
        					$("#userName").show(100);
        				
        			}else if(userPwd==""){
        				//验证登录
        					$("#userPwd").css("borderColor","red");
        					$("#userPwd").hide(100);
        					$("#userPwd").show(100);
        			}
        			else{
        				//执行登录操作前 清空密码框
        				$("#userName").val("");
        				$("#userPwd").val("");
        				$.ajax({
							url:'Auth/login.deepin',
							data:{"userName":userName,"userPwd":userPwd},
							type:"post",
							success:function(res){
								if(res=="true"){
									//跳转首页
									window.location.href="index.jsp";
								}
								else{
									$("#msg").html("用户名/密码 错误！");
								}
							}
						});
        			}
        		});
        		$("input").keyup(function(){
        			//返回颜色
        			$("#userPwd").css("borderColor","#F0FFFF");
        			$("#userName").css("borderColor","#F0FFFF");
        			$("#msg").html("<Br/>");
        		})	
        	})
        </script>
 </body>
</html>
