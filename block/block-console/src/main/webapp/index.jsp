<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>block</title>
	 
	<link rel="stylesheet" type="text/css" href="<%=basePath %>resources/css/base.css">
	<%-- <link rel="stylesheet" type="text/css" href="<%=basePath %>resources/css/ui.css">   --%>
	<link rel="stylesheet" type="text/css" href="<%=basePath %>resources/css/myicons.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>resources/jquery-easyui-1.3.4/themes/default/easyui.css">
	<link href="${basePath}/resources/assets/qtip/jquery.qtip.min.css?jsVersion=${jsVersion}" type="text/css" rel="stylesheet" />
	<%-- <link rel="stylesheet" type="text/css" href="<%=basePath %>resources/jquery-easyui-1.3.4/demo.css"> --%>
	<script type="text/javascript" src="<%=basePath %>resources/js/common/jquery-1.9.0.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>resources/jquery-easyui-1.3.4/jquery.easyui.min.js"></script> 
	<script type="text/javascript" src="<%=basePath %>resources/js/common/globalConfig.js"></script> 
	<script type="text/javascript" src="<%=basePath %>resources/js/common/easyuicommons.js"></script>
	<script type="text/javascript" src="<%=basePath %>resources/js/common/myextend.js"></script>
	
	
	<script src="${basePath}/resources/assets/qtip/jquery.qtip.pack.js?jsVersion=${jsVersion}" type="text/javascript"></script>
	<script	src="${basePath}/resources/js/activiti/drawGraph.js?jsVersion=${jsVersion}"></script>
	<script	src="${basePath}/resources/js/common/jquery.mask.extend.js?jsVersion=${jsVersion}"></script> 
	<%--
	<script	src="${basePath}/resources/js/common/jquery.extend.js?jsVersion=${jsVersion}"></script> 
	<script	src="${basePath}/resources/js/common/jquery.toolbar.js?jsVersion=${jsVersion}"></script>
	  --%>
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
