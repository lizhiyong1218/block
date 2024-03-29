//按钮
   var buttons = {
        pass:{
        text:'通过',
    	id:'pass',
    	disabled:false,
    	iconCls:'icon-ok'
    },
    revert:{
    	text:'驳回',
    	id:'edit',
    	disabled:true,
    	iconCls:'icon-edit',
    	handler:function(){ 
    		revert(taskId);
    	}
    },
    transfer:{
    	text:'转处理人',
    	disabled:true,
    	id:'save',
    	//disabled:true,
    	iconCls:'icon-save',
    	handler:function(){
    		transfer(businessId,taskKey,taskId,pid);
    		workflow.close();
    	}
    },
    suspend:{
    	text:'暂停',
    	id:'delete',
    	disabled:true,
    	iconCls:'icon-undo',
    	handler:function(){
    		suspend(pid);
    		workflow.close();
    	}
    },
    active:{
    	text:'启动',
    	disabled:true,
    	id:'reject',
    	iconCls:'icon-remove',
        	handler:function(){
        		active(pid);
        		workflow.close();
        	}
        }
   };


var Workflow={
	create:function(business){
		   var workflow = {};
//		   var pid = $('#taskInfoForm input[name=processInstanceId]').val();
		   var taskId = $('#taskInfoForm input[name=taskId]').val();
	       var businessId = $('#taskInfoForm input[name=businessId]').val();
//	       var taskKey = $('#taskInfoForm input[name=taskKey]').val();
//	       var disabled = $('#suspended').val()=='true'?true:false;
	       
	       
	       workflow.buttons=buttons;
	       
	       var initButtons = [];//业务使用到的按钮
	       
	       //添加通过按钮
	 	   workflow.addPassButton=function(){
	 		  initButtons.push(buttons.pass);
	 		  return workflow;
	 	   };
	 	   
	 	   //添加驳回按钮
	 	   workflow.addRevertButton=function(){
	 		  initButtons.push(buttons.revert);
	 		  return workflow;
	 	   };
	 	  
	       //添加转流程的按钮
	       workflow.addTransferButton=function(){
	    	   initButtons.push(buttons.transfer);
	    	   return workflow;
	       };
	       
	       //添加暂停按钮
	       workflow.addSuspendButton=function(){
	    	   initButtons.push(buttons.suspend);
	    	   return workflow;
	       };
	       
	       //添加激活按钮
	       workflow.addActiveButton=function(){
	    	   initButtons.push(buttons.active);
	    	   return workflow;
	       };
	       
	       
	       //初始化
	       workflow.init = function(){ 
	    		
	    	    business.init(businessId,taskId);
	    	    //填充流程信息
	    	    fillProcessVariables(taskId);
	    		//初始化工具栏
//	    		$('#workflowButtonToolbar').toolbar({
//	    			items: initButtons
//	    		});
	       };
	       
	       //关闭窗口
	       workflow.close = function(){
	    	   activitiWorkflowDialog.dialog('destroy');
	       };
	       
	       //填充流程信息
	       function fillProcessVariables(taskId){
//	    	   alert("fillProcessVariables");
	       /*	$.ajax({
	       		type : "POST",
	       		async:false,
	       		url :  basePath+'/activiti/processInstanceDetail.do?taskId=' + taskId,
	       		success : function(processDetail) {
	       			var obj = eval('(' + processDetail +')');
	       			$('#activityId').val(obj.activityId);
	       			$('#userId').val(obj.userId);
	       			$('#startTime').val(obj.startTime.substring(0,10));
	       			
	       			var urlStr = '<a style="color:blue" href="#" id="processInstanceStateHref" processId=' + obj.id +' processDefId='+obj.processDefinitionId+'>'+obj.activityId+'</a>';
	       			$("#processInstanceState").html(urlStr);
	       			$('#processInstanceStateHref').click(function(){
	       				 graphTrace($(this));
	       	             return false;
	       			});
	       		}
	           });	*/             
	       }
	       
	       //通过
	       function pass(taskId){
	    	   business.complete(taskId,null);
	       };
	       
	       //驳回
	       function revert(taskId){
	    	   business.revert(taskId,complete);
	       };
	       
	       //完成审核（通过，驳回或者转处理人）
	       function complete(taskId, variables) {
	    		// 转换JSON为字符串
	    	    var responseCode=null;
	    	    var keys = "", values = "", types = "";
	    		if (variables) {
	    			$.each(variables, function() {
	    				if (keys != "") {
	    					keys += ",";
	    					values += ",";
	    					types += ",";
	    				}
	    				keys += this.key;
	    				values += this.value;
	    				types += this.type;
	    			});
	    		}
	    		
	    		$.ajax({
	    				type : "POST",
	    				async: false, 
	    				url : basePath + '/activiti/complete.do?id='+taskId,
	    				data:{
	    				     keys: keys,
	    		             values: values,
	    		             types: types
	    				},
	    				success : function(returnValue) {
	    					var obj = eval('(' + returnValue +')');
	    					$('#workflowList').datagrid('reload'); 
	    					responseCode = obj.responseCode;
	    				}
	    		});	
	    		return responseCode;
	    	};

	    	//转流程操作
	    	function transfer(businessId,taskKey,taskId,pid){
	    		     var responseCode=null;
	    			 $('#transferWorkflow').show();
	    	     	 $('#transferWorkflow').dialog({
	    	     		title: '转处理人',
	    	     		modal: false,
	    	     		width: 500,
	    	     		height: 350,
	    	     		modal: true,
	    	     		onOpen: function() {
	    	     			//加载流程信息
	    	     			$('#transferForm #activityId').combobox({
	    	     				url: basePath+'/activiti/process/trace.do?pid=' + pid,
	    	     				width:310,
	    	     				valueField:'id',
	    	     				textField:'taskName',
	    	     				panelHeight:'auto',
	    	     				method:'get'
	    	     			});
	    	     		},
	    	     		buttons:[{
	    					text: '确定',
	    					handler: function() {
	    						var activityId = $("#transferForm input[name='activityId']").val();
	    						// 转流程
	    						$.ajax({
	    		         			type : "GET",
	    		         			async: false, 
	    		         			url : basePath+'/activiti/task/transfer.do?taskId='+taskId+'&activityId='+activityId,
	    		         			success : function(returnValue) {
	    		         				var obj = eval('(' + returnValue +')');
	    		    					$('#workflowList').datagrid('reload'); 
	    		    					responseCode = obj.responseCode;
	    		         			}
	    		         		});	      
	    						$(this).parent().parent().dialog('close');
	    					}
	    				},{
	    					text: '取消',
	    					handler: function() {
	    						$(this).parent().parent().dialog('close');
	    					}
	    				}]
	    	     	});
	    	     	return responseCode;
	    	};
	    	
	    	//暂停一个流程
	    	function suspend(processInstanceId){
	    		$.ajax({
    				type : "GET",
    				async: false, 
    				url :  basePath+'/activiti/suspend.do?processInstanceId='+processInstanceId,
    				success : function(returnValue) {
    					var obj = eval('(' + returnValue +')');
    					$('#workflowList').datagrid('reload'); 
    					responseCode = obj.responseCode;
    				}
    		    });	     
	    	    return responseCode;
	    	}
	    	
	    	//激活一个流程
	    	function active(processInstanceId){
	    		$.ajax({
    				type : "GET",
    				async: false, 
    				url :  basePath+'/activiti/active.do?processInstanceId='+processInstanceId,
    				success : function(returnValue) {
    					var obj = eval('(' + returnValue +')');
    					$('#workflowList').datagrid('reload'); 
    					responseCode = obj.responseCode;
    				}
    		   });	       
	    	   return responseCode;
	    	}
	        return workflow;
	}	
};

