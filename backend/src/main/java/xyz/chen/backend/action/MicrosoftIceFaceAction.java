package xyz.chen.backend.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xyz.chen.backend.result.Result;
import xyz.chen.backend.service.IAnalyseImageService;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by weihuaxiao
 * on 2017/9/22.
 */
@Controller
@RequestMapping("/api")
public class MicrosoftIceFaceAction {

    private static final Logger logger = LoggerFactory.getLogger(MicrosoftIceFaceAction.class);

    @Autowired
    private IAnalyseImageService analyseImageService;

    @CrossOrigin
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Result getScoreByMicrosoftIce(@RequestBody String img64, HttpServletRequest request) {
        img64 = img64.split("data:image/jpeg;base64,")[1].replaceAll("[\\{\\}\\[\\]\"]", "");
        return  analyseImageService.getScoreByImageResult(img64);
    }

}
