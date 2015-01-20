	$(function() {
		initTodoTaskList();
	});
	
	function initTodoTaskList(){
		 $('#todoTaskList').datagrid({
		 url: basePath+'/activitiTask/todoTaskList.do',
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
                   title:'taskId',
                   field:'taskId',
                   checkbox: true
               }
           ]
       ],
		 columns: [
		     [
				{
				    title: 'processInstanceId',
				    field: 'processInstanceId',
				    width: 120
				},
		         {
		             title: '任务名称',
		             field: 'taskName',
		             width: 120
		         },
		         {
		             title: '流程名称',
		             field: 'processName',
		             width: 100
		         },
		         {
		             title: '优先级',
		             field: 'taskPriority',
		             width: 100
		         },
		         {
		             title: '创建时间',
		             field: 'createTime',
		             width: 100
		         },
		         {
		             title: '过期时间',
		             field: 'dueDate',
		             width: 100
		         },
		         {
		             title: '所属人',
		             field: 'taskOwner',
		             width: 100
		         },
		         {
		             title: '指派人',
		             field: 'taskAssignee',
		             width: 100
		         },
		         {
		             title: '任务类型',
		             field: 'taskType',
		             width: 100,
		             hidden:true
		         },
		         {
		             title: '任务类型',
		             field: 'taskTypeFormatter',
		             width: 100
		         },
		         {
	                    title: '当前节点',
	                    field: 'opt',
	                    width: 100,
	                    formatter: function (value, rec, index) {
	                    	console.log(rec);
	                    	var str='';
	                    	str = '<a processInstanceId=' + rec.processInstanceId +' processDefId='+rec.processDefId+ ' href="#" class="activity">'+11+'</a>';
	                    	/*if(rec.activityId!=null){
	                    		str = '<a processId=' + rec.id +' processDefId='+rec.processDefId+ ' href="#" class="activity" onClick=graphTrace("1,2")>'+11+'</a>';
	                    	}*/
	                        return str;
	                    }
		         }
		     ]
		 ],
	 	 onLoadSuccess: function () {
            //查看当前的节点
            $('.activity').click(function () {
                graphTrace( $(this));
                return false;
            }); 
         },
		 onClickRow: function(rowIndex, rowData){
			  
		 }
		});
		
		initPager("#todoTaskList");
	}