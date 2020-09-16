package xyz.chen.backend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.chen.backend.imageProcess.IFaceRecAction;

@Controller
public class ImageController {
    @Autowired
    private IFaceRecAction faceRecAction;

    @CrossOrigin
    @PostMapping(value = "api/update")
    @ResponseBody
    public String recAction(String imgBase64){
        int score = faceRecAction.getScoreByImageResult(imgBase64);

        String str_score = String.valueOf(score);
        return str_score;
    }
}
