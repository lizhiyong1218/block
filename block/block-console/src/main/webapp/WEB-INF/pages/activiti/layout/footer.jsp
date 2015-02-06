<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<div id="poDetailTabs" class="easyui-tabs" data-options="fitWidth:true">
	<div title="历史审核信息">
		<table id="taskVerifyInfoList" class="easyui-datagrid"
			style="width: auto;" data-options="rownumbers:true,
			singleSelect:false,striped:true,fit:false,checkOnSelect: true,url:'${basePath}/activitiTask/taskHistoryInfo.do?processInstanceId=${detailVo.processInstanceId}'">
			<thead>
				<tr>
					
					<th
						data-options="field:'taskName',width:120,align:'center',halign:'center'">审批环节</th>
					
					<th
						data-options="field:'assignee',width:120,align:'center',halign:'center'">审批人</th>
						
					<th
						data-options="field:'startTime',width:120,align:'center',halign:'center'">任务开始时间</th>
					<th
						data-options="field:'endTime',width:120,align:'center',halign:'center'">任务结束时间</th>
					
					<th
						data-options="field:'owner',width:260,align:'center',halign:'center'">审核信息</th>
						
					<th
						data-options="field:'description',width:120,align:'center',halign:'center'">任务描述</th>
				</tr>
			</thead>
		</table>
	</div>
</div>