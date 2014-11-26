 
	 $(function(){
			initArticleList();
	});

	/*查询*/
	function doSearch(){
		$('#articleList').datagrid('load',$('#searchForm').serializeJson());
	}

	//重置
	function doReset(){
		$('#searchForm').form('reset');
	}
 
	/**
	 * 数据字典
	 */
	function initArticleList(){
		 $('#articleList').datagrid({
		 url: basePath+'/article/articleList.do',
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
		 frozenColumns: [
            [
                {
                    title:'blogId',
                    field:'blogId',
                    checkbox: true
                }
            ]
        ],
		 columns: [
		     [
		         {
		             title: '标题',
		             field: 'blogTitle',
		             width: 120
		         },
		         {
		             title: '类型',
		             field: 'blogType',
		             width: 100
		         },
		         {
		             title: '作者',
		             field: 'authorName',
		             width: 100
		         },
		         {
		             title: '状态',
		             field: 'blogStatus',
		             width: 100
		         },
		         {
		             title: '描述',
		             field: 'blogDesc',
		             width: 100
		         },
		         {
		             title: '封面',
		             field: 'cover',
		             width: 100,
		             align:'center',
		             formatter:imgformatter
		         },
		         {
		             title: '是否热门',
		             field: 'ishot',
		             width: 100
		         } ,
		         {
		             title: '是否推荐',
		             field: 'isrecommend',
		             width: 100
		         } ,
		         {
		             title: '发布时间',
		             field: 'pubTime',
		             width: 100
		         } ,
		         {
		             title: '创建人',
		             field: 'createBy',
		             width: 100
		         } ,
		         {
		             title: '创建时间',
		             field: 'createTime',
		             width: 100
		         } 
		     ]
		 ] 
		});
		
		initPager("#articleList");
	}
	
	function imgformatter(value,row,index){
		console.log(value);
		if(value!=null&&value.length>0){
			return "<img src='"+value+"' style='width:60px;height:60px'/>";
		}
	} 
