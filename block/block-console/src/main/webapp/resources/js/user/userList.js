//创建文件数据表格
     $(function(){
    	 loadFile_listall();
//    	 initCRmaList();
    	 
		});
     
     function initCRmaList(){
    	 console.log(1);
         $('#allfiletable').datagrid({
         url: host+'user/getList.do',
         striped:true,
//         idField:'crmaId',
         singleSelect:true,
         rownumbers:true,
         checkOnSelect: true,
         /*
         frozenColumns: [
             [
                 {
                     title:'crmaId',
                     field:'crmaId',
                     checkbox: true
                 }
             ]
         ],*/
         columns: [
             [
                 {
                     title: '状态',
                     field: 'userId',
                     width: 120
                 },
                 {
                     title: '退换日期',
                     field: 'crmaDate',
                     width: 100
                 },
                 {
                     title: 'RMA#',
                     field: 'crmaNo',
                     width: 100
                 },
                 {
                     title: '客户',
                     field: 'custormerName',
                     width: 100
                 },
                 {
                     title: '客户类型',
                     field: 'zhCustormerType',
                     width: 100
                 },
                 {
                     title: '退换类型',
                     field: 'zhReturnType',
                     width: 100
                 },
                 {
                     title: 'RMA类型',
                     field: 'zhCrmaType',
                     width: 100
                 } 
             ]
         ],
         onLoadSuccess: function () {
         },
         onClickRow: function(rowIndex, rowData){
//         	openCRmaDetail(rowData.crmaId,rowData.crmaNo);
     	}
       });
 	}
     
    
   function loadFile_listall(){
	   $('#allfiletable').datagrid({
			iconCls:'icon-ok',
			width :'auto',  
			height:'auto',
			fitColumns : true,
			idField:'id',
			nowrap:false,
			striped: true,
			collapsible:true,
			url:host+'user/getList.do',
			loadMsg:'数据装载中......',
			singleSelect:false,   
			pagination : true,
			pageSize:10,
			pageList: [10,20, 30],
			columns:[[
				{checkbox:true,field:'ck',width:'100',rowspan:2,align:'center'},           
				{title:'id',field:'id',width:'50',rowspan:2,align:'center',hidden:true}, 
				{title:'姓名',field:'title',width:'100',rowspan:2,align:'center'},
				{title:'描述',field:'desc',width:'200',rowspan:2,align:'center'},
				{title:'状态',field:'status',width:'50',rowspan:2,align:'center'},
				{title:'创建时间',field:'crtime',width:'200',rowspan:2,align:'center'},
				{title:'创建人',field:'cruser',width:'50',rowspan:2,align:'center'} 
	    	]],
	    	rownumbers:true,
	    	onLoadSuccess:function(data){
	    	},
	    	toolbar: "#toolbar"//工具栏
		});
		var pager = $('#allfiletable').datagrid('getPager');  
		pager.pagination({
			beforePageText: '第',              
			afterPageText: '页    共 {pages} 页',                    
			displayMsg: '当前显示 {from} - {to} 条   共 {total} 条'
		});
   }  
     
	//删除操作  
   function removeM(){
	   $.messager.confirm('请选择','是否删除?',function(r){   
		         if (r){   
		        		var row = $('#allfiletable').datagrid('getSelected');
		        	    var id=row.id;
		        		 if(id!=""){
		        		   $.ajax({
		        		     type:"POST",
		        			 url:host+'user/remove',
		        			 data:{ids:id},
		        			 success:function(msg){
		        		            if(msg==true){
		        		            	$.messager.show({
		        		            		title:'我的消息',
		        		            		msg:'删除成功!',
		        		            		timeout:2000,
		        		            		showType:'slide'
		        		            	});
		        		           	 $('#allfiletable').datagrid("reload"); 
		        		           }else{
		        		            alert("fail");
		        		           }
		        		        	$('#allfiletable').datagrid('uncheckAll');
		        		       }  
		        		   });
		        		 }
		         }   
		     });  
	}
   
   function addM(){
		var title=$("#title").val();
		var desc=$("#desc").val();
	    $.ajax({
	     type:"POST",
		 url:host+'user/add',
		 data:{title:title,desc:desc},
		 success:function(msg){
	            if(msg==true){
	            	$.messager.show({
	            		title:'我的消息',
	            		msg:'删除成功!',
	            		timeout:2000,
	            		showType:'slide'
	            	}); 
	           	$('#allfiletable').datagrid("reload"); 
	           	$('#mydialog').dialog("close");
	           }else{
	            alert("fail");
	           }
	       }  
	   });
   }
   
   function updPage(){
	   var row = $('#allfiletable').datagrid('getSelected');
	    var id=row.id;
	   $('#mydialog').show();
		$('#mydialog').dialog({
			collapsible : true,
			minimizable : true,
			maximizable : true,
			href: host+'user/updPage?id='+id,
			buttons : [ {
				text : '提交',
				iconCls : 'icon-ok',
				handler : function() {
					updM();
				}
			}, {
				text : '取消',
				handler : function() {
					$('#mydialog').dialog("close");
				}
			} ]
		});
	}
   
   function updM(){
	   var title=$("#title").val();
		var desc=$("#desc").val();
		var id=$("#id").val();
	   $.ajax({
		     type:"POST",
			 url:host+'user/upd',
			 data:{title:title,desc:desc,id:id},
			 success:function(msg){
		            if(msg==true){
		            	$.messager.show({
		            		title:'我的消息',
		            		msg:'删除成功!',
		            		timeout:2000,
		            		showType:'slide'
		            	}); 
		           	$('#allfiletable').datagrid("reload"); 
		           	$('#mydialog').dialog("close");
		           }else{
		            alert("fail");
		           }
		       }  
		   });
   }
   
   function Open_Dialog() {
			$('#mydialog').show();
			$('#mydialog').dialog({
				collapsible : true,
				minimizable : true,
				maximizable : true,
				href: 'redirect/toPage?path=/user/add_dialog',
				buttons : [ {
					text : '提交',
					iconCls : 'icon-ok',
					handler : function() {
						addM();
					}
				}, {
					text : '取消',
					handler : function() {
						$('#mydialog').dialog("close");
					}
				} ]
			});
		}
