package xyz.chen.baidu_ai_back.img_process;



import com.baidu.aip.face.AipFace;
import org.apache.commons.codec.binary.Base64;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.chen.baidu_ai_back.pojo.IUser;
import xyz.chen.baidu_ai_back.pojo.User;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

/**
 * @author zhuzhe
 * @date 2019/7/15 17:40
 * @email zhuzhe_mail@163.com
 */
public class Facedect {

    //设置APPID/AK/SK
    public static final String APP_ID = "22674117";
    public static final String API_KEY = "j7LGwciBDqS9ezWIo82nplS4";
    public static final String SECRET_KEY = "fb8O2dyuSWwgZMbO50hquXFxUH0s4IA1";

    public IUser getInfoFromImg(String imgBase64){
//        文档地址
//        http://ai.baidu.com/docs#/Face-Java-SDK/top
        String image = imgBase64;
        String imageType = "BASE64";

        // 初始化一个AipFace
        AipFace client = new AipFace(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
//        client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
//        client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

        // 传入可选参数调用接口，根据需求自行设置
        HashMap<String, String> options = new HashMap<>();


        options.put("face_field", "age,beauty,expression,face_shape,gender,glasses,race,eye_status,emotion,face_type");
//        options.put("max_face_num", "2");
//        options.put("face_type", "LIVE");
//        options.put("liveness_control", "LOW");

        JSONObject res = client.detect(image, imageType, options);
        if (res.getString("error_msg") != null && res.getString("error_msg").equals("SUCCESS")) {
            JSONArray faceList = res.getJSONObject("result").getJSONArray("face_list");
            JSONObject jsonObject = faceList.getJSONObject(0);
            Integer age = jsonObject.getInt("age");
            Double beauty = jsonObject.getDouble("beauty");
            System.out.println("Beauty:"+beauty);
            String expression = jsonObject.getJSONObject("expression").getString("type");
            String face_shape = jsonObject.getJSONObject("face_shape").getString("type");
            String gender = jsonObject.getJSONObject("gender").getString("type");
            String race = jsonObject.getJSONObject("race").getString("type");
            Integer eye_status = jsonObject.getJSONObject("eye_status").getInt("right_eye");
            Integer face_probability = jsonObject.getInt("face_probability");
            String glasses = jsonObject.getJSONObject("glasses").getString("type");
            String emotion = jsonObject.getJSONObject("emotion").getString("type");
            String face_type = jsonObject.getJSONObject("face_type").getString("type");
            User user = new User();
            user.setPropty(age,beauty,expression,face_shape,gender,race,eye_status,face_probability,glasses,emotion,face_type);

            System.out.println("face_token：" + jsonObject.getString("face_token"));

            return user;

        } else {
            System.out.println(res.toString());
            return null;
        }

    }

}
