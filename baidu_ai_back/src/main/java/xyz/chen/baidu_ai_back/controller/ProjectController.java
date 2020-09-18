package xyz.chen.baidu_ai_back.controller;

import com.sun.istack.internal.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.UserService;
import xyz.chen.baidu_ai_back.img_process.Facedect;
import xyz.chen.baidu_ai_back.pojo.IUser;
import xyz.chen.baidu_ai_back.pojo.ImgRecv;
import xyz.chen.baidu_ai_back.pojo.User;
import xyz.chen.baidu_ai_back.result.ResponseResult;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api")
public class ProjectController {
    UserService userService;

    @CrossOrigin
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult recievePic(@RequestBody ImgRecv imgRecv){
        String imgBase64 = imgRecv.getImgBase64().split(";base64,")[1].replaceAll("[\\{\\}\\[\\]\"]", "");
        IUser user = new Facedect().getInfoFromImg(imgBase64);
        if(user == null){
            return new ResponseResult(404,null);
        }
        user.setNickname(imgRecv.getNickName());
        userService.add((User) user);

        System.out.println((User)user);
        return new ResponseResult(200, (User) user);
    }

    @CrossOrigin
    @RequestMapping(value = "/getlist",method = RequestMethod.POST)
    @ResponseBody
    public List<User> getList(@RequestBody Double beauty) {
        return userService.getList(beauty);

    }
}
