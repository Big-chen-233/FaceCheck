package xyz.chen.baidu_ai_back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import xyz.chen.baidu_ai_back.img_process.Facedect;
import xyz.chen.baidu_ai_back.pojo.*;
import xyz.chen.baidu_ai_back.result.ResponseResult;
import xyz.chen.baidu_ai_back.service.ImgService;

import java.util.List;

@Controller
@RequestMapping("/api")
public class ProjectController {
    @Autowired
    ImgService imgService;

    @CrossOrigin
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult recievePic(@RequestBody String imgRecv){
        String[] data = imgRecv.split(";base64,");
        String imgBase64 = data[1].replaceAll("[\\{\\}\\[\\]\"]", "");
        String nickname = data[0].split(",\"data64base\":\"data:")[0];
        nickname = nickname.replaceAll("\"nickname\":","").replaceAll("[\\{\\}\\[\\]\"]", "");
        IUser user = new Facedect().getInfoFromImg(imgBase64);
        EasyUser easyUser = new EasyUser();
        if(user == null){
            return new ResponseResult(404,null);
        }
        user.setNickname(nickname);
        easyUser.setNickname(nickname);
        easyUser.setBeauty(user.getBeauty());
        imgService.add(easyUser);
        System.out.println((User)user);
        return new ResponseResult(200, (User) user);
    }



    @CrossOrigin
    @RequestMapping(value = "/getlist",method = RequestMethod.GET)
    @ResponseBody
    public List<EasyUser> getList(){
        return imgService.getAll();
    }

}
