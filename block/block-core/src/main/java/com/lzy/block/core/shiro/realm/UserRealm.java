package com.lzy.block.core.shiro.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
//import org.apache.shiro.util.ByteSource;
//import com.lzy.block.api.model.shiro.User;

/**
 * 
 * @ClassName: UserRealm
 * @Description: 自定义用户realm
 * @author 李志勇
 * @date 2015年3月18日 下午2:11:34
 * 
 */
public class UserRealm extends AuthorizingRealm {

//    private UserService userService = new UserServiceImpl();

	/**
	 * 获取授权信息
	 */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//        String username = (String)principals.getPrimaryPrincipal();

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//        authorizationInfo.setRoles(userService.findRoles(username));  设置角色
//        authorizationInfo.setStringPermissions(userService.findPermissions(username)); 设置权限

        return authorizationInfo;
    }

    
    /**
     *获取身份验证信息
     *首先根据传入的用户名获取User 信息；然后如果user 为空，那么抛出没找到帐号异常UnknownAccountException；如果user
	 *找到但锁定了抛出锁定异常LockedAccountException；最后生成AuthenticationInfo 信息，
	 *交给间接父类AuthenticatingRealm使用CredentialsMatcher进行判断密码是否匹配，如果不
	 *匹配将抛出密码错误异常IncorrectCredentialsException；另外如果密码重试此处太多将抛出
	 *超出重试次数异常ExcessiveAttemptsException；在组装SimpleAuthenticationInfo 信息时，
	 *需要传入：身份信息（用户名）、凭据（密文密码）、盐（username+salt），CredentialsMatcher
	 *使用盐加密传入的明文密码和此处的密文密码进行匹配。
     * 
     * 返回验证信息
     * 
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
 /*
        String username = (String)token.getPrincipal();

        User user = userService.findByUsername(username);

        if(user == null) {
            throw new UnknownAccountException();//没找到帐号
        }

        if(Boolean.TRUE.equals(user.getLocked())) {
            throw new LockedAccountException(); //帐号锁定
        }

        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user.getUsername(), //用户名
                user.getPassword(), //密码
                ByteSource.Util.bytes(user.getCredentialsSalt()),//salt=username+salt
                getName()  //realm name
        );
        return authenticationInfo; */
    	return null;
    }
}
