<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<script
	src="${basePath}/resources/js/quartz/quartzList.js?jsVersion=${jsVersion}"></script>

<div class="easyui-layout" data-options="fit:true">
	 
	<div data-options="region:'center',border:false">
		<div class="easyui-layout" data-options="fit:true">
			
			<form name="searchForm" id="searchForm" action="${basePath}/quartz/update.do" method="post">
				jobName：<input type="text" name="jobName"/><br/>
				jobGroup：<input type="text" name="jobGroup"/><br/>
				jobStatus：<input type="text" name="jobStatus"/><br/>
				cronExpression：<input type="text" name="cronExpression"/><br/>
				desc：<input type="text" name="desc"/>
				<input type="submit" value="submit">
			</form>
		</div>
	</div>
</div>


