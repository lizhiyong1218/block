<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>west页面</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
  </head>
  <body>
     <div class="easyui-accordion" data-options="fit:true,border:false"  style="width:700px;height:300px;" >
     		<div title="个人信息"  data-options="iconCls:'icon-personal'"  style="padding:10px;">
				<input type="button" value=" 个人信息" onclick="tab_add('centertabs','个人信息','user/userList.do')"/>
			</div>
			<div title="用户管理"  data-options="iconCls:'icon-usermanager'"  style="padding:10px;">
				<input type="button" value=" 用户管理 "  />
			</div>
			<div title="文件管理" data-options="iconCls:'icon-filemanage'"  style="padding:10px;">
				<a onclick="openPanel('fileList')" style="cursor:pointer;">文件列表</a>
			</div>
			<div title="我的邮箱"  data-options="iconCls:'icon-mailmanage'" style="padding:10px">
				任务管理
			</div>
			<div title="系统管理" data-options="iconCls:'icon-taskmanage'" style="padding:10px">
				<input type="button" value="数据字典" onclick="tab_add('centertabs','数据字典','common/toListPage.do?pagePath=/dictionary/dictionaryList')"/>
			</div>
			
			<div title="工作流管理" data-options="iconCls:'icon-taskmanage'" style="padding:10px">
				<input type="button" value="流程管理" onclick="tab_add('centertabs','流程管理','common/toListPage.do?pagePath=/activiti/activitiList')"/>
			</div>
			
			<div title="首页管理" data-options="iconCls:'icon-indexmanager'" style="padding:10px">
				<input type="button" value="文章管理" onclick="tab_add('centertabs','文章管理','common/toListPage.do?pagePath=/article/articleList')"/>
				<input type="button" value="任务管理" onclick="tab_add('centertabs','让我管理','common/toListPage.do?pagePath=/quartz/quartzList')"/>
			</div>
			 
			<div title="工作流管理" data-options="selected:true,iconCls:'icon-taskmanage'" style="padding:10px">
				<input type="button" value="流程管理" onclick="tab_add('centertabs','流程管理','common/toListPage.do?pagePath=/activiti/activitiList')"/>
			</div>
			
		</div>
 </body> 
</html>
 