<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>自定义标签测试</title>
</head>
<body>
-----------------
	<@customerServiceDirective city="SHANGHAI" size=12 ;val>
		<#if val.qq??  && val.qq!="">
			${(val.qq)!''}
		<#else>
			<#if city=='SHANGHAI'>400-020-0668<#elseif city=='HONGHE'>400-626-6906<#else>400-020-0668</#if>				
		</#if>
	</@customerServiceDirective>
	
</body>