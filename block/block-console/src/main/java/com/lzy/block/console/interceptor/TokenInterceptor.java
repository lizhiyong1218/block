package com.lzy.block.console.interceptor;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.lzy.block.console.utils.TokenHelper;
import com.lzy.block.core.common.JsonUtils;
import com.lzy.block.core.redis.IRedisCacheClient;
 
/**
 * 重复提交拦截器
 * @see TokenHelper
 */
public class TokenInterceptor extends HandlerInterceptorAdapter
{
     
    private static Logger log = Logger.getLogger(TokenInterceptor.class);
    private static Map<String , String> viewUrls = new HashMap<String , String>();
    private static Map<String , String> actionUrls = new HashMap<String , String>();
    private Object clock = new Object();
     
    static
    {
    	//需要生成token的地址
        viewUrls.put("/block-console/token/tokenTest", "GET");
        //需要验证token的地址 
        actionUrls.put("/block-console/token/subform", "POST");
    }
    {
    	IRedisCacheClient redisCacheClient= (IRedisCacheClient) ContextLoader.getCurrentWebApplicationContext().getBean("redisCacheClient");
        TokenHelper.setRedisCacheClient(redisCacheClient);
    }
     
    /**
     * 拦截方法，添加or验证token
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        String url = request.getRequestURI();
        String method = request.getMethod();
        //需要生成token
        if(viewUrls.keySet().contains(url) && ((viewUrls.get(url)) == null || viewUrls.get(url).equals(method)))
        {
            TokenHelper.setToken(request);
            return true;
        }
        //需要验证token
        else if(actionUrls.keySet().contains(url) && ((actionUrls.get(url)) == null || actionUrls.get(url).equals(method)))
        {
            log.debug("开始进行防重复提交token验证");
            return handleToken(request, response, handler);
        }
        return true;
    }
     
    protected boolean handleToken(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        synchronized(clock)
        {
            if(!TokenHelper.validToken(request))
            {
            	log.debug("未通过验证...");
                return handleInvalidToken(request, response, handler);
            }
        }
        log.debug("通过验证...");
        return handleValidToken(request, response, handler);
    }
     
    /**
     * 当出现一个非法令牌时调用
     */
    protected boolean handleInvalidToken(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        Map<String , Object> data = new HashMap<String , Object>();
        data.put("flag", 0);
        data.put("msg", "do not submit again !");
        try
        {
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print(JsonUtils.toJsonString(data));
        }
        finally
        {
            response.getWriter().close();
        }
        return false;
    }
     
    /**
     * 当发现一个合法令牌时调用.
     */
    protected boolean handleValidToken(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
    	TokenHelper.removeTokenCache(request);//删除缓存里面的token,重复提交时便找不到
        return true;
    }
     
}
