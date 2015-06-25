	/*查询*/
		function doSearch(){
			$('#searchForm').datagrid('load',$('#searchForm').serializeJson());
		}

		$(document).ready(function() {
			initList();
		});

		function initList(){
		    $('#taskList').datagrid({
		    	url: basePath+'/task/taskList.do',
		    	
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
//		        frozenColumns: [
//		            [
//		                {
//		                    title:'priceId',
//		                    field:'priceId',
//		                    checkbox: true
//		                }
//		            ]
//		        ],
		        columns: [
		            [
		             	{
		                    title: '任务名',
		                    field: 'jobName',
		                    width: 60
		                },
		                {
		                    title: '任务组',
		                    field: 'jobGroup',
		                    width: 100
		                },
		                {
		                    title: '时间表达式',
		                    field: 'cronExpression',
		                    width: 60
		                },
		                {
		                    title: '状态',
		                    field: 'jobStatus',
		                    width: 60
		                },
		                {
		                    title: '备注',
		                    field: 'desc',
		                    width: 60 
		                },
		                {
		                    title: '操作',
		                    field: 'phasePrice',
		                    width: 60,
		                    formatter:opFormatter
		                } 
		            ]
		        ]

		    });
		}

		function opFormatter(){
			return "<a style='cursor:pointer' onClick='pause()'>暂停</a> <a style='cursor:pointer' onClick='resume()'>恢复</a>  <a style='cursor:pointer' onClick='deleteJob()'>删除</a> <a style='cursor:pointer' >修改表达式</a> <a style='cursor:pointer' onClick='execute()'>立即运行</a>";
		}

		function pause(){
			 var node = $("#taskList").datagrid('getSelected');
			 $.ajax({
			     type:"POST",
				 url:basePath+'/task/pause.do',
				 data:{
					 jobName:node.jobName,
					 jobGroup:node.jobGroup
					 },
				 success:function(result){
				        if (result.responseCode == 101){
			                showInfo('更新失败！原因：'+result.responseMsg);
			            } else {
			            	$('#taskList').datagrid("reload"); 
			            	showInfo('更新成功！'); 
			            }
			       }
			});
		}

		function resume(){
			 var node = $("#taskList").datagrid('getSelected');
			 $.ajax({
			     type:"POST",
				 url:basePath+'/task/resume.do',
				 data:{
					 jobName:node.jobName,
					 jobGroup:node.jobGroup
					 },
				 success:function(result){
				        if (result.responseCode == 101){
			                showInfo('更新失败！原因：'+result.responseMsg);
			            } else {
			            	$('#taskList').datagrid("reload"); 
			            	showInfo('更新成功！'); 
			            }
			       }
			});
		}

		function execute(){
			 var node = $("#taskList").datagrid('getSelected');
			 $.ajax({
			     type:"POST",
				 url:basePath+'/task/execute.do',
				 data:{
					 jobName:node.jobName,
					 jobGroup:node.jobGroup
					 },
				 success:function(result){
				        if (result.responseCode == 101){
			                showInfo('更新失败！原因：'+result.responseMsg);
			            } else {
			            	$('#taskList').datagrid("reload"); 
			            	showInfo('更新成功！'); 
			            }
			       }
			});
		}

		function deleteJob(){
			 var node = $("#taskList").datagrid('getSelected');
			 $.ajax({
			     type:"POST",
				 url:basePath+'/task/delete.do',
				 data:{
					 jobName:node.jobName,
					 jobGroup:node.jobGroup
					 },
				 success:function(result){
				        if (result.responseCode == 101){
			                showInfo('更新失败！原因：'+result.responseMsg);
			            } else {
			            	$('#taskList').datagrid("reload"); 
			            	showInfo('更新成功！'); 
			            }
			       }
			});
		}
		 