//创建文件数据表格
    $(function(){
    	 loadFile_listall();
	});
    
   function loadFile_listall(){
	   $('#allfiletable').datagrid({
		   url:basePath+'user/getList.do',
//		   	fit: true, 自适应宽度
			width :'auto',  
			height:'auto',
			fitColumns : true,
			nowrap:false,
			striped: true,//隔行变色
			collapsible:true,
			loadMsg:'数据装载中......',
			singleSelect:false,   
			rownumbers:true,
			pagination : true,
			columns:[[
				{checkbox:true,field:'ck',width:'100',align:'center'},           
				{title:'id',field:'userId',width:'50',align:'center',hidden:true}, 
				{title:'用户名',field:'userName',width:'100',align:'center'},
				{title:'pwd',field:'userPwd',width:'200',align:'center'},
				{title:'状态',field:'status',width:'50',align:'center'},
				{title:'创建时间',field:'crtime',width:'200',align:'center'},
				{title:'创建人',field:'cruser',width:'50',align:'center'} 
	    	]],
	    	onLoadSuccess:function(data){
	    	},
//	    	toolbar: "#toolbar"//工具栏
	    	toolbar: [{
	  				    text:'添加',
	  				    iconCls:'icon-add',
	  				    handler:function(){
	  				    	openAddUserDialog();
	  				    }
	  				},
	  				{
	  				    text:'删除',
	  				    iconCls:'icon-del',
	  				    handler:function(){
	  				    	delUser();
	  				    }
	  				},
	  			    {
	  		        text:'修改',
	  		        iconCls:'icon-edit',
	  		        handler:function(){
	  		        	openEditUserDialog();
	  		        }
	  		    }],
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
		        			 url:basePath+'user/remove',
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
		 url:basePath+'user/add',
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
			href: basePath+'user/updPage?id='+id,
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
			 url:basePath+'user/upd',
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
   
   function openAddUserDialog() {
	   var path=basePath+'common/toListPage.do?pagePath=/user/userAdd';
	   var mydialog= openMyDialog(path,'添加用户');
	   /*mydialog.dialog({
		   buttons:[{
						text : '提交',
						iconCls : 'icon-ok',
						handler : function() {
							alert("ok");
						}
					},{
						text : '关闭',
						handler : function() {
							 var d = $(this).closest('.window-body');
				             d.dialog('destroy');
						}
					}]
		   }); */
	    
		}
   
   function initCRmaList(){
  	 console.log(1);
       $('#allfiletable').datagrid({
       url: basePath+'user/getList.do',
       striped:true,
//       idField:'crmaId',
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
//       	openCRmaDetail(rowData.crmaId,rowData.crmaNo);
   	}
     });
	}
   
