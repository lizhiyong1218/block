<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="easyui-panel"
	data-options="title:'流程信息',plain:true,fitWidth:true,cls:'mt20'">
	<form id="processInstanceInfoForm">
	    <input type="hidden" id="activityId" name="activityId"/>
		<table class="form-tb" style="width: 98%">
			<tbody>
				<tr>
				 <tr>
				    
				    <th style="width: 200px;"><em class="cred">*</em>流程创建人：</th>
				    <td><input type="text" class="ipt" id="userId" name="userId" value="${detailVo.processStartBy}" disabled="disabled"/></td>
					
					<th style="width: 200px;"><em class="cred">*</em>创建时间：</th>
				    <td><input type="text" id="startTime" class="easyui-datebox ipt" name="startTime" value="${detailVo.processStartTime }" disabled="disabled"/></td>

					<th style="width: 200px;"><em class="cred">*</em>流程节点：</th>
				    <td><input type="text" class="ipt" id="processInstanceState" name="processInstanceState" value="${detailVo.taskName}" disabled="disabled"/></td>
					<th style="width: 200px;"><em class="cred">*</em>当前处理人：</th>
				    <td><input type="text" class="ipt" id="currentUserId" name="currentUserId" value="${detailVo.assignee}" disabled="disabled"/></td>
				</tr>
			</tbody>
		</table>
	</form>
</div>