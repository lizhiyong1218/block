<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
	
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
	
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center',border:false">
				<!-- <div class="easyui-panel" data-options="title:'基本信息',plain:true,fitWidth:true,cls:'mt20'" > -->
					<form id="itemEditForm" method="post">
					<input type="hidden"  name="refDictionaryValue"  value="${dictionaryValue}"> <!-- 新增数据字典项时关联数据字典值,name不能为dictionaryValue，否则修改数据字典项会为空 -->
					<input type="hidden" name="itemId" value="${dictionaryItemModel.itemId }"> <!-- 修改数据字典的id  -->
						<table id="itemEditInfoTable" class="pertb" style="padding:20px 20px;" >
							<tbody>
								<tr>
									<th><em class="cred">*</em>数据字典项:</th>
										<td >
											<input name="itemValue" id="itemValue"  value="${dictionaryItemModel.itemValue}"   class="easyui-validatebox"   data-options="required:true" > 
										</td>
									<th><em class="cred">*</em>数据字典项名称:</th>
									<td>
										<input name="itemLabel"  class="easyui-validatebox"  data-options="required:true"   value="${ dictionaryItemModel.itemLabel}"></td>	
								</tr>
								
								<tr>
									<th><em class="cred">*</em>是否启用:</th>
									<td>
									<%-- 
										<input name="isavailable"   id="isavailable"   value="${ dictionaryItemModel.isavailable}"/>
										 --%>
										 
										 <select name="isavailable" id="isavailable" value="${ dictionaryItemModel.isavailable}">
											<option value="1">是</option>
											<option value="0">否</option>
										</select>
										 
									</td>
									
									<th>备注:</th>
									<td>
										<input name="remarks" class="ipt"    value="${ dictionaryItemModel.remarks}">
									</td>
								</tr>
								
									<tr>
									<th>排序号</th>
									<td>
										<input name="orderNo"   id="orderNo"   value="${ dictionaryItemModel.orderNo}"/>
									</td>
									
									 
								</tr>
								
							</tbody>
						</table>
					</form>
				<!-- </div> -->
		</div>
	</div> 
	<%--  
	<script src="<%=basePath %>resources/js/dictionary/DictionaryItemEdit.js?jsVersion=${jsVersion}"></script> 
 --%>