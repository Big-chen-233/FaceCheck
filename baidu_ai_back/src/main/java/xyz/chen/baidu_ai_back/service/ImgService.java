package xyz.chen.baidu_ai_back.service;

import xyz.chen.baidu_ai_back.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.chen.baidu_ai_back.pojo.EasyUser;

import java.util.List;
@Service
public class ImgService {
    @Autowired
    UserDao userDao;

    public List<EasyUser> getAll(){
        return userDao.findAll();
    }

    public void add(EasyUser easyUser){
        userDao.save(easyUser);
    }

    public boolean isExist(EasyUser easyUser) {
        if(userDao.findByFaceToken(easyUser.getFaceToken())!=null)
            return false;
        return true;
    }
    public void deleteUser(String faceToken){
        userDao.deleteByFaceToken(faceToken);
    }
}
