package xyz.chen.backend.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import xyz.chen.backend.imageProcess.FaceRecAction;

@Controller
public class ImageController {
    private FaceRecAction faceRecAction;

    @CrossOrigin
    @PostMapping(value = "api/update")
    @ResponseBody
    public String recAction( MultipartFile multipartFile){
        int score = faceRecAction.getScoreByImageResult(multipartFile);
        return String.valueOf(score);
    }
}
