<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center',border:false">
			
				<div class="easyui-panel" data-options="plain:true,fitWidth:true,cls:'mt20'" style="border:0" >
					<form id="dictionaryAddForm" method="post">
					
						<input type="hidden" name="dictionaryItemJsonData" id="dictionaryItemJsonData">
					
						<table id="rmaAddInfoTable" class="pertb" style="padding:20px 20px;" >
							<tbody>
								<tr>
									<th><em class="cred">*</em>数据字典:</th>
										<td >
											<input name="dictionaryValue" id="dictionaryValueId" class="easyui-validatebox" data-options="required:true" onblur="checkExist()"> 
										</td>
									<th><em class="cred">*</em>数据字典名称:</th>
									<td>
										<input name="dictionaryLabel"  class="easyui-validatebox"  data-options="required:true"  ></td>	
								</tr>
								
								<tr>
									<th><em class="cred">*</em>是否启用:</th>
									<td >
	                             		<select name="isavailable" id="isavailable" class="ipt" style="width:140px;">
											<option value="1">是</option>
											<option value="0">否</option>
										</select>
									</td>
									
									<th>备注:</th>
									<td>
										<input name="remarks" class="ipt"   >
									</td>
								</tr>
							</tbody>
						</table>
					</form>
				</div>
				
			 <div  class="easyui-panel" data-options="plain:true,fitWidth:true,cls:'mt20'" style="border:0">
				 <table id="dictionaryItemEditTable"   class="easyui-datagrid"   
						data-options="toolbar:dictionaryItemEditTableToolbar,pagination:false,rownumbers:true,singleSelect:true,checkOnSelect: true, width :'auto',  
		 				height:'auto',fitColumns : true,	striped:true,fit:false">
					<thead>
						<tr>
							<th  data-options="field:'ck',checkbox:'true'"></th>
							<th data-options="field:'itemValue',width:80,editor: {type: 'validatebox', options: { required: true,missingMessage: '数据字典项不能为空'}}">数据字典项</th>
							<th data-options="field:'itemLabel',width:80,editor: {type: 'validatebox', options: { required: true,missingMessage: '名称不能为空'}}">数据字典项名称</th>
							<th data-options="field:'orderNo',width:80,editor: {type: 'text'}">排序号</th>
							<th data-options="field:'remarks',width:100,editor: {type: 'text'}">备注</th>
						</tr>
					</thead>
				</table>  
			</div>
		</div>
	</div> 
	 
