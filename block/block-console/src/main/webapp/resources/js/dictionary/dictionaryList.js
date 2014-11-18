	 
	 $(function(){
		 	
			initDictionaryList();
//			initToolBar();
	});

 
 
	function initDictionaryList(){
		 $('#dictionaryList').datagrid({
		 url: basePath+'/dictionary/dictionaryList.do',
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
		 columns: [
		     [
		         {
		             title: '数据字典',
		             field: 'dictionaryValue',
		             width: 120
		         },
		         {
		             title: '数据字典中文名称',
		             field: 'dictionaryLabel',
		             width: 100
		         },
		         {
		             title: '是否可用',
		             field: 'isavailable',
		             width: 100
		         },
		         {
		             title: '备注',
		             field: 'remarks',
		             width: 100
		         },
		         {
		             title: '创建人',
		             field: 'createBy',
		             width: 100
		         },
		         {
		             title: '创建时间',
		             field: 'createTime',
		             width: 100
		         },
		         {
		             title: '修改人',
		             field: 'modifiBy',
		             width: 100
		         } ,
		         {
		             title: '修改时间',
		             field: 'modifiTime',
		             width: 100
		         } 
		     ]
		 ],
		 onClickRow: function(rowIndex, rowData){
			 dictionaryItemList(rowData.dictionaryValue);
		 }
		});
		 
		 var pager = $('#dictionaryList').datagrid('getPager');  
			pager.pagination({
				beforePageText: '第',              
				afterPageText: '页    共 {pages} 页',                    
				displayMsg: '当前显示 {from} - {to} 条   共 {total} 条'
			});
			
	}

	function initToolBar(){
		$('#toolbar').toolbar({
	        items: [
	            {
	                text: '新增',
	                iconCls: 'icon-add',
	                handler: function () {
	                	openAddDictionaryDialog();
	                }
	            },
	            {
	            	 text: '删除',
		                iconCls: 'icon-del',
		                handler: function () {
		                	delDictionary();
		                }
	            } ,
	            {
	            	 text: '修改',
		                iconCls: 'icon-edit',
		                handler: function () {
		                	openEdtDictionaryDialog();
		                }
	            } 
	            /*,
	            {
	            	 text: '导入数据字典',
		                iconCls: 'icon-edit',
		                handler: function () {
		                	uploadFilebyParas("DictionaryImport",'{}');
		                }
	            },
	            {
	            	 text: '导入数据字典项',
		                iconCls: 'icon-edit',
		                handler: function () {
		                	uploadFilebyParas("DictionaryItemImport",'{}');
		                }
	            }*/
	        ]
	    });
	}
	
	function delDictionary(){
		var row = $('#dictionaryList').datagrid('getSelected');
		if(row){
			$.messager.confirm('请选择','确定要删除该数据字典吗?',function(r){ 
				if (r){
					doDelDictionary(row.dictionaryId);
				} 
			});
		}else{
			showInfo('请选择要删除的数据字典');
		}
	}
	
	function doDelDictionary(dictionaryId){
		 $.ajax({
	     type:"POST",
		 url:basePath+'/dictionary/delDictionary.do',
		 data:{
			 dictionaryId:dictionaryId
		},
		 success:function(result){ 
			 if (result.responseCode == 101){
	                showInfo('操作失败！原因：'+result.responseMsg);
	            } else {
	            	showSuc('操作成功！'); 
	            	$('#dictionaryList').datagrid("reload"); 
	                $('#dictionaryItemList').datagrid('loadData', { total: 0, rows: [] });
	         }
	     }  
	   });
	}

	/*查询*/
	function doSearch(){
		$('#dictionaryList').datagrid('load',$('#searchForm').serializeJson());
	}

	//重置
	function doReset(){
		$('#searchForm').form('reset');
	}
	
	
	
	//修改数据字典弹窗
    function openEdtDictionaryDialog(){
    	var row = $('#dictionaryList').datagrid('getSelected');
    	var dictionaryId=row.dictionaryId;
    	ygDialog({
            width: 600,
            height: 400,
            title: '修改数据字典',
            href: basePath+'/dictionary/editDictionaryPage.do?dictionaryId='+dictionaryId+'&sessionId='+sessionId,
            onSave: function (dg) {
            	saveEditDictionary(dg);
            }
        });
    }
    
   
	
	//新增数据字典弹窗
    function openAddDictionaryDialog(){
    	ygDialog({
            width: 600,
            height: 400,
            title: '新增数据字典',
            href: basePath+'/dictionary/redirect.do?redirect=dictionary/dictionaryAdd&sessionId='+sessionId,
            onSave: function (dg) {
            	saveDictionary(dg);
            }
        });
    }
    
    function saveEditDictionary(dg){
		var url = basePath+'/dictionary/editDictionary.do';
		var myform=$('#dictionaryEditForm');
		myform.form("submit",{
			url:url,
			onSubmit:function(){
				return $(this).form('validate');
			},
			success:function(result){
	            if (result.responseCode == 101){
	                showInfo('操作失败！原因：'+result.responseMsg);
	            } else {
	            	showSuc('操作成功！'); 
	            	$('#dictionaryList').datagrid("reload"); 
	            	dg.close();
	            }
			}
		});
    }
    
    function saveDictionary(dg){
    	endEditing();//结束编辑数据项框,否则不能拿到最后一行的数据
    	var dictionaryItemJsonData= $("#dictionaryItemEditTable").datagrid("getData").rows;//获得选中数据项信息
    	if(dictionaryItemJsonData!=null&&dictionaryItemJsonData.length>0){
    		$("#dictionaryItemJsonData").val(JSON.stringify(dictionaryItemJsonData));
    	}else{
    		showInfo('保存失败, 数据字典项不能为空!');
    		return;
    	}
		var url = basePath+'/dictionary/addDictionary.do?sessionId='+sessionId;
		var myform=$('#dictionaryAddForm');
		myform.form("submit",{
			url:url,
			onSubmit:function(){
				return $(this).form('validate');
			},
			success:function(result){
	            if (result.responseCode == 101){
	                showInfo('操作失败！原因：'+result.responseMsg);
	            } else {
	            	showSuc('操作成功！'); 
	            	$('#dictionaryList').datagrid("reload"); 
	            	dg.close();
	            }
			}
		});
    }
    
  //数据字典工具栏
    var dictionaryItemEditTableToolbar = [{
		text:'添加',
		iconCls:'icon-add',
		handler:function(){
			insertRow();
		}
	},
	'-',{
		text:'删除',
		iconCls:'icon-del',
		handler:function(){
			delRow();
		}
	}
  ];
    
   
    //新增行
    function insertRow(){
    	var tb= $('#dictionaryItemEditTable');
    	var index = tb.datagrid('getRows').length;//获得所有行
    	if (editIndex != index){
	        if (endEditing()){
	        	tb.datagrid('appendRow',{});
		    	tb.datagrid('selectRow', index).datagrid('beginEdit', index);
		    	editIndex = index;
		      }
    	}
        
        tb.datagrid('uncheckAll');	
	}
    
    
	  var editIndex = undefined;
	  //结束编辑
	  function endEditing(){
	      if (editIndex == undefined){
	    	  	return true;
	    	  }
	      if ($("#dictionaryItemEditTable").datagrid('validateRow', editIndex)){
	      		$("#dictionaryItemEditTable").datagrid('endEdit', editIndex);
	      		editIndex = undefined;
	      		return true;
	      } else {
	          return false;
	      }
	  }
	  
	  
	//删除行
	    function delRow(){
			var row = $('#dictionaryItemEditTable').datagrid('getSelected');
			if (row){
				var index = $('#dictionaryItemEditTable').datagrid('getRowIndex', row);
				$('#dictionaryItemEditTable').datagrid('deleteRow', index);
			} else {
				showInfo('请选择要删除的行');
			}
	    }
  
	function dictionaryItemList(dictionaryValue){
		console.log("11:"+dictionaryValue);
	     $('#dictionaryItemList').datagrid({
	        url: basePath+'/dictionary/dictionaryItemList.do',
	        singleSelect:true,
	        striped:true,
	        rownumbers:true,
	        checkOnSelect: true,
	        selectOnCheck:true,
	        queryParams : {
	        	dictionaryValue : dictionaryValue
			},
			toolbar: [
				{
				    text:'添加',
				    iconCls:'icon-add',
				    handler:function(){
				    	openAddItemDialog();
				    }
				},
				{
				    text:'删除',
				    iconCls:'icon-del',
				    handler:function(){
				    	delDictionaryItem();
				    }
				},
			    {
		        text:'修改',
		        iconCls:'icon-edit',
		        handler:function(){
		        	openEditItemDialog();
		        }
		    }],
	        frozenColumns: [
	            [
	                {
	                    title:'itemId',
	                    field:'itemId',
	                    checkbox: true
	                }
	            ]
	        ],
	        columns: [
	            [
	             	
	                {
	                    title: '数据字典项',
	                    field: 'itemValue',
	                    width: 120
	                },
	                {
	                    title: '数据字典项名称',
	                    field: 'itemLabel',
	                    width: 100
	                },
	                {
	                    title: '排序号',
	                    field: 'orderNo',
	                    width: 100
	                },
	                {
			             title: '备注',
			             field: 'remarks',
			             width: 100
			         },
			         {
			             title: '创建人',
			             field: 'createBy',
			             width: 100
			         },
			         {
			             title: '创建时间',
			             field: 'createTime',
			             width: 100
			         },
			         {
			             title: '修改人',
			             field: 'modifyBy',
			             width: 100
			         } ,
			         {
			             title: '修改时间',
			             field: 'modifyTime',
			             width: 100
			         } 
	            ]
	        ],
	        onLoadSuccess: function () {
	        	
	        },
	        onDblClickRow:function (rowIndex, rowData) {
	        	
	        },
	        onClickRow:function(rowIndex,rowData){
//	        	startEdt(rowIndex);
	        }
	    });
    }
	
	function openAddItemDialog(){
		var row = $('#dictionaryList').datagrid('getSelected');
    	var dictionaryValue=row.dictionaryValue;
    	ygDialog({
            width: 600,
            height: 400,
            title: '新增数据字典项',
            href: basePath+'/dictionary/addItemPage.do?dictionaryValue='+dictionaryValue,
            onSave: function (dg) {
            	saveItem(dg);
            }
        });
	}
	
    function openEditItemDialog(){
    	var row = $('#dictionaryItemList').datagrid('getSelected');
    	var itemId=row.itemId;
    	ygDialog({
            width: 600,
            height: 400,
            title: '修改数据字典项',
            href: basePath+'/dictionary/editItemPage.do?itemId='+itemId,
            onSave: function (dg) {
            	saveEditItem(dg);
            }
        });
    }
    
    function saveItem(dg){
    	var url = basePath+'/dictionary/addItem.do';
		var myform=$('#itemEditForm');
		myform.form("submit",{
			url:url,
			onSubmit:function(){
				return $(this).form('validate');
			},
			success:function(result){
	            if (result.responseCode == 101){
	                showInfo('操作失败！原因：'+result.responseMsg);
	            } else {
	            	showSuc('操作成功！'); 
	            	$('#dictionaryItemList').datagrid("reload"); 
	            	dg.close();
	            }
			}
		});
    }
    
    function saveEditItem(dg){
		var url = basePath+'/dictionary/editItem.do';
		var myform=$('#itemEditForm');
		myform.form("submit",{
			url:url,
			onSubmit:function(){
				return $(this).form('validate');
			},
			success:function(result){
	            if (result.responseCode == 101){
	                showInfo('操作失败！原因：'+result.responseMsg);
	            } else {
	            	showSuc('操作成功！'); 
	            	$('#dictionaryItemList').datagrid("reload"); 
	            	dg.close();
	            }
			}
		});
    }
    
    function delDictionaryItem(){
		var row = $('#dictionaryItemList').datagrid('getSelected');
		if(row){
			$.messager.confirm('请选择','确定要删除该数据字典项吗?',function(r){ 
				if (r){
					doDelDictionaryItem(row.itemId);
				} 
			});
		}else{
			showInfo('请选择要删除的数据字典项');
		}
	}
    
    
    function doDelDictionaryItem(itemId){
		 $.ajax({
	     type:"POST",
		 url:basePath+'/dictionary/delDictionaryItem.do',
		 data:{
			 itemId:itemId
		},
		 success:function(result){ 
			 if (result.responseCode == 101){
	                showInfo('操作失败！原因：'+result.responseMsg);
	            } else {
	            	showSuc('操作成功！'); 
	            	$('#dictionaryItemList').datagrid("reload"); 
	         }
	     }  
	   });
	}
    
	
	function checkExist(){
		var dictionaryValue=$("#dictionaryValueId").val().trim();
		if(dictionaryValue!=null&&dictionaryValue!=""){
			$.ajax({
			     type:"POST",
				 url:basePath+'/dictionary/getDictionary.do',
				 data:{
					 dictionaryValue:dictionaryValue
				},
				 success:function(result){ 
					 var dataObj=eval("("+result+")");
					 if(dataObj!=null){
						 showSuc('该数据字典已经存在!');
						 $("#dictionaryValueId").focus();
					 }
			     }  
			});
		}
	}
 
