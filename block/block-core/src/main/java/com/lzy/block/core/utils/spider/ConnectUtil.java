package com.lzy.block.core.utils.spider;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class ConnectUtil {
	
	private static final String HTTP_USERAGENT = "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.97 Safari/537.11";
	private static final Logger logger = Logger.getLogger(ConnectUtil.class.getName());
	
	public static boolean createPicture(String imgUrl,String destPath) {
		try {
			BufferedInputStream in = new BufferedInputStream(new URL(imgUrl).openStream());
			// 生成图片   
			BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(destPath));
			byte[] bytes = new byte[2048];
			int length = in.read(bytes);
			while (length != -1) {
				out.write(bytes, 0, length);
				length = in.read(bytes);
			}
			in.close();
			out.close();
			File f=new File(destPath);
			return f.exists();
		} catch (Exception e) {
			return false;
		}
	}
	
	
	public static Document getDocument(String url) throws Exception {
		Document doc = null;
		int count = 0;
		while(doc == null){
			try {
				count++;
				Thread.sleep(150);
				doc = Jsoup.connect(url).timeout(30000).userAgent(HTTP_USERAGENT).get();
			} catch (Exception e) {
				Thread.sleep(5000);
			}
			if(count >= 3){
				break;
			}
		}
		if(doc == null){
			logger.error("--------not get document--------");
		}
		return doc;
	}
	
	
	public static void testGet(){
//		String str="http://esf.fang.com/agenthome/kw%b8%df%ce%e4+/";
		 String url="http://esf.fang.com/";
		 String CARD_TITLE="已通过名片认证";
		 String COMPANY="公司";
		 String SERVICEAREA="服务商圈";
		 String SHOPNAME="门店名称";
		
		String param="%b8%df%ce%e4+/";
		String city="";
		String mobileurl=url+"agenthome/kw"+param;
		String contenturl=url+"agent/Agentnew/Aloneself.aspx?&agentid={agentid}&managername={managername}";
		try {
			Document mobileDoc = ConnectUtil.getDocument(mobileurl);
			System.err.println(mobileDoc.toString());
			if(mobileDoc!=null) {
				Elements ele=mobileDoc.select("div.house").select("p.housetitle");
				Elements managerElement=ele.select("a");
				String hrefUrl=managerElement.attr("href");
				String managername=hrefUrl.substring(hrefUrl.lastIndexOf("/")+1);
				Elements agentidElement=ele.select("span.gray9").eq(0);
				String agentid=agentidElement.text().replace("(ID:","").replace(")","");
				if(!managername.equals("")&&!agentid.equals("")){
					String realUrl=contenturl.replace("{agentid}", agentid).replace("{managername}", managername);
					Document hrefDoc = ConnectUtil.getDocument(realUrl);
					if(hrefDoc!=null){
						Map<String, Object> params=new HashMap<String, Object>();
						Elements eleMain=hrefDoc.select("div.main");
						String personName=eleMain.select("div.rzname").text().trim();
						String personPicAddress=eleMain.select("div.rzren").select("img").attr("src");
						Elements personIdcardUrls=eleMain.select("ul.contlist01").select("a");
						for(Element element:personIdcardUrls){
							String title=element.attr("title");
							if(title.trim().equals(CARD_TITLE)){
								String personIdcardUrl=element.select("img").attr("src");
								params.put("personIdcardUrl", personIdcardUrl);
								break;
							}
						}
						String personPhone=eleMain.select("span.phonenum").text().trim();
						params.put("name",personName);
						params.put("personPicAddress", personPicAddress);
						params.put("phone",personPhone);
						
						Elements liEle=eleMain.select("ul.cont02").select("li");
						String text="";
						for(Element li : liEle){
							text=li.text().trim();
							String[] texts=text.toString().split("：");
							if(texts.length!=2){
								continue;
							}
							if(texts[0].trim().equals(COMPANY)){
								params.put("companyName",texts[1].trim());
							}else if(texts[0].trim().equals(SERVICEAREA)){
							}
						}
						liEle=eleMain.select("ul.cont03").select("li");
						for(Element li : liEle){
							text=li.text().trim();
							String[] texts=text.split("：");
							if(texts.length!=2){
								continue;
							}
							if(texts[0].trim().equals(SHOPNAME)){
								params.put("branchName",texts[1].trim());
								break;
							}
						}
						params.put("cityName",city);
						logger.info("person:"+params);
					 
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		testGet();
	}
	
}
