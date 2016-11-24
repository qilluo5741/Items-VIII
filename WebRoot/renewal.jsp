<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("url","login.jsp");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>恭喜你注册成功！</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript">
		function load(){
			if("${!empty url}"=="true"){
				window.location.href="<%=basePath%>"+"${url}";
			}
		}
	</script>
  </head>
  
  <body onload="javascript:setTimeout('load()',11500);" bgcolor="#ccc">
  <div class="screenbg">
	<object id="swftitlebar" data="image/79514.swf" width="100%" height="220" type="application/x-shockwave-flash">
	      <param name="allowScriptAccess" value="always">
	      <param name="allownetworking" value="all">
	      <param name="allowFullScreen" value="true">
	      <param name="wmode" value="transparent">
	      <param name="menu" value="false">
	      <param name="scale" value="noScale">
	      <param name="salign" value="1">
	</object>
</div>
<marquee direction="left" dir="ltr" onmouseout="15"><a href="login.jsp" style="text-decoration: none;">恭喜${userName}注册成功!赶快去登录试试吧!</a></marquee>
  </body>
</html>
