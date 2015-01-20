	$(function() {
		initWorkFlowList();
	});

	function initWorkFlowList() {
		$('#workflowList').datagrid({
			 url: basePath+'activiti/process/list.do',
			 width :'auto',  
			 height:'auto',
			 fitColumns : true,
			 nowrap:false,
			 striped: true,//隔行变色
			 collapsible:true,
			 loadMsg:'数据装载中......',
			 singleSelect:true,
			 rownumbers:true,
			 checkOnSelect: true,
			 pagination : true,
			 frozenColumns: [
	            [
	                {
	                    title:'id',
	                    field:'id',
	                    checkbox: true
	                }
	            ]
	        ],
			 columns: [
			     [
					{
					    title: '流程名称',
					    field: 'deploymentId',
					    width: 100,
					    hidden:true
					},
			        {
	                    title: '流程名称',
	                    field: 'name',
	                    width: 100
	                },
	                {
	                    title: '类别',
	                    field: 'category',
	                    width: 100
	                },
	                { 
	                    title: '版本',
	                    field: 'version',
	                    width: 100
	                },{
	                    title: '图片',
	                    field: 'diagramResourceName',
	                    width: 200,
	                    minWidth: 100,
	                    formatter: function (value, rec, index) {
	                    	var str = '<a style="color:blue" data-id=' + rec.id + ' href="javascript:;" class="photoContent">'+rec.diagramResourceName+'</a>';
	                        return str;
	                    }
	                },
	                {
	                    title: 'XML文件',
	                    field: 'opt',
	                    width: 100,
	                    formatter: function (value, rec, index) {
	                    	/*var processId = rec.id;
	                        var xmlUrl = basePath+'/workflow/resource/read.do?processDefinitionId='+processId+'&resourceType=xml';
	                        var str = '<a title="流程XML详情" target="_blank" href="'+xmlUrl+'">流程XML详情</a>';*/
	                    	var str=showXmlFile(value, rec, index);
	                        return str;
	                    }
	                },
	                {
	                    title: '配置审核人和分组',
	                    field: 'assignTask',
	                    width: 100,
	                    formatter: function (value, rec, index) {
	                        var str = '<a style="color:blue" data-id=' + rec.id + ' href="javascript:;" class="taskAssigneeConfig">'+"配置审核人和分组"+'</a>';
	                        return str;
	                    }
	                },
	                {
	                    title: '配置邮件模板',
	                    field: 'mailConfig',
	                    width: 100,
	                    formatter: function (value, rec, index) {
	                        var str = '<a style="color:blue" data-id=' + rec.id + ' href="javascript:;" class="mailConfig">'+"配置邮件模板"+'</a>';
	                        return str;
	                    }
	                }
			     ]
			 ],
			 
			 onLoadSuccess: function () {
	        	//图片
	        	$('.photoContent').click(function() {
		            drawWorkflowGraph($(this).attr('data-id'));
		            return false;
		        });
	        	 
	            //配置任务审核人和分组
	            $('.taskAssigneeConfig').click(function() {
	                var processId = $(this).attr('data-id');
//	                var url = basePath+'/activiti/redirect.do?redirect=task/taskAssigneeConfig&processDefinitionId='+processId;
	                //打开新窗口
	                /*ygDialog({
                        width: 1000,
                        height: 620,
                        title: '配置任务审核人和分组',
                        scrollable:true,
                        href: url,
                   });*/
	                var path = basePath + 'activiti/taskAssigneeConfigPage.do?processDefinitionId='+processId;
	        		var mydialog = openMyDialog(path, '配置任务审核人和分组');
	        		mydialog.dialog({
	        			width : 1000,
	        			height : 620,
	        			buttons : [ {
	        				text : '提交',
	        				iconCls : 'icon-ok',
	        				handler : function() {
	        					ajaxFileUpload(mydialog);
	        				}
	        			} ]
	        		});
	                
	                
	                
	            });
	            
	            //配置邮件模板
	            $('.mailConfig').click(function() {
	               /* var processId = $(this).attr('data-id');
	                var url = basePath+'/activiti/redirect.do?redirect=task/mailConfig&processDefinitionId='+processId+'&sessionId='+sessionId;
	                //打开新窗口
	                ygDialog({
                        width: 1000,
                        height: 620,
                        title: '配置邮件模板',
                        scrollable:true,
                        href: url,
                   });*/
	            	
	            	selectPeople();
	            	
	            });
	            
	        }
		 
		});
		initPager("#workflowList");
	}
	
	//重置
	function doReset(){
		$('#searchForm').form('reset');
	}
	
	/*查询*/
	function doSearch(){
 		$('#workflowList').datagrid('load',$('#searchForm').serializeJson());
	}
	
	
	/**
	 * 流程图展示
	 */
	function drawWorkflowGraph(processId){
		 var imageUrl = basePath+'/activiti/resource/read.do?processDefinitionId='+processId+'&resourceType=image';
         if ($('#workflowTraceDialog').length == 0) {
         	$('<div/>', {
	                    id: 'workflowTraceDialog',
	                    title: '查看流程',
	                    html: "<div><img src='" + imageUrl + "' style='position:absolute; left:0px; top:0px; padding-top:50px; padding-left:50px;' />" +
	                    "</div>"
	            }).appendTo('body');
         } else {
             $('#workflowTraceDialog img').attr('src', imageUrl);
         }
         
         // 打开对话框
         $('#workflowTraceDialog').dialog({
             modal: true,  
             resizable: false,
             dragable: false,
             open: function() {
                 $('#workflowTraceDialog').dialog('option', 'title', '查看流程');
                 $('#workflowTraceDialog').css('padding', '0.2em');
                 $('#workflowTraceDialog .ui-accordion-content').css('padding', '0.2em').height($('#workflowTraceDialog').height() - 75);
             },
             close: function() {
                 $('#workflowTraceDialog').remove();
             },
             width: 900,
             height: 500
         });
	}
	
	/**
	 * 显示流程xml文件
	 * @param value
	 * @param rec
	 * @param index
	 * @returns {String}
	 */
	function showXmlFile(value, rec, index){
		var processId = rec.id;
        var xmlUrl = basePath+'activiti/resource/read.do?processDefinitionId='+processId+'&resourceType=xml';
        var str = '<a title="流程XML详情" target="_blank" href="'+xmlUrl+'">流程XML详情</a>';
        return str;
	}
	
	
	/**
	 * 部署流程
	 */
	function openAddProcessDialog() {
		var path = basePath + 'common/toListPage.do?pagePath=activiti/deploy';
		var mydialog = openMyDialog(path, '流程部署');
		mydialog.dialog({
			width : 600,
			height : 400,
			buttons : [ {
				text : '提交',
				iconCls : 'icon-ok',
				handler : function() {
					ajaxFileUpload(mydialog);
				}
			} ]
		}); 
	}
	
	/**
	 * 执行上传
	 * @param tempId
	 * @param customParas
	 */
	function ajaxFileUpload(mydialog){  
//		$("#myExcelDialog").mask("Waiting...");//loadmask插件
		$.ajaxFileUpload(  
	        {  
			     url:basePath + 'activiti/process/deploy.do',            //需要链接到服务器地址
			     type:"post",//请求方法
			     secureuri:false,  
			     fileElementId:'houseMaps',                        //文件选择框的id属性  
			     dataType: 'JSON',                            //返回值类型 一般设置为json,一定要大写,否则可能会出现一些bug
			     success: function (result, status)            
			     {
//					var dataObj = eval("(" + result + ")");// 转换为json对象
					var dataObj = jQuery.parseJSON(jQuery(result).text());;// 转换为json对象
					if (dataObj.status == 102) {
						showInfo('操作失败！原因：' + dataObj.message);
					} else if(dataObj.status == 101){
						showInfo('操作成功！');
						$('#workflowList').datagrid("reload");
						closeMyDialog(mydialog);  
					}else{
						showInfo('异常！');
					}
				}  
			 }  
	    );  
//		$("#myExcelDialog").unmask();
	} 
	
	/**
	 * 删除工作流
	 */
	function delProcess(){
		var row = $('#workflowList').datagrid('getSelected');
		if(row){
			$.messager.confirm('请选择','确定要删除该流程吗?',function(r){ 
				if (r){
					doDelProcess(row.deploymentId);
				} 
			});
		}else{
			showWarm('请选择要删除的流程');
		}
	}
	
	/**
	 * 执行删除工作流
	 * @param deploymentId
	 */
	function doDelProcess(deploymentId){
		$.ajax({
		     type:"POST",
			 url:basePath+'workflow//process/delete.do',
			 data:{
				 deploymentId:deploymentId
			},
			 success : function(dataObj) {
					if (dataObj.status == 102) {
						showInfo('操作失败！原因：' + dataObj.message);
					} else if (dataObj.status == 101) {
						showInfo('操作成功！');
						$('#workflowList').datagrid("reload");
					} else {
						showInfo('异常！');
					}
				}  
		 });
	}
	
	function selectPeople(){
		   $('<div/>').dialog({
		        href: basePath+"/selectPeople.jsp",
		        width: 500,
		        height: 400,
		        modal: true,
				collapsible : true,
		        title: "选择框",
				buttons : [
	            	{
					text : '关闭',
					handler : function() {
						 var d = $(this).closest('.window-body');
			             d.dialog('destroy');
					}
				}],
		        onClose: function () {
		            $(this).dialog('destroy');
		        }
		    });
	}
	 
	
	
	/*
	 * function sychorItInfo(){ $.ajax({ type : "POST", async:false, url :
	 * basePath+'/ororganization/sychorToIt.do', success :
	 * function(processDetail) { var obj = eval('(' + msg +')');
	 * showSuc(obj.responseMsg); } }); }
	 */
	
	
	
	/*---------------------------------------------------以下为关联界面JS---------------------------------------------------------------------*/
	/*获取批量选中时ID列表*/
	/*function getBatchSelectedIds(tb) {
	    var node = $(tb).datagrid('getChecked');
	    var ids = [];
	    $.each(node, function (index, val) {
	    	ids.push(val.asnId);
	    });
	    return ids;
	}*/
