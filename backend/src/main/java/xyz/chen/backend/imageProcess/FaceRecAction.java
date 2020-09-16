package xyz.chen.backend.imageProcess;


import net.sf.json.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Repository("faceRecAction")
public class FaceRecAction implements IFaceRecAction{
    private static final String upload_picBase64_api = "http://kan.msxiaobing.com/APi/Image/UploadBase64";
    private static final String ice_api = "http://kan.msxiaobing.com/Api/ImageAnalyze/Process?service=yanzhi";
    private static final String ice_page = "http://kan.msxiaobing.com/ImageGame/Portal?task=yanzhi";

    public int getScoreByImageResult(String imgBase64){
        String jsonResult = getUpload_picBase64(imgBase64);
        String analyzeResult = analyzeImage(jsonResult);
        return getScore(analyzeResult);
    }


    private String getUpload_picBase64(String imgBase64){
        StringBuilder sb=new StringBuilder();
        try {
            URL realUrl = new URL(upload_picBase64_api);
            HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");

            conn.setRequestProperty("Charset", "UTF-8");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Connection", "Keep-Alive");// 维持长连接

            DataOutputStream dataOutputStream = new DataOutputStream(conn.getOutputStream());
            dataOutputStream.writeBytes(imgBase64);
            dataOutputStream.flush();
            dataOutputStream.close();

            String readLine;
            BufferedReader responseReader=new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
            while((readLine=responseReader.readLine())!=null){
                sb.append(readLine).append("\n");
            }
            responseReader.close();
            System.out.println(sb.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            return sb.toString();
        }
    }

    public String analyzeImage(String jsonResult){
        DefaultHttpClient httpClient=new DefaultHttpClient();
        DefaultHttpClient httpClient2=new DefaultHttpClient();
        HttpPost httpPost1=new HttpPost(ice_api);
        HttpPost httpPost2=new HttpPost(ice_page);

        JSONObject jasonObject = JSONObject.fromObject(jsonResult);
        Map<String,String> contentImgUrl = (Map)jasonObject;
        String imgUrl = contentImgUrl.get("Host") + contentImgUrl.get("Url");
        System.out.print(jsonResult);
        List<NameValuePair> form=new ArrayList<NameValuePair>();

        form.add(new BasicNameValuePair("MsgId",String.valueOf(System.currentTimeMillis())+"063"));
        form.add(new BasicNameValuePair("CreateTime",String.valueOf(Calendar.getInstance().getTimeInMillis())));
        form.add(new BasicNameValuePair("Content[imageUrl]",imgUrl));

        try {

            httpPost1.addHeader("Referer","http://kan.msxiaobing.com/V3/Portal?task=yanzhi&ftid=91ac082228fb48739f12c66ee3a3fee0");
            httpPost1.setEntity(new UrlEncodedFormEntity(form, "UTF-8"));

            HttpResponse pageResponse=httpClient.execute(httpPost2);
            CookieStore cookieStore=httpClient.getCookieStore();
            httpClient2.setCookieStore(cookieStore);
            HttpResponse response=httpClient2.execute( httpPost1);
            return EntityUtils.toString(response.getEntity());
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }
    public int getScore(String jsonResult){
        Pattern pattern = Pattern.compile("\\d+[.]\\d+");
        Matcher m = pattern.matcher(jsonResult);
        if (m.find()) {
            System.out.println("analyzeResult=" + m.group());
            double temp = Double.parseDouble(m.group());
            int score = (int) (temp * 10);
            return score;
        } else {
            return 0;
        }

    }

}
