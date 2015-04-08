/**  
* @Title: DictionaryController.java
* @Package com.lzy.block.console.controller.dictionary
* @author 李志勇  
* @date 2014年11月18日 上午9:34:16
* @version V1.0  
*/ 
package com.lzy.block.console.controller.dictionary;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lzy.block.api.common.Pagination;
import com.lzy.block.api.constant.common.ResultStatus;
import com.lzy.block.api.model.dictionary.DictionaryItemModel;
import com.lzy.block.api.model.dictionary.DictionaryModel;
import com.lzy.block.api.vo.ResultVo;
import com.lzy.block.console.common.ProjectUtil;
import com.lzy.block.core.common.JsonUtils;
import com.lzy.block.core.service.dictionary.IDictionaryItemService;
import com.lzy.block.core.service.dictionary.IDictionaryService;

/**
 * @ClassName: DictionaryController
 * @Description: 数据字典 
 * @author 李志勇
 * @date 2014年11月18日 上午9:34:16
 *
 */
@Controller
@RequestMapping("/dictionary")
public class DictionaryController  {
	private static Logger logger = Logger.getLogger(DictionaryController.class.getName());	
	@Resource
	private IDictionaryService dictionaryService;
	@Resource
	private IDictionaryItemService dictionaryItemService;
	
