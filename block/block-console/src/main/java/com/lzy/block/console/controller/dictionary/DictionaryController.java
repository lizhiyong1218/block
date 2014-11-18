/**  
* @Title: DictionaryController.java
* @Package com.lzy.block.console.controller.dictionary
* @author 李志勇  
* @date 2014年11月18日 上午9:34:16
* @version V1.0  
*/ 
package com.lzy.block.console.controller.dictionary;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lzy.block.api.common.Pagination;
import com.lzy.block.api.constant.common.AvaliableEnum;
import com.lzy.block.api.model.dictionary.DictionaryItemModel;
import com.lzy.block.api.model.dictionary.DictionaryModel;
import com.lzy.block.console.common.ProjectUtil;
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
	
	@ResponseBody
	@RequestMapping(value="/dictionaryList")
	public  Map<String, Object>  dictionaryList(DictionaryModel dictionaryModel,int page,int rows,HttpServletRequest req) {
		logger.debug("Dictionarylist in!!!");
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
	
	
	@ResponseBody
	@RequestMapping("/addDictionary")
	public String addDictionary(DictionaryModel dictionaryModel,String dictionaryItemJsonData,String sessionId) {
		logger.debug("save in"+"===");
		//把json字符串转换成集合对象
		List<DictionaryItemModel> items=new ArrayList<DictionaryItemModel>();
		/*if(!StringUtils.isEmpty(dictionaryItemJsonData)){
			items = JsonUtils.getGson(("yyyy-MM-dd").fromJson(dictionaryItemJsonData, new TypeToken<List<DictionaryItemModel>>() {
			}.getType());
		}*/
//		SimpleReturnVo vo;
		try {
			dictionaryModel.setItems(items);
			dictionaryModel.setCreateBy(ProjectUtil.getOpUser());
			dictionaryModel.setCreateTime(new Date());
			dictionaryService.insert(dictionaryModel);
//			vo = new SimpleReturnVo(this.SUCCESS, "成功");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
//			vo = new SimpleReturnVo(this.FAIL, "异常");
		}
		return null;
	}	
	
	 /**
	 * 删除数据字典(软删除)
	 * @param dictionaryModel
	 * @param sessionId
	 * @return  
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/delDictionary")
	public String delDictionary(DictionaryModel dictionaryModel,String sessionId){
//		SimpleReturnVo vo;
		try {
			dictionaryModel.setIsavailable(AvaliableEnum.DISAVAILABLE.value());
			dictionaryModel.setModifiBy(ProjectUtil.getOpUser());
			dictionaryModel.setModifiTime(new Date());
			dictionaryService.update(dictionaryModel);
//			vo = new SimpleReturnVo(this.SUCCESS, "成功");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
//			vo = new SimpleReturnVo(this.FAIL, "异常");
		}
		return null;
	}
	
	@RequestMapping(value="/editDictionaryPage")
	public ModelAndView editDictionaryPage(int dictionaryId,String sessionId){
		logger.debug("editDictionaryPage in");
//		String bootPath= BaseController.BOOT_PATH;
		DictionaryModel model=new DictionaryModel();
		try {
			model= dictionaryService.getOneById(dictionaryId);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		logger.debug(model);
		ModelMap modelMap=new ModelMap();
		modelMap.addAttribute("dictionaryId", dictionaryId);
		modelMap.addAttribute("dictionaryModel", model);
//		modelMap.addAttribute("bootPath", bootPath);
		return new ModelAndView("/dictionary/dictionaryEdit",modelMap);
	}
	
	@ResponseBody
	@RequestMapping("/editDictionary")
	public String ediDictionary(DictionaryModel dictionaryModel,String sessionId) {
		logger.debug("ediDictionary in"+"===");
//		SimpleReturnVo vo;
		try {
			dictionaryModel.setModifiBy(ProjectUtil.getOpUser());
			dictionaryModel.setModifiTime(new Date());
			dictionaryService.update(dictionaryModel);
//			vo = new SimpleReturnVo(this.SUCCESS, "成功");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
//			vo = new SimpleReturnVo(this.FAIL, "异常");
		}
		return null;
	}	
	

	
	@ResponseBody
	@RequestMapping(value="/getDictionary")
	public DictionaryModel getDictionary(DictionaryModel dictionaryModel,HttpServletRequest req) {
		logger.debug("getDictionary in!!!");
		DictionaryModel model=dictionaryService.selectModel(dictionaryModel); 
	    return model;
	}
	
	
	@ResponseBody
	@RequestMapping(value="/getDictionaryItems")
	public String getDictionaryItems(DictionaryItemModel dictionaryItemModel,HttpServletRequest req) {
		logger.debug("dictionaryItemList in!!!");
		List<DictionaryItemModel> list=new ArrayList<DictionaryItemModel>();
		try {				
			list=dictionaryItemService.getAll(dictionaryItemModel);			
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	    return null;
	}
	
	
	@ResponseBody
	@RequestMapping(value="/dictionaryItemList")
	public String dictionaryItemList(DictionaryItemModel dictionaryModel,PageBounds page,HttpServletRequest req) {
		logger.debug("dictionaryItemList in!!!");
		Pagination<DictionaryItemModel> pm = new Pagination<DictionaryItemModel>();
		try {				
			pm = dictionaryItemService.getPagination(dictionaryModel, page);			
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	    return null;
	}
	
	@RequestMapping(value="/editItemPage")
	public ModelAndView editItemPage(int itemId,String sessionId){
		logger.debug("editItemPage in");
//		String bootPath= BaseController.BOOT_PATH;
		DictionaryItemModel model=new DictionaryItemModel();
		try {
			model= dictionaryItemService.getOneById(itemId);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		logger.debug(model);
		ModelMap modelMap=new ModelMap();
		modelMap.addAttribute("dictionaryItemModel", model);
//		modelMap.addAttribute("bootPath", bootPath);
		return new ModelAndView("/dictionary/dictionaryItemEdit",modelMap);
	}
	
	
	@RequestMapping(value="/addItemPage")
	public ModelAndView addItemPage(String dictionaryValue,String sessionId){
		ModelMap modelMap=new ModelMap();
		modelMap.addAttribute("dictionaryValue", dictionaryValue);
		return new ModelAndView("/dictionary/dictionaryItemEdit",modelMap);
	}
	
	
	@ResponseBody
	@RequestMapping("/editItem")
	public String editItem(DictionaryItemModel dictionaryItemModel,String sessionId) {
		logger.debug("ediItem in"+"===");
//		SimpleReturnVo vo;
		try {
			dictionaryItemModel.setModifyBy(ProjectUtil.getOpUser());;
			dictionaryItemModel.setModifyTime(new Date());
			dictionaryItemService.update(dictionaryItemModel);
//			vo = new SimpleReturnVo(this.SUCCESS, "成功");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
//			vo = new SimpleReturnVo(this.FAIL, "异常");
		}
		return null;
	}	
	
	@ResponseBody
	@RequestMapping("/addItem")
	public String addItem(String refDictionaryValue, DictionaryItemModel dictionaryItemModel,String sessionId) {
		logger.debug("addItem in"+"===");
//		SimpleReturnVo vo;
		try {
			dictionaryItemModel.setCreateBy(ProjectUtil.getOpUser());
			dictionaryItemModel.setCreateTime(new Date());
			dictionaryItemModel.setDictionaryValue(refDictionaryValue);
			dictionaryItemService.insert(dictionaryItemModel);
//			vo = new SimpleReturnVo(this.SUCCESS, "成功");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
//			vo = new SimpleReturnVo(this.FAIL, "异常");
		}
		return null;
	}
	
	
	
	 /**
	 * 删除数据字典项(软删除)
	 * @param dictionaryModel
	 * @param sessionId
	 * @return  
	 * @throws
	 */ 
	@ResponseBody
	@RequestMapping("/delDictionaryItem")
	public String delDictionaryItem(DictionaryItemModel dictionaryItemModel,String sessionId){
//		SimpleReturnVo vo;
		try {
			dictionaryItemModel.setIsavailable(AvaliableEnum.DISAVAILABLE.value());
			dictionaryItemModel.setModifyBy(ProjectUtil.getOpUser());
			dictionaryItemModel.setModifyTime(new Date());
			dictionaryItemService.update(dictionaryItemModel);
//			vo = new SimpleReturnVo(this.SUCCESS, "成功");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
//			vo = new SimpleReturnVo(this.FAIL, "异常");
		}
		return null;
	}
}
