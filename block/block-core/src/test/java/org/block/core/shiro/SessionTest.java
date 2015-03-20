package org.block.core.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.ThreadContext;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @ClassName: SessionTest
 * @Description: 测试session
 * @author 李志勇
 * @date 2015年3月19日 下午3:45:26
 * 
 */
public class SessionTest{

    @Test
    public void testGetSession() throws Exception{
        login("classpath:shiro.ini", "zhang", "123");
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();//获取会话

        System.out.println(session.getId());//获取会话ID
        System.out.println(session.getHost());//获取当前登录用户主机地址
        System.out.println(session.getTimeout());//获取超时时间
        System.out.println(session.getStartTimestamp()); //获取会话创建时间
        System.out.println(session.getLastAccessTime()); //获取最后访问时间
        Thread.sleep(1000L);
        session.touch();//更新会话最后访问时间
        System.out.println(session.getLastAccessTime());


        //会话属性操作
        session.setAttribute("key", "123");
        Assert.assertEquals("123", session.getAttribute("key"));
        session.removeAttribute("key");
    }
    
    
    @Before
    public void setUp() {

    }


    @After
    public void tearDown() throws Exception {
        ThreadContext.unbindSubject();//退出时请解除绑定Subject到线程 否则对下次测试造成影响
    }

    protected void login(String configFile, String username, String password) {
        //1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
        Factory<org.apache.shiro.mgt.SecurityManager> factory =
                new IniSecurityManagerFactory(configFile);

        //2、得到SecurityManager实例 并绑定给SecurityUtils
        org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        //3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        token.setHost("10.83.1.1");
        subject.login(token);
    }

    public Subject subject() {
        return SecurityUtils.getSubject();
    }
    

}
