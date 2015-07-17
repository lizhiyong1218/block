<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<script	src="${basePath}/resources/js/activiti/finishProcessList.js?jsVersion=${jsVersion}"></script>

<div class="easyui-layout" data-options="fit:true">
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
						<table id=finishProcessList></table>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
