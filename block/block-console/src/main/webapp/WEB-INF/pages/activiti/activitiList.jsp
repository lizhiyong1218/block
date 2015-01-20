<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<script
	src="${basePath}/resources/js/activiti/activitiList.js?jsVersion=${jsVersion}"></script>
<%-- 
<link href="${basePath}/resources/assets/loadmask/jquery.loadmask.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${basePath}/resources/assets/loadmask/jquery.loadmask.min.js"></script>
 --%>

<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'north',border:false" class="toolbar-region" style=" background-color: #f4f4f4;">
		<table class="toolbar-wrapper toolbar-align-left " >
		    	<tbody>
		    		<tr>
		    			<td class="toolbar-item-container">
		    				<a  class="toolbar-item l-btn   l-btn-plain" onclick="openAddProcessDialog()"   href="javascript:void(0)" >
		    					<span class="l-btn-left l-btn-icon-left">
									<span class="l-btn-icon-left icon-add"> </span>
									<span class="l-btn-text">部署新流程</span>
								</span>
		    				</a>
		    			</td>
		    			<td class="toolbar-item-container">
		    				<a class="toolbar-item dialog-tool-separator"></a>
		    			</td>
		    			
		    			 <td class="toolbar-item-container">
		    				<a  class="toolbar-item l-btn   l-btn-plain" onclick="delProcess()"   href="javascript:void(0)" >
		    					<span class="l-btn-left l-btn-icon-left">
									<span class="l-btn-icon-left icon-del"> </span>
									<span class="l-btn-text">删除</span>
								</span>
		    				</a>
		    			</td>
		    			<!-- <td class="toolbar-item-container">
		    				<a class="toolbar-item dialog-tool-separator"></a>
		    			</td>
		    			
		    			<td class="toolbar-item-container">
		    				<a  class="toolbar-item l-btn   l-btn-plain" onclick="openEdtProcessDialog()"   href="javascript:void(0)" >
		    					<span class="l-btn-left l-btn-icon-left">
									<span class="l-btn-icon-left icon-edit"> </span>
									<span class="l-btn-text">修改</span>
								</span>
		    				</a>
		    			</td> -->
		    		</tr>
		    	</tbody>
		</table>  
	</div>
	<div data-options="region:'center',border:false">
		<div class="easyui-layout" data-options="fit:true">
			<!--搜索start-->
			<div data-options="region:'north',border:false">
				<div class="search-div">
					<form name="searchForm" id="searchForm" action="" method="post">
						<table class="search-tb">
							<col width="100" />
							<col />
		
							<tbody>
								<tr>
									<th>流程名称：</th>
									<td><input class="ipt" name="processName"
										id="processName" /></td>
										
									<td><a class="easyui-linkbutton ml10" id="SearchBtn"
										data-options="iconCls:'icon-search'" onclick="doSearch()">搜索</a>
										&nbsp;<a class="easyui-linkbutton ml10" id="SearchBtn"
										data-options="iconCls:'icon-refresh'" onclick="doReset()">重置</a>
									</td>
								</tr>
							</tbody>
						</table>
					</form>
				</div>
			</div>
			<!--搜索end-->
			<!--列表-->
			<div data-options="region:'center',border:false">
				<div id="subLayout" class="easyui-layout"
					data-options="fit:true,border:false">
					<div data-options="region:'center',border:false">
						<table id="workflowList"></table>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>