	/**
	 * 
	* @Title: dictionaryList
	* @Description: 数据字典列表
	* @param dictionaryModel
	* @param page
	* @param rows
	* @param req
	* @return:     
	* Map<String,Object>    
	* @throws
	 */
	@ResponseBody
	@RequestMapping(value="/dictionaryList")
	public  Map<String, Object>  dictionaryList(DictionaryModel dictionaryModel,int page,int rows) {
		Pagination<DictionaryModel> pm = new Pagination<DictionaryModel>();
		try {				
			PageBounds pageBounds=new PageBounds(page, rows);
			pm = dictionaryService.getPagination (dictionaryModel, pageBounds);			
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		ModelMap map=new ModelMap();
		map.put("rows", pm.getRecordList());  
		map.put("total", pm.getRecordCount()); 
		return map;
	}
	
	
	/**
	* 
	* @Title: addDictionary
	* @Description: 添加数据字典
	* @param dictionaryModel
	* @param dictionaryItemJsonData
	* @return:     
	* ResultVo    
	* @throws
	 */
	@ResponseBody
	@RequestMapping(value="/addDictionary",produces={"application/json;charset=UTF-8"})
	public ResultVo addDictionary(DictionaryModel dictionaryModel,String dictionaryItemJsonData) {
		logger.info("新增数据字典"+"==="+dictionaryItemJsonData);
		ResultVo res=new ResultVo();
		//把json字符串转换成集合对象
		List<DictionaryItemModel> items=new ArrayList<DictionaryItemModel>();
		TypeReference<List<DictionaryItemModel>> t=new TypeReference<List<DictionaryItemModel>>() {
		};
		try {
			items=JsonUtils.toJavaBeanList(dictionaryItemJsonData, t);
		} catch (IOException e) {
			res.setStatus(ResultStatus.FAILURE.value());
			res.setMessage(e.getMessage());
			return res;
		}
		try {
			dictionaryModel.setItems(items);
			dictionaryModel.setCreateBy(ProjectUtil.getOpUser());
			dictionaryModel.setCreateTime(new Date());
			dictionaryService.insert(dictionaryModel);
			res.setStatus(ResultStatus.SUCCESS.value());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			res.setStatus(ResultStatus.FAILURE.value());
			res.setMessage(e.getMessage());
		}
		return res;
	}	
	
	
	/**
	 *  
	* @Title: delDictionary
	* @Description: 删除数据字典(物理删除)
	* @param dictionaryModel
	* @return:     
	* String    
	* @throws
	 */
	@ResponseBody
	@RequestMapping("/delDictionary")
	public ResultVo delDictionary(@RequestParam(value = "dictionaryId", required = true)Integer dictionaryId,
			@RequestParam(value = "dictionaryValue", required = true)String dictionaryValue){
		logger.info("删除数据字典:"+dictionaryValue);
		ResultVo res=new ResultVo();
		try {
			dictionaryService.deleteDictionary(dictionaryId, dictionaryValue);
			res.setStatus(ResultStatus.SUCCESS.value());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			res.setStatus(ResultStatus.FAILURE.value());
			res.setMessage(e.getMessage());
		}
		return res;
	}
	
	/**
	 * 
	* @Title: dictionaryEditPage
	* @Description: 修改数据字典页面
	* @param dictionaryId
	* @return:     
	* ModelAndView    
	* @throws
	 */
	@RequestMapping(value="/dictionaryEditPage")
	public ModelAndView dictionaryEditPage(int dictionaryId){
		DictionaryModel model=new DictionaryModel();
		try {
			model= dictionaryService.getOneById(dictionaryId);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		logger.info(model);
		ModelMap modelMap=new ModelMap();
		modelMap.addAttribute("dictionaryId", dictionaryId);
		modelMap.addAttribute("dictionaryModel", model);
		return new ModelAndView("/dictionary/dictionaryEdit",modelMap);
	}
	
	/**
	 * 
	* @Title: ediDictionary
	* @Description: 修改数据字典
	* @param dictionaryModel
	* @return:     
	* String    
	* @throws
	 */
	@ResponseBody
	@RequestMapping("/editDictionary")
	public ResultVo ediDictionary(DictionaryModel dictionaryModel) {
		logger.info("修改数据字典 "+dictionaryModel);
		ResultVo res=new ResultVo();
		try {
			dictionaryModel.setModifiBy(ProjectUtil.getOpUser());
			dictionaryModel.setModifiTime(new Date());
			dictionaryService.update(dictionaryModel);
			res.setStatus(ResultStatus.SUCCESS.value());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			res.setStatus(ResultStatus.FAILURE.value());
			res.setMessage(e.getMessage());
		}
		return res;
	}	
	

	/**
	 * 
	* @Title: getDictionary
	* @Description: 查询数据字典
	* @param dictionaryModel
	* @return:     
	* DictionaryModel    
	* @throws
	 */
	@ResponseBody
	@RequestMapping(value="/getDictionary")
	public DictionaryModel getDictionary(DictionaryModel dictionaryModel) {
		logger.info("getDictionary in!!!");
		DictionaryModel model=dictionaryService.getOneByModel(dictionaryModel); 
	    return model;
	}
	
	
	/**
	 * 
	* @Title: existDictionary
	* @Description: 验证是否存在数据字典
	* @param dictionaryModel
	* @return:     
	* boolean    
	* @throws
	 */
	@ResponseBody
	@RequestMapping(value="/existDictionary")
	public boolean existDictionary(DictionaryModel dictionaryModel) {
		DictionaryModel model=dictionaryService.getOneByModel(dictionaryModel);
		if(model!=null){
			return true;
		}
	    return false;
	}
	
	/**
	 * 
	* @Title: getDictionaryItems
	* @Description: 获得数据字典集合
	* @param dictionaryItemModel
	* @return:     
	* List<DictionaryItemModel>    
	* @throws
	 */
	@ResponseBody
	@RequestMapping(value="/getDictionaryItems")
	public List<DictionaryItemModel> getDictionaryItems(DictionaryItemModel dictionaryItemModel) {
		List<DictionaryItemModel> list=new ArrayList<DictionaryItemModel>();
		try {				
			list=dictionaryItemService.getAll(dictionaryItemModel);			
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	    return list;
	}
	
	/**
	 * 
	* @Title: dictionaryItemList
	* @Description: 数据字典项列表
	* @param dictionaryItemModel
	* @param page
	* @param req
	* @return:     
	* String    
	* @throws
	 */
	@ResponseBody
	@RequestMapping(value="/dictionaryItemList")
	public Map<String, Object> dictionaryItemList(DictionaryItemModel dictionaryItemModel,int page,int rows,HttpServletRequest req) {
		Pagination<DictionaryItemModel> pm = new Pagination<DictionaryItemModel>();
		try {				
			PageBounds pageBounds=new PageBounds(page, rows);
			pm = dictionaryItemService.getPagination(dictionaryItemModel, pageBounds);			
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		ModelMap map=new ModelMap();
		map.put("rows", pm.getRecordList());  
		map.put("total", pm.getRecordCount()); 
		return map;
	}
	
	/**
	 * 
	* @Title: editItemPage
	* @Description: 修改数据字典页面
	* @param itemId 数据字典id
	* @return:     
	* ModelAndView    
	* @throws
	 */
	@RequestMapping(value="/editItemPage")
	public ModelAndView editItemPage(@RequestParam(value = "itemId", required = true)int itemId){
		logger.info("editItemPage in:"+itemId);
		DictionaryItemModel model=new DictionaryItemModel();
		try {
			model= dictionaryItemService.getOneById(itemId);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		ModelMap modelMap=new ModelMap();
		modelMap.addAttribute("dictionaryItemModel", model);
		return new ModelAndView("/dictionary/dictionaryItemEdit",modelMap);
	}
	
	
	/**
	 * 
	* @Title: addItemPage
	* @Description: 数据字典项添加页面
	* @param dictionaryValue 数据字典值
	* @return:     
	* ModelAndView    
	* @throws
	 */
	@RequestMapping(value="/addItemPage")
	public ModelAndView addItemPage(String dictionaryValue){
		ModelMap modelMap=new ModelMap();
		modelMap.addAttribute("dictionaryValue", dictionaryValue);
		return new ModelAndView("/dictionary/dictionaryItemEdit",modelMap);
	}
	
	/**
	 * 
	* @Title: editItem
	* @Description: 修改数据字典项
	* @param dictionaryItemModel
	* @return:     
	* String    
	* @throws
	 */
	@ResponseBody
	@RequestMapping("/editItem")
	public ResultVo editItem(DictionaryItemModel dictionaryItemModel) {
		logger.info("修改数据字典项");
		ResultVo res=new ResultVo();
		try {
			dictionaryItemModel.setModifyBy(ProjectUtil.getOpUser());;
			dictionaryItemModel.setModifyTime(new Date());
			logger.info(dictionaryItemModel);
			dictionaryItemService.update(dictionaryItemModel);
			res.setStatus(ResultStatus.SUCCESS.value());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			res.setStatus(ResultStatus.FAILURE.value());
			res.setMessage(e.getMessage());
		}
		return res;
	}	
	
	/**
	 * 
	* @Title: addItem
	* @Description: 新增数据字典项
	* @param refDictionaryValue 数据字典值
	* @param dictionaryItemModel 
	* @return:     
	* String    
	* @throws
	 */
	@ResponseBody
	@RequestMapping("/addItem")
	public ResultVo addItem(@RequestParam(value = "refDictionaryValue", required = true)String refDictionaryValue, DictionaryItemModel dictionaryItemModel) {
		logger.info("新增数据字典项:"+refDictionaryValue);
		ResultVo resultVo=new ResultVo();
		try {
			dictionaryItemModel.setCreateBy(ProjectUtil.getOpUser());
			dictionaryItemModel.setCreateTime(new Date());
			dictionaryItemModel.setDictionaryValue(refDictionaryValue);
			dictionaryItemService.insert(dictionaryItemModel);
			resultVo.setStatus(ResultStatus.SUCCESS.value());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			resultVo.setStatus(ResultStatus.FAILURE.value());
			resultVo.setMessage(e.getMessage());
		}
		return resultVo;
	}
	
	
	/**
	 * 
	* @Title: delDictionaryItem
	* @Description: 删除数据字典项 (物理删除)
	* @param dictionaryItemModel
	* @return:     
	* String    
	* @throws
	 */
	@ResponseBody
	@RequestMapping("/delDictionaryItem")
	public ResultVo delDictionaryItem(@RequestParam(value = "itemId", required = true)Integer itemId){
		logger.info("删除数据字典项:"+itemId);
		ResultVo resultVo=new ResultVo();
		try {
			dictionaryItemService.deleteById(itemId);
			resultVo.setStatus(ResultStatus.SUCCESS.value());
		} catch (Exception e) {
			resultVo.setStatus(ResultStatus.FAILURE.value());
			resultVo.setMessage(e.getMessage());
			logger.error(e.getMessage(),e);
		}
		return resultVo;
	}
}
