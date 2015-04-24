<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@taglib prefix="lizy" tagdir="/WEB-INF/tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>north页面</title>
  </head>
  <body>
     
           欢迎你:[<shiro:principal/>] <a href="${pageContext.request.contextPath}/logout">退出</a> <br/>
     
    <shiro:authenticated>
		用户[<shiro:principal/>]已身份验证通过 <br/>
	</shiro:authenticated>
	
	<shiro:notAuthenticated>
		未身份验证（包括记住我）<br/>
	</shiro:notAuthenticated>
     
    
    <lizy:hasAllRoles name="admin,user">
		用户[<shiro:principal/>]拥有角色admin 和user<br/>
	</lizy:hasAllRoles> 
     
     
 </body> 
</html>
 