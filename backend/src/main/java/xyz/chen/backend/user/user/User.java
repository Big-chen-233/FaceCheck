//package xyz.chen.backend.user.user;
//
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import xyz.chen.backend.result.Result;
//
//import javax.persistence.*;
//
//
//@Entity
//@Table(name = "face_check_user")
//@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
//public class User {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    Integer id;
//    String nickName;
//    String Base64Img;
//    String score;
//    String comment;
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getNickName() {
//        return nickName;
//    }
//
//    public void setNickName(String nickName) {
//        this.nickName = nickName;
//    }
//
//    public String getBase64Img() {
//        return Base64Img;
//    }
//
//    public void setBase64Img(String base64Img) {
//        Base64Img = base64Img;
//    }
//
//    public String getScore() {
//        return score;
//    }
//
//    public void setScore(String score) {
//        this.score = score;
//    }
//
//    public String getComment() {
//        return comment;
//    }
//
//    public void setComment(String comment) {
//        this.comment = comment;
//    }
//}
