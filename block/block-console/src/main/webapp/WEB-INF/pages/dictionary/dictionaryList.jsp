<%@ page language="java" contentType="text/html; charset=UTF-8"%>


<script
	src="${basePath}/resources/js/dictionary/dictionaryList.js?jsVersion=${jsVersion}"></script>
<script
	src="${basePath}/resources/js/dictionary/dictionaryIndex.js?jsVersion=${jsVersion}"></script>

<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'north',border:false" class="toolbar-region" style=" background-color: #f4f4f4;">
		<table class="toolbar-wrapper toolbar-align-left " >
		    	<tbody>
		    		<tr>
		    			<td class="toolbar-item-container">
		    				<a  class="toolbar-item l-btn   l-btn-plain" onclick="openAddDictionaryDialog()"   href="javascript:void(0)" >
		    					<span class="l-btn-left l-btn-icon-left">
									<span class="l-btn-icon-left icon-add"> </span>
									<span class="l-btn-text">新增</span>
								</span>
		    				</a>
		    			</td>
		    			<td class="toolbar-item-container">
		    				<a class="toolbar-item dialog-tool-separator"></a>
		    			</td>
		    			
		    			 <td class="toolbar-item-container">
		    				<a  class="toolbar-item l-btn   l-btn-plain" onclick="delDictionary()"   href="javascript:void(0)" >
		    					<span class="l-btn-left l-btn-icon-left">
									<span class="l-btn-icon-left icon-del"> </span>
									<span class="l-btn-text">删除</span>
								</span>
		    				</a>
		    			</td>
		    			<td class="toolbar-item-container">
		    				<a class="toolbar-item dialog-tool-separator"></a>
		    			</td>
		    			
		    			<td class="toolbar-item-container">
		    				<a  class="toolbar-item l-btn   l-btn-plain" onclick="openEdtDictionaryDialog()"   href="javascript:void(0)" >
		    					<span class="l-btn-left l-btn-icon-left">
									<span class="l-btn-icon-left icon-edit"> </span>
									<span class="l-btn-text">修改</span>
								</span>
		    				</a>
		    			</td>
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
							<col width="100" />
							<col />
		
							<tbody>
								<tr>
									<th>数据字典值：</th>
									<td><input class="ipt" name="dictionaryValue"
										id="dictionaryValue" /></td>
									<th>数据字典名称：</th>
									<td><input class="ipt" name="dictionaryLabel"
										id="dictionaryLabel" /></td>
		
		
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
						<table id="dictionaryList"></table>
					</div>
		
					<div
						data-options="region:'south',border:false,minSplit:true,height:230">
						<div id="dictionaryItemTabs" class="easyui-tabs"
							data-options="fit:true">
							<div  title="数据项" style="padding: 0px">
								<table id="dictionaryItemList"></table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>


