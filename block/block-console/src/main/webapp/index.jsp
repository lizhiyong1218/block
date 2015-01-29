<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>block</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>resources/css/base.css">
	<%-- <link rel="stylesheet" type="text/css" href="<%=basePath %>resources/css/ui.css">   --%>
	<link rel="stylesheet" type="text/css" href="<%=basePath %>resources/css/myicons.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>resources/jquery-easyui-1.3.4/themes/default/easyui.css">
	<%-- <link rel="stylesheet" type="text/css" href="<%=basePath %>resources/jquery-easyui-1.3.4/demo.css"> --%>
	<script type="text/javascript" src="<%=basePath %>resources/js/common/jquery-1.9.0.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>resources/jquery-easyui-1.3.4/jquery.easyui.min.js"></script> 
	<script type="text/javascript" src="<%=basePath %>resources/js/common/globalConfig.js"></script> 
	<script type="text/javascript" src="<%=basePath %>resources/js/common/easyuicommons.js"></script>
	<script type="text/javascript" src="<%=basePath %>resources/js/common/myextend.js"></script> 
  </head>
  
  <body  class="easyui-layout" >
	    <!-- 
	    <div data-options="region:'north',title:'North Title',split:true,href:'frame/north.jsp'" style="height:100px;"></div>
	    <div data-options="region:'south',title:'South Title',split:true,href:'frame/south.jsp'" style="height:100px;"></div>
	    <div data-options="region:'east',title:'East',split:true,href:'frame/east.jsp'" style="width:100px;"></div>
	     -->
	    <div data-options="region:'west', title:'功能菜单',split:true,href:'frame/west.jsp'" style="width:150px;"></div>
	    <div data-options="region:'center', split:true,href:'frame/center.jsp'"  style="padding:5px;background:#eee;" >
	    </div>
  </body>
</html>
