package com.lzy.block.core.shiro.cryptography;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import com.lzy.block.api.model.shiro.ShiroUser;

/**
 * 
 * @ClassName: PasswordHelper
 * @Description: 加密工具类
 * @author 李志勇
 * @date 2015年3月18日 下午2:22:26
 * 
 */
public class PasswordHelper {

    private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

    private String algorithmName = "md5";
    private final int hashIterations = 2;

    public void encryptPassword(ShiroUser user) {

        user.setSalt(randomNumberGenerator.nextBytes().toHex());

        String newPassword = new SimpleHash(
                algorithmName,
                user.getPassword(),
                ByteSource.Util.bytes(user.getCredentialsSalt()),
                hashIterations).toHex();

        user.setPassword(newPassword);
    }
}
