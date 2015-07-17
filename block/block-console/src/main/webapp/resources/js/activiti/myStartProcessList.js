//我发起的流程
	$(function() {
		initMyStartList();
	});
	
	//我启动的流程
	function initMyStartList(){
		 $('#processList').datagrid({
		 url: basePath+'/activitiProcess/myStartProcess.do',
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
	                    title: '类别',
	                    field: 'category',
	                    width: 100
	                },
	                { 
	                    title: '版本',
	                    field: 'version',
	                    width: 100
	                },{
			             title: '当前节点',
			             field: 'opt',
			             width: 150,
			             formatter: function (value, rec, index) {
		                    	var str='';
		                    	 if(rec.currentNode!=null){
		                    		 str = '<a processInstanceId=' + rec.processInstanceId +' processDefId='+rec.id+ ' href="#" class="activity">'+rec.currentNode+'</a>';
		                    	}else{
		                    		str="已结束";
		                    	} 
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
         } 
		});
		
//		initPager("#processList");
	}