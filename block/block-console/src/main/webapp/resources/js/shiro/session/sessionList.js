	$(function(){
		initSessionList();
	});
	
	
	/**
	 * 会话列表
	 */
	function initSessionList(){
		 $('#sessionList').datagrid({
		 url: basePath+'session/sessionList.do',
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
                    title:'sessionId',
                    field:'sessionId',
                    checkbox: true
                }
            ]
        ],
		 columns: [
		     [
		         {
		             title: 'id',
		             field: 'id',
		             width: 120
		         },
		         {
		             title: 'lastAccessTime',
		             field: 'lastAccessTime',
		             width: 100
		         },
		         {
		             title: 'host',
		             field: 'host',
		             width: 100
		         } 
		     ]
		 ]
		 
		});
		
		initPager("#sessionList");
	}