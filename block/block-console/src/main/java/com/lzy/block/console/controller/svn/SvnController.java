package com.lzy.block.console.controller.svn;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lzy.block.api.common.Pagination;
import com.lzy.block.api.model.svn.SvnLogModel;
import com.lzy.block.core.utils.svn.SvnUtil;

@Controller
@RequestMapping("/svn")
public class SvnController {
	
	private static Logger logger=Logger.getLogger(SvnController.class);
	
	@RequestMapping(value="svnLogList")
	@ResponseBody
	public Map<String, Object> taskList(int page,int rows){
		ModelMap map=new ModelMap();
		Pagination<SvnLogModel> pm = SvnUtil.getSVNLogPagination(page, rows);
		map.put("rows", pm.getRecordList());  
		map.put("total", pm.getRecordCount()); 
		return map;
	}
	
}
