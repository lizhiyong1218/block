package com.lzy.block.console.filters.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.springframework.beans.factory.annotation.Autowired;

import com.lzy.block.api.constant.ehcache.EhcacheConstants;
import com.lzy.block.api.constant.shiro.ShiroConstants;
import com.lzy.block.api.model.user.User;
import com.lzy.block.core.service.user.IUserService;

/**
 * 用于根据当前登录用户身份获取User信息放入request；然后就可以通过request获取User。
 * 也可以不用这个过滤器，直接存到用户threadlocal中
 * @ClassName: SysUserFilter
 * @Description: 系统用户过滤器
 * @author 李志勇
 * @date 2015年4月8日 下午4:33:36
 * 
 */
public class SysUserFilter extends PathMatchingFilter {

    @Autowired
    private IUserService userService;
    
    private Cache<String, User> userInfoCache;
    
    public SysUserFilter(CacheManager cacheManager){
    	userInfoCache = cacheManager.getCache("userInfoCache");
    }

    /**
     * 从缓存中读取，如果不存在则从数据库中读取然后加入到缓存中
     */
    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
       String username = (String)SecurityUtils.getSubject().getPrincipal();
        String key=EhcacheConstants.USERNAMEKEYPRE+username;
        User user= userInfoCache.get(key);
        if(user==null){
        	user=userService.getByName(username);
        	userInfoCache.put(key, user);
        } 
        request.setAttribute(ShiroConstants.CURRENT_USER,user );
        return true;
    }
}
