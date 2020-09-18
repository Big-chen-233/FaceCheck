package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.chen.baidu_ai_back.dao.UserDao;
import xyz.chen.baidu_ai_back.pojo.User;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserDao userDao;

    public void add(User user){
        userDao.save(user);
    }

    public List<User> getList(){
       return userDao.findAll();
    }
}
