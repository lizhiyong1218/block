
package com.lzy.block.core.utils.mail;
/**       
 * @author: ZhiYong.Li  
 * @date:2015年8月27日     
 */

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import com.lzy.block.api.common.StrUtil;
 
 
public class EmailManager { 
 
    private Properties props; //系统属性 
    private Session session; //邮件会话对象 
    private MimeMessage mimeMsg; //MIME邮件对象 
    private Multipart mp; //Multipart对象,邮件内容,标题,附件等内容均添加到其中后再生成MimeMessage对象 
      
    /**
     * Constructor
     * @param smtp 邮件发送服务器
     */
    public EmailManager(){
        props = System.getProperties();   
        props.put("mail.smtp.auth","false"); 
        session = Session.getDefaultInstance(props, null);
        session.setDebug(true);
        mimeMsg = new MimeMessage(session);
        mp = new MimeMultipart(); 
    } 
 
    /**
     * Constructor
     * @param smtp 邮件发送服务器
     */
    public EmailManager(String smtp, String username, String password){ 
        props = System.getProperties();
        props.put("mail.smtp.auth","true"); 
        props.put("mail.smtp.host", smtp);
        props.put("username", username);
        props.put("password", password);
        session = Session.getDefaultInstance(props, null);
        session.setDebug(false);
        mimeMsg = new MimeMessage(session);
        mp = new MimeMultipart(); 
    } 
 
    /** 
     * 发送邮件
     */ 
    public boolean sendMail(String from, String  to, String  copyto, String subject, String content, String filename) {
        try {
            //设置发信人
            mimeMsg.setFrom(new InternetAddress(from)); 
            
            if(StrUtil.isNotEmpty(to)){
            	mimeMsg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            }
            
            if(StrUtil.isNotEmpty(copyto)){
            	mimeMsg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(copyto));
            }
            //设置主题
            mimeMsg.setSubject(subject);
            //设置正文
            BodyPart bp = new MimeBodyPart(); 
            bp.setContent(content, "text/html;charset=utf-8");
            mp.addBodyPart(bp);
            //设置附件
            bp = new MimeBodyPart();
            FileDataSource fileds = new FileDataSource(filename); 
            bp.setDataHandler(new DataHandler(fileds)); 
            bp.setFileName(MimeUtility.encodeText(fileds.getName(),"UTF-8","B"));
            mp.addBodyPart(bp); 
            mimeMsg.setContent(mp); 
            mimeMsg.saveChanges(); 
            //发送邮件
            if(props.get("mail.smtp.auth").equals("true")){
                Transport transport = session.getTransport("smtp"); 
                transport.connect((String)props.get("mail.smtp.host"), (String)props.get("username"), (String)props.get("password")); 
                transport.sendMessage(mimeMsg, mimeMsg.getRecipients(Message.RecipientType.TO)); 
                if(StrUtil.isNotEmpty(copyto)){
                	transport.sendMessage(mimeMsg, mimeMsg.getRecipients(Message.RecipientType.CC));
                }
                transport.close(); 
            }else{
                Transport.send(mimeMsg);
            }
            System.out.println("邮件发送成功");
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return true;
    }
     

	public static void main(String[] args) {
		String smtp = "smtp.263.net";
		String username = "system@test.com";
		String password = "test";
		String from = "system@test.com";
		String to = "zhiyong.li@test.com.cn" ;
		String copyto = "zhiyong.li@test.com.cn" ;
		String subject = "主题";
		String content = "邮件内容xx";
//		String filename = "f:/过滤仓库11.txt";
		String filename = "d:/test.csv";
		EmailManager email = new EmailManager(smtp, username, password);
		email.sendMail(from, to, copyto, subject, content, filename);
		 
	}
     
}
