
	/**
	 * 获得选中面板
	 * @param gridId
	 * @param field
	 * @returns {String}
	 */
	function getSelectField(gridId,field){
		var checkeditems=$(gridId).datagrid('getChecked');
		var fileds="";
		$.each(checkeditems,function(index,item){
			fileds+=item.field+",";
		});
		fileds=fileds.substr(0,fileds.length-1); 
		return fileds;
	}
	
	/**
	 * 新增面板
	 * @param tabsIdName
	 * @param newTabTitle
	 * @param newTabUrl
	 */
	function tab_add(tabsIdName,newTabTitle,newTabUrl){
		var tabsId= "#"+tabsIdName;
			if ($(tabsId).tabs('exists',newTabTitle)){
					$(tabsId).tabs('select', newTabTitle);
				} else {
					$(tabsId).tabs('add',{
					    title: newTabTitle, 
						href:newTabUrl,
						closable:true,
						tools:[{ 
							iconCls:'icon-mini-refresh', 
							handler:function(){ 
								tab_refresh(tabsId,newTabUrl);
							}
						}]
					});
				}
  		}
	
	/**
	 * 刷新面板
	 * @param tabsId
	 * @param newTabUrl
	 */ 
	function tab_refresh(tabsId,newTabUrl){
		 var tab = $(tabsId).tabs('getSelected'); 
		    $(tabsId).tabs('update',{
		        tab: tab,
		        options: {
		            href: newTabUrl
		        }
		    });
		    tab.panel('refresh');
	}
	
	/**
	 * 弹窗
	 * @param url  地址
	 * @param title 标题
	 * @returns
	 */
	function openMyDialog(url, title) {
	    var dialog= $('<div/>').dialog({
	        href: url,
	        width: 500,
	        height: 400,
	        modal: true,
			collapsible : false,
			minimizable : false,
			maximizable : false,
	        title: title,
			buttons : [ 
			
			/*{
				text : '提交',
				iconCls : 'icon-ok',
				handler : function() {
					alert("ok");
				}
			}, {
				text : '取消',
				handler : function() {
					alert("cancel");
				}
			},*/
			{
				text : '关闭',
				handler : function() {
					 var d = $(this).closest('.window-body');
		             d.dialog('destroy');
				}
			}],
	        onClose: function () {
	            $(this).dialog('destroy');
	        }
	    });
	    
	    return dialog;
	}
	
	/**
	 * 关闭弹出窗
	 * @param dialog  弹出窗对象
	 */
	function closeMyDialog(dialog){
		dialog.dialog('destroy');
	}
	
	/**
	 * 初始化分页
	 * @param tabId  "#rmaList"
	 */
	function initPager(tabId){
		var pager = $(tabId).datagrid('getPager');  
		pager.pagination({
			beforePageText: '第',              
			afterPageText: '页    共 {pages} 页',                    
			displayMsg: '当前显示 {from} - {to} 条   共 {total} 条'
		});
	}
	
	/**
	 * 消息提示框
	 * @param msg
	 */
	function showInfo(msg){
		$.messager.show({
			title:'消息',
			msg: msg,
			timeout:3000,
			showType:'slide'
		});
	}
	 
  		 