/**
 * 打开开始页面
 * @param taskId  任务id
 * @param parameterMap 传过来的参数
 * @param width        宽度
 * @param height       高度
 */
/*function openWorkflowVerifyPage(taskId,width,height,includeBusiness,parameterMap){ 
	   $.ajax({
		type : "GET",
//		async:false,
		url : basePath + '/activitiTask/taskDetail.do?taskId='+taskId,
		success : function(taskDetail) {
			var path=basePath + "common/toListPage.do?pagePath=/activiti/layout/simpleTaskDetail";
	  	   	var mydialog= openMyDialog(path,'查看详情');
	  	   	mydialog.dialog({
	  		   width:1000,
	  		   height:600
	  		   }); 
			
			
//			var obj = eval('(' + taskDetail +')');
			var taskMessage = obj.responseMsg; 
			if(obj.responseCode=='NOT_EXIST'||obj.responseCode=='PERMISSION_DENIED'){
				$.messager.alert('提示',taskMessage);   
			}
			
			 if(includeBusiness==true){
				$('<div>').dialog({
			        width: width,
			        height: height,
			        title: '查看审核详情',
			        
			        href: basePath + "common/toListPage.do?pagePath=/activiti/layout/simpleTaskDetail",
			                    
			        			 "&taskId="+taskMessage.taskId+
			                     "&bussinessId="+taskMessage.bussinessId+
			                     "&taskKey="+taskMessage.taskKey+
			                     "&taskName="+taskMessage.taskName+
			                     "&processInstanceId="+taskMessage.processInstanceId,
			                     
			        scrollable:true,
			        
			        onOpen:function(){
			        	activitiWorkflowDialogResult = $(this);
			        },
			        
			        onClose:function(){
			        	activitiWorkflowDialogResult.dialog('destroy');
			        },
				
				    buttons:[{
				    	text:'取消',
				    	iconCls:'icon-cancel',
				    	handler:function(dialog){
				    		activitiWorkflowDialogResult.dialog('destroy');
				    	}
				    }]
			    });
			}else{
				
				var url = taskMessage.verifyUrl;
				if(parameterMap!=null){
					$.each(parameterMap,function(name,value) {
						url=url+'&'+name+'='+value;
					});
				}

			    $('<div>').dialog({
				    width: width,
				    height: height,
				    title: '流程办理['+taskDetail.taskName+']',
				    href: path+url,
				    scrollable:true,
				    
				    onOpen:function(){
				    	activitiWorkflowDialogResult = $(this);
				    },
				    
				    onClose:function(){
				    	activitiWorkflowDialogResult.dialog('destroy');
				    },
			
			    buttons:[{
			    	text:'取消',
			    	iconCls:'icon-cancel',
			    	handler:function(dialog){
			    		activitiWorkflowDialogResult.dialog('destroy');
			    	}
			    }]

				});

			}
			 
		}
    });	    
//	activitiWorkflowDialog = activitiWorkflowDialogResult;
};
*/
//var activitiWorkflowDialog;



