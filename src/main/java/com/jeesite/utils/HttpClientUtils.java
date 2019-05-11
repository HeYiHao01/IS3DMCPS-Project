package com.jeesite.utils;
import com.alibaba.fastjson.*;
import com.jeesite.common.config.Global;
import org.apache.http.*;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.*;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.*;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.helper.StringUtil;

import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HttpClientUtils {

    private static final CloseableHttpClient HTTP_CLIENT = HttpClients.createDefault();
    private static final String API_URL = Global.getConfig("faceapi.url");
    private static final String API_GALLERIES = Global.getConfig("faceapi.galleries");
    private static final String GONGAN_URL = Global.getConfig("gongan.url");
    private static final String GONGAN_APIKEY = Global.getConfig("gongan.apiKey");

    public static String postGongAn(int page) throws Exception {
        HttpPost httppost;
        if(StringUtil.isBlank(GONGAN_APIKEY)) {
             httppost = new HttpPost(GONGAN_URL);      	
        }else {
             httppost = new HttpPost(GONGAN_URL + "?apiKey=" +GONGAN_APIKEY );        	
        }
        httppost.setHeader("Content-Type", "application/json");
        JSONObject condition =new JSONObject();
        condition.put("cardIdKey", "*");
        condition.put("refId", "6");
        JSONObject data =new JSONObject();
        JSONObject json =new JSONObject();
        data.put("current", page);
        data.put("size",20);
        data.put("condition", condition);
        data.put("isAsc", false);
        json.put("data", data);
        StringEntity params = new StringEntity(json.toString());
        httppost.addHeader("content-type", "application/json");
        httppost.setEntity(params);
        try (CloseableHttpResponse response = HTTP_CLIENT.execute(httppost)) {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String res = EntityUtils.toString(entity, "utf-8");
                return res;
            }else {
            	return "";
            }
        } catch (HttpHostConnectException he) {
        	return "";
        }
    }

    public static String uploadFacePhoto(File inputStream, String stuNo) throws Exception {
        ContentType contentType = ContentType.create("text/plain", Consts.UTF_8);
        HttpPost httppost = new HttpPost(API_URL + "/face");
        httppost.addHeader("Accept-Encoding", "gzip,deflate");
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        builder.setCharset(Charset.forName("utf-8"));
        builder.addTextBody("galleries", API_GALLERIES, contentType);
        builder.addTextBody("meta", stuNo, contentType);
        builder.addPart("photo", new FileBody(inputStream));

        httppost.setEntity(builder.build());
        try (CloseableHttpResponse response = HTTP_CLIENT.execute(httppost)) {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String res = EntityUtils.toString(entity, "utf-8");
                JSONObject jsonObject = JSON.parseObject(res);
                JSONArray results = jsonObject.getJSONArray("results");
                if (results != null && results.size() == 1) {
                    return results.getJSONObject(0).getString("id");
                } else {
                    String code = jsonObject.getString("code");
                    if ("NO_FACES".equals(code)) {
                        throw new Exception("无有效的人脸图像");
                    }
                }
            }
        } catch (HttpHostConnectException he) {
            throw he;
        }
        return "";
    }
    
    public static String pictureVerify(String photo1, String photo2) throws Exception {
        HttpPost httppost = new HttpPost(API_URL + "/verify");
        List<NameValuePair> params=new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("photo1",photo1));
        params.add(new BasicNameValuePair("photo2",photo2));
        params.add(new BasicNameValuePair("threshold","0.78"));
        httppost.setEntity(new UrlEncodedFormEntity(params,Consts.UTF_8));
   
        try (CloseableHttpResponse response = HTTP_CLIENT.execute(httppost)) {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String res = EntityUtils.toString(entity, "utf-8");
                JSONObject jsonObject = JSON.parseObject(res);
                JSONArray results = jsonObject.getJSONArray("results");
                if (results != null && results.size() == 1) {
                    return results.getJSONObject(0).getString("verified");
                } else {
                        return "false";
                }
            }
        } catch (HttpHostConnectException he) {
            throw he;
        }
        return "false";
    }

    public static String deleteByFaceId(String faceId) throws HttpHostConnectException {
        HttpDelete httpDelete = new HttpDelete(API_URL + "/face/id/" + faceId);
        try (CloseableHttpResponse response = HTTP_CLIENT.execute(httpDelete)) {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String res = EntityUtils.toString(entity, "utf-8");
                if (res.equals("\r\n")) {
                    String s = getMetaByFaceId(faceId);
                    if (s != null && !"".equals(s)) {
                        throw new Exception("删除人脸数据失败");
                    }
                    return "";
                }
                JSONObject jsonObject = JSON.parseObject(res);
                return jsonObject.getString("execute");
            }
        } catch (HttpHostConnectException he) {
            throw he;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getMetaByFaceId(String faceId) throws HttpHostConnectException {
        HttpGet httpDelete = new HttpGet(API_URL + "/face/id/" + faceId);
        try (CloseableHttpResponse response = HTTP_CLIENT.execute(httpDelete)) {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String res = EntityUtils.toString(entity, "utf-8");
                JSONObject jsonObject = JSON.parseObject(res);
                return jsonObject.getString("meta");
            }
        } catch (HttpHostConnectException he) {
            throw he;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * 以字符串形式获取当前时间戳
     *
     */
    public static String getTimeNum(){
        Date date = new Date();
        Long time = date.getTime();
        String now = time.toString();

        return now;
    }
}
