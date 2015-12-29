package com.lzy.block.core.shiro;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * 
 * @ClassName: RetryLimitHashedCredentialsMatcher
 * @Description: 凭证匹配器 
 *  登录次数限制，使用了ehcache缓存
 * @author 李志勇
 * @date 2015年3月18日 下午2:01:47
 * 
 */
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {

    private Cache<String, AtomicInteger> passwordRetryCache;

    public RetryLimitHashedCredentialsMatcher(CacheManager cacheManager) {
        passwordRetryCache = cacheManager.getCache("passwordRetryCache");
    }

    /**
     * 验证，失败超过5次则抛出异常
     */
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
    	 String username = (String)token.getPrincipal();
    	 String key=username+"errorcount";
    	 
         //retry count + 1
         AtomicInteger retryCount = passwordRetryCache.get(key);
         if(retryCount == null) {
             retryCount = new AtomicInteger(0);
             passwordRetryCache.put(key, retryCount);
         }
         if(retryCount.incrementAndGet() > 5) {
             //if retry count > 5 throw
             throw new ExcessiveAttemptsException();
         }

         boolean matches = super.doCredentialsMatch(token, info);
         if(matches) {
             //clear retry count
             passwordRetryCache.remove(key);
         }
         return matches;
    }
    
    public static void main(String[] args) {
    	String username="admin";
    	String str="111111";
        String password =  new SimpleHash("md5",str,ByteSource.Util.bytes(username),2).toHex();  
        System.err.println(password);
	}
    
    
}
