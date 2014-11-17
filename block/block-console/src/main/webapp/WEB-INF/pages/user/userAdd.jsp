<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<script type="text/javascript" src='<%=basePath %>resources/js/user/userList.js' ></script>

<script language="javascript" type="text/javascript">
</script> 

 <label class="lbInfo">姓名：</label>
<input id="title" name="title" type="text" class="easyui-validatebox" required="true" />
<br/>
<br/>
 <label class="lbInfo">描述：</label>
<input id="desc" name="title"  type="text" class="easyui-validatebox" required="true" />
