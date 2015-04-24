/**  
 * @Title: FtpUtils.java
 * @Package com.lzy.block.console.utils
 * @author 李志勇  
 * @date 2015年4月20日 上午11:55:17
 * @version V1.0  
 */
package com.lzy.block.console.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;

/**
 * @ClassName: FtpUtils
 * @Description: FtpUtils
 * @author 李志勇
 * @date 2015年4月20日 上午11:55:17
 * 
 */
public class FtpUtils {

	private static Logger logger = Logger.getLogger(FtpUtils.class);

	public static FTPClient getFTPClient(String ip, int port, String username,
			String password) throws IOException {
		FTPClient ftpClient = new FTPClient();
		try {
			ftpClient.setControlEncoding("UTF-8");
			ftpClient.connect(ip, port);
			// 登录
			ftpClient.login(username, password);
			// 设置文件传输类型为二进制
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			ftpClient.setDataTimeout(120000);
			// 检测连接是否成功
			int reply = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftpClient.disconnect();
				logger.error("登录ftp服务器失败，FTP服务器无法打开！");
			}
			System.out.println(reply+"=====");
		} catch (SocketException e) {
			logger.error("登录ftp服务器失败,连接超时！");
			return null;
		} catch (IOException e) {
			logger.error("登录ftp服务器失败，FTP服务器无法打开！");
			return null;
		}
		return ftpClient;
	}
	
	 
	public static void main(String[] args) {
		String host="10.1.100.59";
		int port=9999;
		String username="pic1";
		String password="pic1";
		String remotePath="/data/www/pic/28/0f5e3c98-e170-11e3-aef8-00155d004608.jpg";
		String localPath="e:/myftp/0f5e3c98-e170-11e3-aef8-00155d004608.jpg";
		String fileName="0f5e3c98-e170-11e3-aef8-00155d004608.jpg";
		downFtpFile(host, port, username, password, remotePath, fileName, localPath);
		
	}
	
	public static boolean downFtpFile(String url, int port, String username,  
            String password, String remotePath, String fileName,  
            String localPath) {  
        boolean success = false;  
        FTPClient ftp = new FTPClient();  
        try {  
            int reply;  
            ftp.connect(url, port);  
            // 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器  
            ftp.login(username, password);// 登录  
            reply = ftp.getReplyCode();  
            if (!FTPReply.isPositiveCompletion(reply)) {  
                ftp.disconnect();  
                return success;  
            }  
            ftp.changeWorkingDirectory(remotePath);// 转移到FTP服务器目录  
            FTPFile[] fs = ftp.listFiles();  
            for (FTPFile ff : fs) {  
                String fname = new String(ff.getName().getBytes("iso-8859-1"),  
                        "gbk");  
                if (fname.equals(fileName)) {  
                    File localFile = new File(localPath);  
                    OutputStream is = new FileOutputStream(localFile);  
                    ftp.retrieveFile(ff.getName(), is);  
                    is.close();  
                    break;  
                }  
            }  
            ftp.logout();  
            success = true;  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            if (ftp.isConnected()) {  
                try {  
                    ftp.disconnect();  
                } catch (IOException ioe) {  
                }  
            }  
        }  
        return success;  
    }
	
}
