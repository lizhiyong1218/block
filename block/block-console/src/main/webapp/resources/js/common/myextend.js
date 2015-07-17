/**
 * 获取表单中的name:value对
 * 用来使用
 * $('#dictionaryList').datagrid('load',$('#searchForm').serializeJson());
 */
(function($){
	$.fn.serializeJson=function(){
		var serializeObj={};
		$(this.serializeArray()).each(function(){
			serializeObj[this.name]=this.value;
		});
		return serializeObj;
	};
})(jQuery);
