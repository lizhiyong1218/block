//我的流程
	$(function() {
		initMyStartList();
	});
	
	//我启动的流程
	function initMyStartList(){
		 $('#todoTaskList').datagrid({
		 url: basePath+'/activitiProcess/myStartProcess.do',
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