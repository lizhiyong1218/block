package com.lzy.block.console.filters.shiro;

import org.apache.log4j.Logger;
import org.apache.shiro.session.Session;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import com.lzy.block.api.constant.shiro.ShiroConstants;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 
 * @ClassName: ForceLogoutFilter
 * @Description: 强制退出过滤器
 * @author 李志勇
 * @date 2015年4月8日 下午4:33:56
 * 
 */
public class ForceLogoutFilter extends AccessControlFilter {

	private static Logger logger=Logger.getLogger(ForceLogoutFilter.class.getName());

	@Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        Session session = getSubject(request, response).getSession(false);
        if(session == null) {
            return true;
        }
        return session.getAttribute(ShiroConstants.SESSION_FORCE_LOGOUT_KEY) == null;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        try {
            getSubject(request, response).logout();//强制退出
        } catch (Exception e) {
        	logger.error(e);
        }

        String loginUrl = getLoginUrl() + (getLoginUrl().contains("?") ? "&" : "?") + "forceLogout=1";
        WebUtils.issueRedirect(request, response, loginUrl);
        return false;
    }
}
