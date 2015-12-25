<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>重复登录拦截页面</title>
  </head>
  <body>
  	  <b>test</b>
  	  <form action="<%=basePath%>token/subform" method="post">
		 <input type="text" name="blok.token.name" value="${tokenName}"/>
		 <input type="text" name="blok.token.value" value="${tokenValue}"/>
		 <input type="submit" value="ok" name="ok"/>
	 </form> 
 </body> 
</html>
 