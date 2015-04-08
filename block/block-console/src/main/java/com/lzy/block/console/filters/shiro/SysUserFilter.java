package com.lzy.block.console.filters.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.springframework.beans.factory.annotation.Autowired;

import com.lzy.block.api.constant.shiro.ShiroConstants;
import com.lzy.block.core.service.user.IUserService;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 
 * @ClassName: SysUserFilter
 * @Description: 系统用户过滤器
 * @author 李志勇
 * @date 2015年4月8日 下午4:33:36
 * 
 */
public class SysUserFilter extends PathMatchingFilter {

    @Autowired
    private IUserService userService;

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {

        String username = (String)SecurityUtils.getSubject().getPrincipal();
        request.setAttribute(ShiroConstants.CURRENT_USER, userService.getByName(username));
        return true;
    }
}
