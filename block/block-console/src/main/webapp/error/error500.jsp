<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>异常页面</title>
	 
  </head>
  <body>
     	500页面
     	<!--  -->
     	
		<%=request.getAttribute("javax.servlet.error.status_code") %>
		
     	
 </body> 
</html>
 