//填充流程信息
function fillProcessVariables(taskId){
/*	$.ajax({
		type : "POST",
		async:false,
		url :  basePath+'/activiti/processInstanceDetail.do?taskId=' + taskId,
		success : function(processDetail) {
			var obj = eval('(' + processDetail +')');
			$('#activityId').val(obj.activityId);
			$('#userId').val(obj.userId);
			$('#startTime').val(obj.startTime.substring(0,10));
			
			var urlStr = '<a style="color:blue" href="#" id="processInstanceStateHref" processId=' + obj.id +' processDefId='+obj.processDefinitionId+'>'+obj.activityId+'</a>';
			$("#processInstanceState").html(urlStr);
			$('#processInstanceStateHref').click(function(){
				 graphTrace($(this));
	             return false;
			});
		}
    });	*/             
}

//通过
$("#pass").click(function(){
	var taskId = $('#taskInfoForm input[id=taskId]').val();
	// 设置流程变量
	var arr= [ {
		key : 'checkState',
		value : '1',
		type : 'S'
	}, {
		key : 'checkDesc',
		value : 'ok',
		type : 'S'
	}, {
		key : 'rmaNo',
		value : '123',
		type : 'S'
	} ];
	
	
	// 转换JSON为字符串
//    var responseCode="";
//    var keys = "", values = "", types = "";
//	if (arr) {
//		$.each(arr, function() {
//			if (keys != "") {
//				keys += ",";
//				values += ",";
//				types += ",";
//			}
//			keys += this.key;
//			values += this.value;
//			types += this.type;
//		});
//	}
	console.log(JSON.stringify(arr));
	var str=JSON.stringify(arr);
	$.ajax({
			type : "POST",
			async: false, 
			url : basePath + '/activitiTask/complete.do',
			data:{
				 taskId:taskId,
//			     keys: keys,
//	             values: values,
//	             types: types
				 arr:str
			},
			success : function(returnValue) {
				var obj = eval('(' + returnValue +')');
//				$('#workflowList').datagrid('reload'); 
				responseCode = obj.responseCode;
			}
	});	
	return responseCode;
});
//驳回
//function revert(taskId){
//	alert("revert"+taskId);  
//};

