package xyz.chen.baidu_ai_back.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.chen.baidu_ai_back.pojo.User;

import java.util.List;

public interface UserDao extends JpaRepository<User,Integer> {
    List<User> findAll();
}
