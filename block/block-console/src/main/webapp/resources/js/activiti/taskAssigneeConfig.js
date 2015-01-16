
$(document).ready(function() {
	var processDefinitionId = $('#processDefinitionId').val();
	alert(processDefinitionId);
	
	//初始化组织结构
	/* */
	$('#organizationTree').tree({
//		url: basePath+'/ororganization/data.do?typeKey=type&typeValue=0',
		url: basePath+'peopel.json',
		valueField:'vendorId',
		textField:'vendorName',
		dnd:true,
		onDragEnter:function(target,source){
			return false;
		} 
	});
	 
	
	//初始化面板
	$.ajax({
		type : "POST",
		async: false, 
		url : basePath+'/workflow/process/traceTaskDefinations.do?processDefinitionId=' + processDefinitionId,
		success : function(returnValue) {
			debugger;
			console.log(returnValue);
//			var res = eval('(' + returnValue +')');
//			console.log(res);
//			var obj = eval('(' + returnValue +')');
			var obj = returnValue.taskDefinations;
			console.log(obj);
			$.each(obj, function(i, taskInfo) {
				  var task = $('#assignExample').clone(true);
				  task.attr('id',taskInfo.taskKey);
				  task.attr('title',taskInfo.taskName);
				  task.appendTo($('#taskAssignAccordion'));
				  
				  var assignees = taskInfo.assignee;
				  if(assignees!=null){
					  $('#'+task.attr('id')+' .taskAssignee').append("<li class='drag-item' "+"id='' name="+assignees+ ">"+assignees+"</li>");
				  }
				  
				  var cadidateGroups = taskInfo.cadidateGroups;
				  $.each(cadidateGroups, function(i, cadidateGroup) {
					  $('#'+task.attr('id')+' .taskCandidateGroup').append("<li class='drag-item' "+"id='' name="+cadidateGroup+ ">"+cadidateGroup+"</li>");
				  });
				  
				  var cadidateUsers = taskInfo.cadidateUsers;
				  $.each(cadidateUsers, function(i, cadidateUser) {
					  $('#'+task.attr('id')+' .taskCandidateUser').append("<li class='drag-item' "+"id='' name="+cadidateUser+ ">"+cadidateUser+"</li>");
				  });
				  
				  //将数据库中已经分配的人员和组织结构填充到页面
				  $.ajax({
						type : "POST",
						async: false, 
						url : basePath+'/task/taskAssignDetail.do?defKey=' + processDefinitionId+"&taskKey="+taskInfo.taskKey,
						success : function(returnValue) {
							var obj = eval('(' + returnValue + ')');
							$.each(obj, function (index, val) {
								var taskKey = task.attr('id');
								var assignee = "<li class=drag-item"+" orgId="+val.orgId+" id="+taskKey+val.orgId+ " name="+val.orgName+  "><div style=color:blue;>"+val.orgName+"</div></li>";
								if(val.type=='1'){
									 $('#'+taskKey+' .taskAssignee').append(assignee);
								}else if(val.type=='2'){
									 $('#'+taskKey+' .taskCandidateUser').append(assignee);
								}else if(val.type=='3'){
									 $('#'+taskKey+' .taskCandidateGroup').append(assignee);
								}
								$('#'+taskKey+val.orgId).bind('dblclick',function(){
								    	$(this).remove();
								});
						    });
						}
				  });
				  
				  //为保存按钮注册点击事件
				  var link = $('#'+taskInfo.taskKey+' .easyui-linkbutton');
				  link.attr('id',taskInfo.taskKey+'SaveOrganizationBtn');
				  link.bind('click', function(){    
						var task = $('#taskAssignAccordion').accordion('getSelected'); 
						var taskAssignee = $('#'+task.attr('id')+' .taskAssignee');
						var taskCandidateUser =$('#'+task.attr('id')+' .taskCandidateUser'); 
						var taskCandidateGroup =$('#'+task.attr('id')+' .taskCandidateGroup');
						
						var taskAssigneeUsers = [];
						taskAssignee.children().each(function(i,user){
							var id = $(user).attr('orgId');
							var name = $(user).attr('name');
							if(id!=null&&id!=""){
								var variable = {id:id,name:name};
								taskAssigneeUsers.push(variable);
							}
						});
						
				        var taskCandidateUsers =[];
				        taskCandidateUser.children().each(function(i,user){
							var id = $(user).attr('orgId');
							var name = $(user).attr('name');
							if(id!=null&&id!=""){
								var variable = {id:id,name:name};
								taskCandidateUsers.push(variable);
							}
						});
				        
				        var taskCandidateGroups =[];
				        taskCandidateGroup.children().each(function(i,user){
							var id = $(user).attr('orgId');
							var name = $(user).attr('name');
							if(id!=null&&id!=""){
								var variable = {id:id,name:name};
								taskCandidateGroups.push(variable);
							}
						});
						
				        var result = {task:$('#'+task.attr('id')),
				        		      taskAssigneeUsers:taskAssigneeUsers,
				        		      taskCandidateUsers:taskCandidateUsers,
				        		      taskCandidateGroups:taskCandidateGroups};

				       $.ajax({
							type : "POST",
							async: false, 
							url : basePath + '/task/taskAssign.do',
							data:{
								processDefinitionId:processDefinitionId,
								task:task.attr('id'),
				  		        taskAssigneeUsers:taskAssigneeUsers,
				  		        taskCandidateUsers:taskCandidateUsers,
				  		        taskCandidateGroups:taskCandidateGroups
							},
							success : function(returnValue) {
								var obj = eval('(' + returnValue +')');
								showSuc(obj.responseMsg);
							}
					    });
						var str= JSON.stringify(result);
				    });    
			});
			$('#assignExample').remove();
		}
    });
	 
	//点击选择选择任务分配人
	$('#selectAssigneeBtn').bind('click', function(){    
		var node = $("#organizationTree").tree("getSelected");
		if(node!=null){
			if($('#organizationTree').tree('isLeaf',node.target)&&node.type==1){
				var task = $('#taskAssignAccordion').accordion('getSelected'); 
				var taskId=task.attr('id');
				 var exist = false;
				 $('#'+task.attr('id')+' .taskAssignee').children().each(function(i,user){
						var id = $(user).attr('orgId');
						if(id==node.id){
							exist = true;
						}
				 });
				   
				 if(!exist){
				    $('#'+task.attr('id')+' .taskAssignee').append("<li class=drag-item"+" orgId="+node.id+" id="+taskId+node.id+  " name="+node.text+  "><div style=color:blue;>"+node.text+"</div></li>");
				    $('#'+taskId+node.id).bind('dblclick',function(){
				    	$(this).remove();
				    });
				 }
			}
		}
    });    
	
	//点击任务候选人
	$('#selectUserCandidateBtn').bind('click', function(){    
		var node = $("#organizationTree").tree("getSelected");
		if(node!=null){
			if($('#organizationTree').tree('isLeaf',node.target)&&node.type==1){
			   var task = $('#taskAssignAccordion').accordion('getSelected'); 
			   var taskId=task.attr('id');
			   var exist = false;
			   $('#'+task.attr('id')+' .taskCandidateUser').children().each(function(i,user){
					var id = $(user).attr('orgId');
					if(id==node.id){
						exist = true;
					}
			   });
			   
			   if(!exist){
				   $('#'+task.attr('id')+' .taskCandidateUser').append("<li class=drag-item"+" orgId="+node.id+" id="+taskId+node.id+ " name="+node.text+  "><div style=color:blue;>"+node.text+"</div></li>");
				   $('#'+taskId+node.id).bind('dblclick',function(){
				     	$(this).remove();
				   });
			   }
			}
		}
    });  
	
	//点击任务候选分组
	$('#selectOrganizationBtn').bind('click', function(){    
		var node = $("#organizationTree").tree("getSelected");
		if(node!=null){
			if(!$('#organizationTree').tree('isLeaf',node.target)&&node.level==2){
			   var task = $('#taskAssignAccordion').accordion('getSelected'); 
			   var taskId=task.attr('id');
			   var exist = false;
			   $('#'+task.attr('id')+' .taskCandidateGroup').children().each(function(i,user){
					var id = $(user).attr('orgId');
					if(id==node.id){
						exist = true;
					}
			   });
			   
			   if(!exist){
			       $('#'+task.attr('id')+' .taskCandidateGroup').append("<li class=drag-item"+" orgId="+node.id+" id="+taskId+node.id+ " name="+node.text+  "><div style=color:blue;>"+node.text+"</li>");
			       $('#'+taskId+node.id).bind('dblclick',function(){
			  	  	   $(this).remove();
			       });
			   }
			}
		}
    });    
});