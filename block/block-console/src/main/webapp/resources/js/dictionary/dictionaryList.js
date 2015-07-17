	 
		 $(function(){
				initDictionaryList();
		});

		/*查询*/
		function doSearch(){
			$('#dictionaryList').datagrid('load',$('#searchForm').serializeJson());
		}

		//重置
		function doReset(){
			$('#searchForm').form('reset');
		}
 
	/**
	 * 数据字典
	 */
	function initDictionaryList(){
		 $('#dictionaryList').datagrid({
		 url: basePath+'/dictionary/dictionaryList.do',
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
                    title:'dictionaryId',
                    field:'dictionaryId',
                    checkbox: true
                }
            ]
        ],
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
		
		initPager("#dictionaryList");
	}
	
	/**
	 * 删除数据字典弹窗
	 */
	function delDictionary(){
		var row = $('#dictionaryList').datagrid('getSelected');
		if(row){
			$.messager.confirm('请选择','确定要删除该数据字典吗?',function(r){ 
				if (r){
					doDelDictionary(row.dictionaryId,row.dictionaryValue);
				} 
			});
		}else{
			showWarm('请选择要删除的数据字典');
		}
	}
	
	
	
	/**
	 * 新增数据字典弹窗
	 */
    function openAddDictionaryDialog(){
    	var path=basePath+'common/toPage.do?pagePath=dictionary/dictionaryAdd';
  	   	var mydialog= openMyDialog(path,'添加数据字典');
  	   	mydialog.dialog({
  		   width:600,
  		   height:400,
  		   buttons:[{
  						text : '提交',
  						iconCls : 'icon-ok',
  						handler : function() {
  							saveDictionary(mydialog);
  						}
  					} ]
  		   }); 
    }
    
    /**
     * 保存数据字典
     * @param dg
     */
    function saveDictionary(dg){
    	endEditing();//结束编辑数据项框,否则不能拿到最后一行的数据
    	var dictionaryItemJsonData= $("#dictionaryItemEditTable").datagrid("getData").rows;//获得选中数据项信息
    	if(dictionaryItemJsonData!=null&&dictionaryItemJsonData.length>0){
    		$("#dictionaryItemJsonData").val(JSON.stringify(dictionaryItemJsonData));
    	}else{
    		showInfo('保存失败, 数据字典项不能为空!');
    		return;
    	}
		var url = basePath+'/dictionary/addDictionary.do';
		var myform=$('#dictionaryAddForm');
		myform.form("submit",{
			url:url,
			onSubmit:function(){
				return $(this).form('validate');
			},
			success:function(result){
				var dataObj=eval("("+result+")");//转换为json对象 
	            if (dataObj.status == 102){
	                showInfo('操作失败！原因：'+dataObj.message);
	            } else {
	            	showInfo('操作成功！'); 
	            	$('#dictionaryList').datagrid("reload"); 
	            	closeMyDialog(dg);
	            }
			}
		});
    }
    
    
    /**
	 * 修改数据字典弹窗
	 */
    function openEdtDictionaryDialog(){
    	var row = $('#dictionaryList').datagrid('getSelected');
    	var dictionaryId=row.dictionaryId;
    	var path= basePath+'/dictionary/dictionaryEditPage.do?dictionaryId='+dictionaryId;
    	var mydialog= openMyDialog(path,'修改数据字典');
  	   	mydialog.dialog({
  		   width:500,
  		   height:200,
  		   buttons:[{
  						text : '提交',
  						iconCls : 'icon-ok',
  						handler : function() {
  							saveEditDictionary(mydialog);
  						}
  					} ]
  		   }); 
    }
    
    /**
     * 修改数据字典
     * @param dg
     */
    function saveEditDictionary(dg){
		var url = basePath+'/dictionary/editDictionary.do';
		var myform=$('#dictionaryEditForm');
		var opt = {
		        loadMsg: '处理中..',
		        target:$('.window')
		};
		$.mask(opt);
		myform.form("submit",{
			url:url,
			onSubmit:function(){
				return $(this).form('validate');
			},
//		    beforeSend: function(jqXHR){
//		    	$.mask(opt);
//			},
			success:function(result){
				var dataObj=eval("("+result+")");//转换为json对象 
	            if (dataObj.status == 102){
	                showInfo('操作失败！原因：'+dataObj.message);
	            } else {
	            	showInfo('操作成功！'); 
	            	$('#dictionaryList').datagrid("reload"); 
	            	closeMyDialog(dg);
	            }
			}
//			,
//			complete: function(jqXHR, textStatus){
//				$.unmask(opt);
//			} 
		});
    }
    
    /**
	 * 删除数据字典
	 * @param dictionaryId 数据字典id
	 * @param dictionaryValue 数据字典
	 */
	function doDelDictionary(dictionaryId,dictionaryValue){
		 $.ajax({
	     type:"POST",
		 url:basePath+'dictionary/delDictionary.do',
		 data:{
			 dictionaryId:dictionaryId,
			 dictionaryValue:dictionaryValue
		},
		 success:function(dataObj){ 
	            if (dataObj.status == 102){
	                showInfo('操作失败！原因：'+dataObj.message);
	            } else if(dataObj.status == 101){
	            	showInfo('操作成功！'); 
	            	$('#dictionaryList').datagrid("reload"); 
	                $('#dictionaryItemList').datagrid('loadData', { total: 0, rows: [] });
	            }else{
	            	showInfo('异常！');
	            }
	     }  
	   });
		
	}
    
    /*****数据字典项******/
    
    /**
	 * 数据字典项
	 * @param dictionaryValue 数据字典值
	 */
	function dictionaryItemList(dictionaryValue){
	     $('#dictionaryItemList').datagrid({
	        url: basePath+'/dictionary/dictionaryItemList.do',
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
	    });
		initPager("#dictionaryItemList");
    }
	
	/**
	 * 添加数据字典项弹窗
	 */
	function openAddItemDialog(){
		var row = $('#dictionaryList').datagrid('getSelected');
    	var dictionaryValue=row.dictionaryValue;
    	var path=basePath+'/dictionary/addItemPage.do?dictionaryValue='+dictionaryValue;
  	   	var mydialog= openMyDialog(path,'添加数据字典项');
  	   	mydialog.dialog({
  		   width:500,
  		   height:200,
  		   buttons:[{
  						text : '提交',
  						iconCls : 'icon-ok',
  						handler : function() {
  							saveItem(mydialog);
  						}
  					} ]
  		   }); 
	}
	
	/**
	 * 修改数据字典项弹窗
	 */
    function openEditItemDialog(){
    	var row = $('#dictionaryItemList').datagrid('getSelected');
    	var itemId=row.itemId;
    	var path=basePath+'/dictionary/editItemPage.do?itemId='+itemId;
    	var mydialog= openMyDialog(path,'修改数据字典项');
  	   	mydialog.dialog({
  		   width:500,
  		   height:200,
  		   buttons:[{
  						text : '提交',
  						iconCls : 'icon-ok',
  						handler : function() {
  							saveEditItem(mydialog);
  						}
  					} ]
  		   }); 
    }
    
    /**
     * 新增数据字典项
     * @param mydialog
     */
    function saveItem(mydialog){
    	var url = basePath+'/dictionary/addItem.do';
		var myform=$('#itemEditForm');
		myform.form("submit",{
			url:url,
			onSubmit:function(){
				return $(this).form('validate');
			},
			success:function(result){
				var dataObj=eval("("+result+")");//转换为json对象 
	            if (dataObj.status == 102){
	                showInfo('操作失败！原因：'+dataObj.message);
	            } else {
	            	showInfo('操作成功！'); 
	            	$('#dictionaryItemList').datagrid("reload"); 
	            	closeMyDialog(mydialog);
	            } 
			}
		}); 
    }
    
    /**
     * 修改数据字典项
     * @param dg
     */
    function saveEditItem(dg){
		var url = basePath+'/dictionary/editItem.do';
		var myform=$('#itemEditForm');
		myform.form("submit",{
			url:url,
			onSubmit:function(){
				return $(this).form('validate');
			},
			success:function(result){
				var dataObj=eval("("+result+")");//转换为json对象 
	            if (dataObj.status == 102){
	                showInfo('操作失败！原因：'+dataObj.message);
	            } else {
	            	showInfo('操作成功！'); 
	            	$('#dictionaryItemList').datagrid("reload"); 
	            	closeMyDialog(dg);
	            }
			}
		});
    }
    
    /**
     * 删除数据字典项
     */
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
    
    /**
     * 删除数据字典项
     * @param itemId 数据字典项id
     */
    function doDelDictionaryItem(itemId){
		 $.ajax({
	     type:"POST",
		 url:basePath+'/dictionary/delDictionaryItem.do',
		 data:{
			 itemId:itemId
		 },
		 success:function(result){ 
			 var dataObj=eval("("+result+")");//转换为json对象 
	            if (dataObj.status == 102){
	                showInfo('操作失败！原因：'+dataObj.message);
	            } else {
	            	showInfo('操作成功！'); 
	            	$('#dictionaryItemList').datagrid("reload"); 
	         }
	     }  
	   });
	}
