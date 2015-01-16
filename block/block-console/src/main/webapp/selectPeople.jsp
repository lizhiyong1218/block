<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>

 
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>dialog</title>
	<link rel="stylesheet" type="text/css" href="<%=basePath %>resources/jquery-easyui-1.3.4/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>resources/jquery-easyui-1.3.4/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>resources/jquery-easyui-1.3.4/demo.css">
	<script type="text/javascript" src="<%=basePath %>resources/js/jquery-1.9.0.min.js"></script> 
	<script type="text/javascript" src="<%=basePath %>resources/jquery-easyui-1.3.4/jquery.easyui.min.js"></script>  
	<script type="text/javascript" src="<%=basePath %>/resources/js/excel/excelop.js"></script>

	<div  class="easyui-layout" data-options="fit:true" >   
		<div data-options="region:'east', split:true" style="width:200px;">
			 <p>已选区</p>
		     <select id="selectR" name="selectR" multiple="multiple">
		     </select>
		</div>   
	    <div data-options="region:'west', split:true" style="width:200px;">
	    	 <p>待选区</p>
		     <select id="selectL" name="selectL" multiple="multiple">
		         <option value="13800138000">王新安 - 13800138000</option>
		         <option value="13800138001">李密 - 13800138001</option>
		         <option value="13800138002">姜瑜 - 13800138002</option>
		         <option value="13800138002">钱书记 - 13800138004</option>
		     </select>
	    </div>   
	    <div data-options="region:'center'" style="padding:5px;background:#eee;">
	    	<p id="toright" title="添加">&gt;</p>
        	<p id="toleft" title="移除">&lt;</p>
	    </div> 
	</div>
	
	
<script type="text/javascript">
$(function(){
    var leftSel = $("#selectL");
	var rightSel = $("#selectR");
	$("#toright").bind("click",function(){		
		leftSel.find("option:selected").each(function(){
			$(this).remove().appendTo(rightSel);
		});
	});
	$("#toleft").bind("click",function(){		
		rightSel.find("option:selected").each(function(){
			$(this).remove().appendTo(leftSel);
		});
	});
	leftSel.dblclick(function(){
		$(this).find("option:selected").each(function(){
			$(this).remove().appendTo(rightSel);
		});
	});
	rightSel.dblclick(function(){
		$(this).find("option:selected").each(function(){
			$(this).remove().appendTo(leftSel);
		});
	});
	$("#sub").click(function(){
		var selVal = [];
		rightSel.find("option").each(function(){
			selVal.push(this.value);
		});
		selVals = selVal.join(",");
		//selVals = rightSel.val();
		if(selVals==""){
			alert("没有选择任何项！");
		}else{
			alert(selVals);
		}
	});
});
</script> 



<!-- 首先，绑定向右的方向建按钮的click事件，当单击按钮时，左侧列表框选中的项会添加到右侧列表框中，完成添加的操作。

var leftSel = $("#selectL"); 
var rightSel = $("#selectR"); 
$("#toright").bind("click",function(){         
    leftSel.find("option:selected").each(function(){ 
        $(this).remove().appendTo(rightSel); 
    }); 
}); 

同样，绑定向左的方向建按钮的click事件，当单击按钮时，右侧列表框选中的项会添加到左侧列表框中，完成移除的操作。

$("#toleft").bind("click",function(){         
    rightSel.find("option:selected").each(function(){ 
        $(this).remove().appendTo(leftSel); 
    }); 
}); 

接下来，需要完成双击选择事件，当双击该项时，该项立即从该列表框中移除，并添加到与之相对的列表框中。

leftSel.dblclick(function(){ 
    $(this).find("option:selected").each(function(){ 
        $(this).remove().appendTo(rightSel); 
    }); 
}); 
rightSel.dblclick(function(){ 
    $(this).find("option:selected").each(function(){ 
        $(this).remove().appendTo(leftSel); 
    }); 
}); 

以上代码是有点多，但是非常直观，而且非常容易理解，有了这些操作后，就能对列表框的值进行随心所欲的控制了。

我们知道，后台程序要获取选择框的值，只有当选择框选中了项，后台才能获取提交的选项的值，但是问题就出在我们这个列表框有移除的操作，当用户执行了移除操作时，右侧列表框中本来已选中的项全部取消选中状态，这时再去提交，后台是获取不到提交的选项值的。那么我们应该处理的是，不管右侧的项是否选中，我们都认为右侧列表框为选中的项，事实也是如此，这样我们只需将右侧选择框中的项组成一个字符串或数组提交给后台就OK。看代码：

$("#sub").click(function(){ 
    var selVal = []; 
    rightSel.find("option").each(function(){ 
        selVal.push(this.value); 
    }); 
    selVals = selVal.join(","); 
    //selVals = rightSel.val(); 
    if(selVals==""){ 
        alert("没有选择任何项！"); 
    }else{ 
        alert(selVals); 
    } 
}); 

最终得到的结果是一串以“,”号隔开的字符串。 -->