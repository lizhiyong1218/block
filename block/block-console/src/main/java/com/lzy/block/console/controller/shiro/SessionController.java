package com.lzy.block.console.controller.shiro;

import org.apache.log4j.Logger;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lzy.block.api.constant.shiro.ShiroConstants;
import com.lzy.block.api.model.dictionary.DictionaryModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 
 * @ClassName: SessionController
 * @Description: 会话管理
 * @author 李志勇
 * @date 2015年4月17日 下午2:28:11
 * 
 */

//@RequiresPermissions("session:*")
@Controller
@RequestMapping("/session")
public class SessionController {
	
	private static Logger logger=Logger.getLogger(SessionController.class.getName());
	
    @Autowired
    private SessionDAO sessionDAO;
    @RequestMapping()
    public String list(Model model) {
        Collection<Session> sessions =  sessionDAO.getActiveSessions();
        List<com.lzy.block.api.model.shiro.Session> list=new ArrayList<com.lzy.block.api.model.shiro.Session>();
        com.lzy.block.api.model.shiro.Session temp;
        for (Session session : sessions) {
			temp=new com.lzy.block.api.model.shiro.Session();
			temp.setSessionId(session.getId().toString());
			temp.setHost(session.getHost());
			temp.setLastAccessTime(session.getLastAccessTime());
			list.add(temp);
		}
        model.addAttribute("sessions", list);
        model.addAttribute("sessionCount", list.size());
        return "sessions/list";
    }
    
	@ResponseBody
	@RequestMapping(value="/sessionList")
	public  Map<String, Object>  sessionList(DictionaryModel dictionaryModel,int page,int rows) {
		Collection<Session> sessions =  sessionDAO.getActiveSessions(); 
		ModelMap map=new ModelMap();
		/*List<com.lzy.block.api.model.shiro.Session> list=new ArrayList<com.lzy.block.api.model.shiro.Session>();
        com.lzy.block.api.model.shiro.Session temp;
        for (Session session : sessions) {
			temp=new com.lzy.block.api.model.shiro.Session();
			temp.setSessionId(session.getId().toString());
			temp.setHost(session.getHost());
			temp.setLastAccessTime(session.getLastAccessTime());
			list.add(temp);
		}*/
		map.put("rows", sessions);  
		map.put("total", sessions.size()); 
		return map;
	}

    @RequestMapping("/{sessionId}/forceLogout")
    public String forceLogout(
            @PathVariable("sessionId") String sessionId, RedirectAttributes redirectAttributes) {
        try {
            Session session = sessionDAO.readSession(sessionId);
            if(session != null) {
                session.setAttribute(ShiroConstants.SESSION_FORCE_LOGOUT_KEY, Boolean.TRUE);
            }
        } catch (Exception e) {
        	logger.error("强制退出异常:",e);
        	
        }
        redirectAttributes.addFlashAttribute("msg", "强制退出成功！");
        return "redirect:/sessions";
    }

}
