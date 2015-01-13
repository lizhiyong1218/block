<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<script src="${basePath}/resources/js/common/ajaxfileupload.js"></script>

<div class="easyui-layout" data-options="fit:true" id="ddd">
	<div data-options="region:'center',border:false">
		<form id="partNumberAddForm" method="post" action="${basePath}/activiti/deploy.do"
			enctype="multipart/form-data">
			<div class="pd10">
				<table class="form-tb" style="width: 98%">
					<col width="120" />
					<tbody>
						<tr>
							<th style="width: 200px;"><em class="cred">*</em></th>
							<td>部署新流程：支持文件格式：zip、bar、bpmn、bpmn20.xml</td>
						</tr>
						<tr>
						    <th></th>
							<td>
							 	<input type="file" id="houseMaps" name="houseMaps"/>
				            </td>
						</tr>
					</tbody>
				</table>
			</div>
		</form>
	</div>
</div>