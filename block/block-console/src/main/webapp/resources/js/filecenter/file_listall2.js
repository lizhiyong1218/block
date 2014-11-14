$(function() {
//var PostData={keyWord:keyW,channlName:channN,classifyName:classifyN};
	$("#list").jqGrid({
		caption : "",//表格标题
		hidegrid : false,
		url : host + 'filecenter/listall',
		contentType : 'application/json',
		datatype : "json",
		mtype : "post",
		colNames : ['视频标题', '创建用户', '创建时间','视频状态','操作'],
		
		colModel : [{
			name : 'title',
			index : 'title',
			width : 100,
			align : 'center',
			sortable : false
		},  {
			name : 'cruser',
			index : 'cruser',
			width : 100,
			align : 'center',
			sortable : false
		}, {
			name : 'crtime',
			index : 'crtime',
			width : 150,
			align : 'center'
		}, {
			name : 'status',
			index : 'status',
			width : 150,
			align : 'center'
		},  {
			name : 'modify',
			index : 'modify',
			width : 80,
			align : 'center',
				sortable:false
		}],
		jsonReader : {
			root : "recordList",
			total : "pageCount",
			page : "currentPage",
			records : "recordCount",
			repeatitems : false,
			recordList : "recordList"
			//id : "id",
			//group:"group"
		},
		rowNum : 3,
		rowList : [10, 20, 30],
		pager : 'pager',
		sortname : 'id',
		//postData: PostData,//传参数
		height:'auto',
		autowidth: true,  
		viewrecords : true,
        shrinkToFit: true,//自适应
		multiselect : true,
		multiselectWidth : 25,
		sortable : true,
		sortorder : "desc",
		toolbar : [false, "top"],
		
		gridComplete:function(){ 
			var ids=jQuery("#list").jqGrid('getDataIDs'); 
			for(var i=0; i<ids.length; i++){ 
				var id=ids[i]; 
				modify ='<a style="cursor:pointer;" onclick="Modify('+id+')">编辑</a>';
				jQuery("#list").jqGrid('setRowData', ids[i], { modify: modify}); 
			}
		},
		loadComplete : function(data) { // 完成服务器请求后，回调函数
			if (data.records == 0) { // 如果没有记录返回，追加提示信息，删除按钮不可用
				$("p").appendTo($("#list")).addClass("nodata").html('找不到相关数据！');
				$("#del_btn").attr("disabled", true);
			} else { // 否则，删除提示，删除按钮可用
				$("p.nodata").remove();
				$("#del_btn").removeAttr("disabled");
			}
		},
		ondblClickRow:function(rowid,iRow,iCol,e){
			Modify(rowid);
		}
	}).navGrid('#pager', {
		edit : false,
		add : false,
		del : false,
		search : false,
		refresh : false
	});

//	$("#first_pager").html("首页");
	$("#prev_pager").html("上页");
	$("#next_pager").html("下页");
//	$("#last_pager").html("尾页");
	
  //删除操作
	$("#del_btn").click(function() {
		var ids = $("#list").jqGrid('getGridParam', 'selarrrow');
		if (ids=="") {
			art.dialog('请选择要删除的项!', function(){});//artdialog的弹窗
		} else {
			if (confirm("您是否确认删除？")) {
				$.ajax({
					type : "POST",
					url : host + "/media/delete",
					data : "ids=" + ids,
					error : function() {
						art.dialog('请求失败...', function(){});
					},
					success : function(msg) {
						$("#list").trigger("reloadGrid");
					}
				});
			}
		}
	});
	
	$("#upd_btn").click(function() {
		var ids = $("#list").jqGrid('getGridParam', 'selarrrow');
		if (ids=="") {
			art.dialog('请选择要修改的项!', function(){});//artdialog的弹窗
		}else if(ids.length>1){
			art.dialog('一次只能修改一个', function(){});
		} else{
			Modify(ids);
		}
	});
	
	
});

//修改
function Modify(id){
 		art.dialog({
	  			id:'modifydialog',
	  			padding: '0',
				title: '修改信息',
				content: "<iframe width='610' height='530'  id='Preview'  scrolling='no' src='media/updatePage?id="+id+"' ></iframe>",
				width: 610,
				height:530,			 
				fixed: true,
				resize: false,
				drag: false,
				lock: true
			});
	
//	var myDialog = art.dialog({
//			id:'modifydialog',
//  			padding: '0',
//			title: '修改信息',
//			width: 610,
//			height:530,			 
//			fixed: true,
//			resize: false,
//			drag: false,
//			lock: true}	
//	);// 初始化一个带有loading图标的空对话框
//	jQuery.ajax({
//	    url: 'media/updatePage?id='+id,
//	    success: function (data) {
//	        myDialog.content(data);// 填充对话框内容
//	    }
//	});
	
}


//关闭修改弹窗
function closeupdatedialog(){
	art.dialog({id: 'modifydialog'}).close();
}

function reldate(){
	$("#list").trigger("reloadGrid");
}

function sub(form){
	var key=document.getElementById('keyword').value;
	var str=key.replace(/[ ]/g,"");
	if(str==""){
		art.dialog('关键字不能为空', function(){});
		return false;
	}else{
		this.form.submit();
	}
}
