<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%-- 
<script type="text/javascript" src="${basePath}/resources/activiti/js/activiti/workflow.js"></script>
<script type="text/javascript" src="${basePath}/resources/activiti/js/activiti/verified.js"></script>
<script type="text/javascript" src="${basePath}/resources/activiti/js/activiti/drawGraph.js"></script>
     --%>
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'north',border:false" class="toolbar-region">
		<form id="taskInfoForm">
			<input type="hidden" name="taskId" id="taskId" value="${taskId}" /> 
			<input type="hidden" name="bussinessId" id="bussinessId" value="${bussinessId}" /> 
			<input type="hidden" name="processInstanceId" id="processInstanceId" value="${processInstanceId}" /> 
			<input type="hidden" name="taskName" id="taskName" value="${taskName}" /> 
			<input type="hidden" name="suspended" id="suspended" value="${suspended}" />
			<input type="hidden" name="taskKey" id="taskKey" value="${taskKey}" />
		</form>
	</div>
	
	<div data-options="region:'center',border:false">
	    <!-- 头页面 -->
		<%@include file="header.jsp"%> 
		<br>
		<br>
		<br>
		<!-- 审核历史页面 -->
	  <%@include file="footer.jsp"%>  
	</div>
</div>
