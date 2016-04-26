//		$(document).ready(function() {
//			initList();
//		});

		$(function() {
	        $('#dg').datagrid({
				striped: true,//隔行变色
				collapsible:true,
				loadMsg:'数据装载中......',
	            rownumbers : true,
	            fit : true,
	            pagination : true,
	            singleSelect : true,
	            border : false,
	            pageSize : 20,
	            pageList : [20,50,100,500,1000],
	            nowrap : false,
	            url : basePath+'/svn/svnLogList.do',
	            columns : [ 
		             [ 
			            {
			                field : 'revision',
			                title : '修订版本',
			                halign: 'center',
			                align : 'right',
			                width : 60
			            }, {
			                field : 'author',
			                title : '提交者',
			                halign: 'center',
			                width : 60
			            }, {
			                field : 'date',
			                title : '日期',
			                halign: 'center',
			                width : 150,
			            }, {
			                field : 'entryCount',
			                title : '影响文件',
			                halign: 'center',
			                width : 60,
			            },
			            {
			                field : 'message',
			                title : '注释信息',
			                halign: 'center',
			                width : 350,
			                formatter : function(value,rowData) {    
			                    if(value != undefined ) {
			                        return value.replaceAll("\n", "<br />");
			                    }
			                }
			            }
		            ] 
	            ],
	              onClickRow: function (index, row) {
	                  value=row.logEntry;
	                  if(value != undefined ) {
	                    value=value.replaceAll("\n", "<br />");
	                    $("#logfile").html(value);
	                }
	              }
	        });
	        initPager("#dg");
	    });
	   
		//重写替换方法
	   String.prototype.replaceAll = function(reallyDo, replaceWith, ignoreCase) { 
	        if (!RegExp.prototype.isPrototypeOf(reallyDo)) { 
	        	 return this.replace(new RegExp(reallyDo, (ignoreCase ? "gi": "g")), replaceWith); 
	        } else { 
	        	return this.replace(reallyDo, replaceWith); 
	        } 
	  }
		