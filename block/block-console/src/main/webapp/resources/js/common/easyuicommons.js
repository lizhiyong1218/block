

	function getSelectField(gridId,field){
		var checkeditems=$(gridId).datagrid('getChecked');
		var fileds="";
		$.each(checkeditems,function(index,item){
			fileds+=item.field+",";
		});
		fileds=fileds.substr(0,fileds.length-1); 
		return fileds;
	}
	 
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
	
	function openMyDialog(url, title) {
	    var dialog= $('<div/>').dialog({
	        href: url,
	        width: 500,
	        height: 400,
	        modal: true,
			collapsible : true,
			minimizable : true,
			maximizable : true,
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
	 
  		 
