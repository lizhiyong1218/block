 $(function(){
	 dictionaryItemEditTable();
	});
 
 	/**
	 * 新增数据字典时验证数据字典是否存在
	 */
	function checkExist(){
		var dictionaryValue=$("#dictionaryValueId").val().trim();
		if(dictionaryValue!=null&&dictionaryValue!=""){
			$.ajax({
			     type:"POST",
				 url:basePath+'/dictionary/existDictionary.do',
				 data:{
					 dictionaryValue:dictionaryValue
				},
				 success:function(result){ 
					 if(result==true){
						 showInfo('该数据字典已经存在!');
						 $("#dictionaryValueId").focus(); 
					 } 
			     }  
			});
		}
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
 
 
//数据字典项工具栏
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
 
 function dictionaryItemEditTable(){
	 $('#dictionaryItemEditTable').datagrid({
		 width :'auto',  
		 height:'auto',
		 fitColumns : true,
		 nowrap:false,
		 striped: true,//隔行变色
		 collapsible:true,
		 singleSelect:true,
		 rownumbers:true,
		 checkOnSelect: true,
		 
		 toolbar:  dictionaryItemEditTableToolbar,
		 frozenColumns : [ [ {
			title : 'dictionaryId',
			field : 'dictionaryId',
			checkbox : true
		} ] ],
		 
		 columns: [
		     [
		         {
		             title: '数据字典项',
		             field: 'itemValue',
		             editor:{type:'validatebox',options:{required:true,missingMessage: '数据字典项不能为空'}},
		             width: 120
		         },
		         {
		             title: '数据字典项名称',
		             field: 'itemLabel',
		             editor:{type:'validatebox',options:{required:true,missingMessage: '数据字典项不能为空'}},
		             width: 100
		         },
		         {
		             title: '排序号',
		             field: 'orderNo',
		             editor:{type:'text'},
		             width: 100
		         },
		         {
		             title: '备注',
		             field: 'remarks',
		             editor:{type:'text'},
		             width: 100
		         }
		     ]
		 ],
		 
		});
		
 }