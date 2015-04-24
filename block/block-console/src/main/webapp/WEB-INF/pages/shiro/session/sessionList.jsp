<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<script	src="${basePath}/resources/js/shiro/session/sessionList.js?jsVersion=${jsVersion}"></script>
<div class="easyui-layout" data-options="fit:true">
	<!--列表-->
	<div data-options="region:'center',border:false">
		<div id="subLayout" class="easyui-layout"
			data-options="fit:true,border:false">
			<div data-options="region:'center',border:false">
				<table id="sessionList"></table>
			</div>
		</div>
	</div>
</div>