//完成审核（通过，驳回或者转处理人）
//function complete(taskId, variables) {
//		// 转换JSON为字符串
//	    var responseCode="";
//	    var keys = "", values = "", types = "";
//		if (variables) {
//			$.each(variables, function() {
//				if (keys != "") {
//					keys += ",";
//					values += ",";
//					types += ",";
//				}
//				keys += this.key;
//				values += this.value;
//				types += this.type;
//			});
//		}
//		
//		$.ajax({
//				type : "POST",
//				async: false, 
//				url : basePath + '/activiti/complete.do?id='+taskId,
//				data:{
//				     keys: keys,
//		             values: values,
//		             types: types
//				},
//				success : function(returnValue) {
//					var obj = eval('(' + returnValue +')');
////					$('#workflowList').datagrid('reload'); 
//					responseCode = obj.responseCode;
//				}
//		});	
//		return responseCode;
//	};
//
//	//转流程操作
//	function transfer(businessId,taskKey,taskId,pid){
//		     var responseCode="";
//			 $('#transferWorkflow').show();
//	     	 $('#transferWorkflow').dialog({
//	     		title: '转处理人',
//	     		modal: false,
//	     		width: 500,
//	     		height: 350,
//	     		modal: true,
//	     		onOpen: function() {
//	     			//加载流程信息
//	     			$('#transferForm #activityId').combobox({
//	     				url: basePath+'/activiti/process/trace.do?pid=' + pid,
//	     				width:310,
//	     				valueField:'id',
//	     				textField:'taskName',
//	     				panelHeight:'auto',
//	     				method:'get'
//	     			});
//	     		},
//	     		buttons:[{
//					text: '确定',
//					handler: function() {
//						var activityId = $("#transferForm input[name='activityId']").val();
//						// 转流程
//						$.ajax({
//		         			type : "GET",
//		         			async: false, 
//		         			url : basePath+'/activiti/task/transfer.do?taskId='+taskId+'&activityId='+activityId,
//		         			success : function(returnValue) {
//		         				var obj = eval('(' + returnValue +')');
//		    					$('#workflowList').datagrid('reload'); 
//		    					responseCode = obj.responseCode;
//		         			}
//		         		});	      
//						$(this).parent().parent().dialog('close');
//					}
//				},{
//					text: '取消',
//					handler: function() {
//						$(this).parent().parent().dialog('close');
//					}
//				}]
//	     	});
//	     	return responseCode;
//	};
//	
//	//暂停一个流程
//	function suspend(processInstanceId){
//		$.ajax({
//			type : "GET",
//			async: false, 
//			url :  basePath+'/activiti/suspend.do?processInstanceId='+processInstanceId,
//			success : function(returnValue) {
//				var obj = eval('(' + returnValue +')');
//				$('#workflowList').datagrid('reload'); 
//				responseCode = obj.responseCode;
//			}
//	    });	     
//	    return responseCode;
//	}
//	
//	//激活一个流程
//	function active(processInstanceId){
//		$.ajax({
//			type : "GET",
//			async: false, 
//			url :  basePath+'/activiti/active.do?processInstanceId='+processInstanceId,
//			success : function(returnValue) {
//				var obj = eval('(' + returnValue +')');
//				$('#workflowList').datagrid('reload'); 
//				responseCode = obj.responseCode;
//			}
//	   });	       
//	   return responseCode;
//	}
