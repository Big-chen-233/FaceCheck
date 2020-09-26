package xyz.chen.baidu_ai_back.img_process;



import com.baidu.aip.face.AipFace;
import org.json.JSONArray;
import org.json.JSONObject;
import xyz.chen.baidu_ai_back.pojo.IUser;
import xyz.chen.baidu_ai_back.pojo.User;
import java.util.HashMap;
public class Facedect {
    public static final String APP_ID = "22674117";
    public static final String API_KEY = "j7LGwciBDqS9ezWIo82nplS4";
    public static final String SECRET_KEY = "fb8O2dyuSWwgZMbO50hquXFxUH0s4IA1";

    public IUser getInfoFromImg(String imgBase64){
        String image = imgBase64;
        String imageType = "BASE64";


        AipFace client = new AipFace(APP_ID, API_KEY, SECRET_KEY);


        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        HashMap<String, String> options = new HashMap<>();


        options.put("face_field", "age,beauty,expression,face_shape,gender,glasses,race,eye_status,emotion,face_type");
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
            String face_token = jsonObject.getString("face_token");
            User user = new User();
            user.setPropty(age,beauty,expression,face_shape,gender,race,eye_status,face_probability,glasses,emotion,face_type,face_token);


            return user;

        } else {
            System.out.println(res.toString());
            return null;
        }

    }

}
