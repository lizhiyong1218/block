<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
	
	<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
	%>
	
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center',border:false">
				<div class="easyui-panel" data-options="title:'基本信息',plain:true,fitWidth:true,cls:'mt20'" >
					<form id="dictionaryEditForm" method="post">
					<input type="hidden" name="dictionaryId" value="${dictionaryModel.dictionaryId }">
						<table id="dictionaryEditInfoTable" class="pertb" style="padding:20px 20px;" >
							<tbody>
								<tr>
									<th><em class="cred">*</em>数据字典项:</th>
										<td >
											<input name="dictionaryValue" id="dictionaryValueId"  value="${dictionaryModel.dictionaryValue}" disabled="disabled" class="easyui-validatebox"   data-options="required:true" > 
										</td>
									<th><em class="cred">*</em>数据字典名称:</th>
									<td>
										<input name="dictionaryLabel"  class="easyui-validatebox"  data-options="required:true"   value="${ dictionaryModel.dictionaryLabel}"></td>	
								</tr>
								
								<tr>
									<th><em class="cred">*</em>是否启用:</th>
									<td>
									<%-- 
										<input name="isavailable"   id="isavailable"   value="${ dictionaryModel.isavailable}"/>
									 --%>
									 	<select name="isavailable" id="isavailable" value="${ dictionaryModel.isavailable}">
											<option value="1">是</option>
											<option value="0">否</option>
										</select>
									 	
									</td>
									
									<th>备注:</th>
									<td>
										<input name="remarks" class="ipt"    value="${ dictionaryModel.remarks}">
									</td>
								</tr>
							</tbody>
						</table>
					</form>
				</div>
		</div>
	</div> 
	 
	<script src="<%=basePath %>resources/js/dictionary/dictionaryEdit.js?jsVersion=${jsVersion}"></script> 
