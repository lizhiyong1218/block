package com.lzy.block.core.utils.crypto;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES {

    // 加密公钥
    private static final String Algorithm = "AES/CBC/PKCS5Padding";
    private static final String PWD       = "12345678";

    public static String decrypt(String cipherText, String iv) throws Exception {
        iv = iv.length() > 16 ? iv.substring(0, 16) : iv;
        return decrypt(cipherText, PWD, iv);
    }

    /**
     * 解密 以String密文输入,String明文输出
     * 
     * @param strMi
     * @return
     */
    public static String decrypt(String cipherText, String password, String iv) throws Exception {
        try {
            byte[] bytes = decrypt(Base64.decode(cipherText), password, iv);
            return new String(bytes, "UTF-8");
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 解密以byte[]密文输入,以byte[]明文输出
     * 
     * @param byteD
     * @return
     */
    public static byte[] decrypt(byte[] byteD, String pwd, String iv) {
        Cipher cipher;
        byte[] byteFina = null;
        try {
            cipher = Cipher.getInstance(Algorithm);

            SecretKeySpec keySpec = new SecretKeySpec(getKey(pwd), "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(iv.getBytes("UTF-8"));
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

            byteFina = cipher.doFinal(byteD);

        } catch (Exception e) {
        } finally {
            cipher = null;
        }
        return byteFina;
    }

    /**
     * 获取随机向量,取前16位
     * 
     * @return
     */
    public static String getRandomIv() {
        return UUID.randomUUID().toString().substring(0, 16);
    }

    private static byte[] getKey(String password) throws UnsupportedEncodingException {
        // 使用256位密码
        if (password.length() > 16) password = password.substring(0, 16);
        else if (password.length() < 16) {
            int count = (16 - password.length());
            for (int i = 0; i < count; i++) {
                password += "0";
            }
        }

        return password.getBytes("UTF-8");
    }

    /**
     * 加密
     * 
     * @param plainText 明文
     * @param iv 16位的随机码
     * @return
     */
    public static String encrypt(String plainText, String password, String iv) {
        try {
            return Base64.encode(encrypt(plainText.getBytes("UTF-8"), password, iv));
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    /**
     * 加密
     * 
     * @param plainText 明文
     * @param iv 16位的随机码
     * @return
     */
    public static String encrypt(String plainText, String iv) {
        try {
            iv = iv.length() > 16 ? iv.substring(0, 16) : iv;
            return Base64.encode(encrypt(plainText.getBytes("UTF-8"), PWD, iv));
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    /**
     * 加密以byte[]明文输入,byte[]密文输出
     * 
     * @param byteS
     * @return
     */
    public static byte[] encrypt(byte[] byteS, String pwd, String iv) {

        byte[] byteFina = null;
        Cipher cipher;
        try {
            cipher = Cipher.getInstance(Algorithm);

            SecretKeySpec keySpec = new SecretKeySpec(getKey(pwd), "AES");

            IvParameterSpec ivSpec = new IvParameterSpec(iv.getBytes("UTF-8"));

            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
            byteFina = cipher.doFinal(byteS);
        } catch (Exception e) {
        } finally {
            cipher = null;
        }

        return byteFina;
    }

    /**
     * 解密 以String名文输入,String密文输出
     * 
     * @param strMi
     * @return
     */
    public static String encryptAll(String plainText, String pwd, String iv) {
        try {
            iv = iv.length() > 16 ? iv.substring(0, 16) : iv;
            return Base64.encode(encrypt(plainText.getBytes("UTF-8"), pwd, iv));
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    public static void main(String[] args) {
        try {
//        	生成16位随机数
//        	System.out.println(RandomStringUtils.random(16, true, true));
        	
        	String str="lzy";
        	String salt="1234567ab2345678";//必须16位以上
        	String encrypt = AES.encrypt(str, salt);
        	System.out.println(encrypt);
        	String decrypt=AES.decrypt(encrypt,salt);
        	System.out.println(decrypt);
//            System.out.println(AES.encrypt("DTE+FDGDTY4E32SDFSD342GFSFSDFDS", "1234567ab2345678"));
//            System.out.println(AES.decrypt("On8IeSVA5Q/BUkEto95TNerdhLzS4p/yebhK4vb4UMI=", "1234567ab2345678"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
