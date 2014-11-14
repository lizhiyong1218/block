

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
	 
  		 
