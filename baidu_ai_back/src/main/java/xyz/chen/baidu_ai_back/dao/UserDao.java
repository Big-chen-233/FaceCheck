package xyz.chen.baidu_ai_back.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.chen.baidu_ai_back.pojo.EasyUser;

import java.util.List;


public interface UserDao extends JpaRepository<EasyUser,Integer>{
    List<EasyUser> findAll();
    EasyUser findByFaceToken(String faceToken);
    void deleteByFaceToken(String faceToken);
}
