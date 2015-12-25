/**
 * 
 */
package com.lzy.block.console.utils;

import java.math.BigInteger;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import com.lzy.block.core.redis.IRedisCacheClient;

/**
 * @author 李志勇
 *
 */
public class TokenHelper
{
     
    /**
     * 保存token值的默认命名空间
     */
    public static final String TOKEN_NAMESPACE = "blok.tokens";
     
    /**
     * 持有token名称的字段名
     */
    public static final String TOKEN_NAME_FIELD = "blok.token.name";
    public static final String TOKEN_VALUE_FIELD = "blok.token.value";
    
    private static final Logger LOG = Logger.getLogger(TokenHelper.class);
    private static final Random RANDOM = new Random();
     
    private static IRedisCacheClient redisCacheClient;// 缓存调用,代替session,支持分布式
     
    public static void setRedisCacheClient(IRedisCacheClient redisCacheClient)
    {
        TokenHelper.redisCacheClient = redisCacheClient;
    }
     
    /**
     * 使用随机字串作为token名字保存token
     * 
     * @param request
     * @return token
     */
    public static String setToken(HttpServletRequest request)
    {
        return setToken(request, generateGUID());
    }
     
    /**
     * 使用给定的字串作为token名字保存token
     * 
     * @param request
     * @param tokenName
     * @return token
     */
    private static String setToken(HttpServletRequest request, String tokenName)
    {
        String token = generateGUID();
        setCacheToken(request, tokenName, token);
        return token;
    }
     
    /**
     * 保存一个给定名字和值的token
     * 
     * @param request
     * @param tokenName
     * @param token
     */
    private static void setCacheToken(HttpServletRequest request, String tokenName, String token)
    {
        try
        {
            String tokenName0 = buildTokenCacheAttributeName(tokenName);
            redisCacheClient.setKey(tokenName0, token);
            request.setAttribute("tokenName", tokenName0);
            request.setAttribute("tokenValue", token);
        }
        catch(IllegalStateException e)
        {
            String msg = "Error creating HttpSession due response is commited to client. You can use the CreateSessionInterceptor or create the HttpSession from your action before the result is rendered to the client: " + e.getMessage();
            LOG.error(msg, e);
            throw new IllegalArgumentException(msg);
        }
    }
     
    /**
     * 构建一个基于token名字的带有命名空间为前缀的token名字
     * 
     * @param tokenName
     * @return the name space prefixed session token name
     */
    public static String buildTokenCacheAttributeName(String tokenName)
    {
        return TOKEN_NAMESPACE + "." + tokenName;
    }
     
    /**
     * 验证当前请求参数中的token是否合法，如果合法的token出现就会删除它，它不会再次成功合法的token
     * 
     * @return 验证结果
     */
    public static boolean validToken(HttpServletRequest request)
    {
        String tokenName = request.getParameter(TOKEN_NAME_FIELD);
         
        if(tokenName == null)
        {
            LOG.debug("no token name found -> Invalid token ");
            return false;
        }
         
        String token=request.getParameter(TOKEN_VALUE_FIELD);
         
        if(token == null)
        {
            if(LOG.isDebugEnabled())
            {
                LOG.debug("no token found for token name " + tokenName + " -> Invalid token ");
            }
            return false;
        }
         
        Object cacheToken = redisCacheClient.getKey(tokenName);
        if(cacheToken==null){
        	LOG.debug("no token found for token name " + tokenName + " -> Invalid token ");
        	return false;
        }
        if(!token.equals(cacheToken.toString()))
        {
            LOG.warn("xxx.internal.invalid.token Form token " + token + " does not match the session token " + cacheToken + ".");
            return false;
        }
              
        return true;
    }
     
    public static String generateGUID()
    {
        return new BigInteger(165, RANDOM).toString(36).toUpperCase();
    }
    
    //删除缓存
    public static void removeTokenCache(HttpServletRequest request){
    	String tokenName = request.getParameter(TOKEN_NAME_FIELD);
    	redisCacheClient.removeKey(tokenName);
    }
}
