package xyz.chen.backend.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import xyz.chen.backend.result.Result;

/**
 * Created by weihuaxiao
 * on 2017/9/22.
 */
public interface IAnalyseImageService {

    Result getScoreByImageResult(String img64);




}
