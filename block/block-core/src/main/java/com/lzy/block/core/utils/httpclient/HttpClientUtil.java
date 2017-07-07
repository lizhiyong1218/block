package com.lzy.block.core.utils.httpclient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.lzy.block.core.common.JsonUtils;

public class HttpClientUtil {

    private static Logger log = Logger.getLogger(HttpClientUtil.class);

    public static String get(String uri) {
        log.info("HttpClientUtil getting uri:" + uri);
        HttpGet httpGet = new HttpGet(uri);
        return execute(httpGet);
    }
    
    public static String get(String uri, String contentType) {
        log.info("HttpClientUtil getting uri:" + uri);
        HttpGet httpGet = new HttpGet(uri);
        httpGet.addHeader("Content-Type", contentType);
        return execute(httpGet);
    }

    public static String post(String uri, Map<String, Object> params) {
        return post(uri, params, null);
    }
    
    
    public static String post(String uri, Map<String, Object> params, String cookieStr) {
    	 log.info("HttpClientUtil posting uri:" + uri);
         HttpPost httppost = new HttpPost(uri);
         List<NameValuePair> nvps = new ArrayList<NameValuePair>();
         for (Entry<String, Object> entry : params.entrySet()) {
             Object value = entry.getValue();
             nvps.add(new BasicNameValuePair(entry.getKey(), value != null ? value.toString() : null));
         }
         httppost.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));
         if(StringUtils.isNotBlank(cookieStr)){
        	 httppost.addHeader(new BasicHeader("Cookie", cookieStr));
         }
         return execute(httppost);
    }
    
    

    private static String execute(HttpUriRequest request) {
        String out = null;
        HttpClient client = new DefaultHttpClient();
        HttpResponse rsp;
        int status = 0;
        try {
            rsp = client.execute(request);
            status = rsp.getStatusLine().getStatusCode();
            log.info("HttpClientUtil got status:" + status);
            if (status == 200) {
                HttpEntity entity = rsp.getEntity();
                out = EntityUtils.toString(entity);
                log.info("HttpClientUtil got result:" + out);
            }
        } catch (ClientProtocolException e) {
            log.error("HttpClientUtil found ClientProtocolException:" + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            log.error("HttpClientUtil found IOException:" + e.getMessage());
            e.printStackTrace();
        }finally {
            client.getConnectionManager().shutdown();
        }
        return out;
    }
    
    public static void main(String[] args) {
		String uri="http://user.qfang.com/userApi/register/queryUserByPhone";
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("phone", "13414782312");
		String post = HttpClientUtil.post(uri, params);
		System.out.println(post);
	}
    
}
