package xyz.chen.baidu_ai_back.controller;

import com.sun.istack.internal.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import xyz.chen.baidu_ai_back.img_process.Facedect;
import xyz.chen.baidu_ai_back.pojo.IUser;
import xyz.chen.baidu_ai_back.pojo.User;
import xyz.chen.baidu_ai_back.result.ResponseResult;

@Controller
@RequestMapping("/api")
public class ProjectController {
    @CrossOrigin
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult recievePic(@RequestBody String imgBase64){
        imgBase64 = imgBase64.split("data:image/jpeg;base64,")[1].replaceAll("[\\{\\}\\[\\]\"]", "");
        IUser user = new Facedect().getInfoFromImg(imgBase64);
        if(user == null){
            System.out.println((User)user);
            return new ResponseResult(404,null);
        }
        return new ResponseResult(200, (User) user);
    }
}
