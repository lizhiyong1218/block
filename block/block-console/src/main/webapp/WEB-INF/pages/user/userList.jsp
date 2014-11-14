<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<!-- 主页面中存在这些文件,不能再引入
<link rel="stylesheet" type="text/css" href="<%=basePath %>resources/jquery-easyui-1.3.4/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>resources/jquery-easyui-1.3.4/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>resources/jquery-easyui-1.3.4/demo.css">
	<script type="text/javascript" src="<%=basePath %>resources/js/common/jquery-1.9.0.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>resources/jquery-easyui-1.3.4/jquery.easyui.min.js"></script> 
	<script type="text/javascript" src="<%=basePath %>resources/js/common/globalConfig.js"></script> 
-->	

<script type="text/javascript" src='<%=basePath %>resources/js/user/userList.js' ></script>
<table id="allfiletable"></table>
<div id="toolbar">
	<a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="Open_Dialog()">add</a>
	<a class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeM()">del</a>
	<a class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="updPage()">upd</a>
</div>
 <div id="mydialog" style="display:none;padding:5px;width:400px;height:200px;" title="弹框练习"> 
</div> 