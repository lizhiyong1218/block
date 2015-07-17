//已经结束流程

	$(function() {
		initfinishProcessList();
	});
	
	function initfinishProcessList(){
		 $('#finishProcessList').datagrid({
		 url: basePath+'/activitiProcess/finishProcess.do',
		 width :'auto',  
		 height:'auto',
		 fit: false,
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
		             field: 'name',
		             width: 100
		         }
		         , 
		         {
		             title: '流程发起人',
		             field: 'startUser',
		             width: 100
		         },
		         {
		             title: '创建时间',
		             field: 'startTime',
		             width: 150
		         },
		         {
		             title: '结束时间',
		             field: 'endTime',
		             width: 150
		         } 
		     ]
		 ],
	 	 onLoadSuccess: function () {
            //查看当前的节点
            $('.activity').click(function () {
                graphTrace( $(this));
                return false;
            }); 
         } 
		});
//		initPager("#finishProcessList");
	}
	
//	//重置
//	function doReset(){
//		$('#searchForm').form('reset');
//	}
//	
//	/*查询*/
//	function doSearch(){
// 		$('#finishProcessList').datagrid('load',$('#searchForm').serializeJson());
//	}