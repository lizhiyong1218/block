<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<script type="text/javascript" src='<%=basePath %>resources/js/filecenter/file_listall.js' ></script>

<input id="id" name="id" type="hidden" class="easyui-validatebox" required="true" value="${media.id}" />
 <label class="lbInfo">姓名：</label>
<input id="title" name="title" type="text" class="easyui-validatebox" required="true" value="${media.title}" />
<br/>
<br/>
 <label class="lbInfo">描述：</label>
<input id="desc" name="title"  type="text" class="easyui-validatebox" required="true" value="${media.desc}" />
