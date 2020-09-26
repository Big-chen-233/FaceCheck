package xyz.chen.baidu_ai_back.pojo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

public class User implements IUser{
    private String nickname;
    private Integer age;
    private Double beauty;
    private String expression;
    private String face_shape;
    private String gender;
    private String race;
    private Integer eye_status;
    private Integer face_probability;
    private String glasses;
    private String emotion;
    private String face_type;
    private String faceToken;

    public String getFaceToken() {
        return faceToken;
    }

    public String getNickname() {
        return nickname;
    }

    @Override


    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setPropty(int age,
                          double beauty,
                          String expression,
                          String face_shape,
                          String gender,
                          String race,
                          int eye_status,
                          int face_probability,
                          String glasses,
                          String emotion,
                          String face_type,
                          String faceToken){
        this.age = age;
        this.beauty= beauty;
        this.expression=expression;
        this.face_shape = face_shape;
        this.gender = gender;
        this.race = race;
        this.eye_status = eye_status;
        this.face_probability = face_probability;
        this.glasses = glasses;
        this.emotion = emotion;
        this.face_type = face_type;
        this.faceToken = faceToken;
    }

    public Integer getAge() {
        return age;
    }

    public double getBeauty() {
        return beauty;
    }

    public String getExpression() {
        return expression;
    }

    public String getFace_shape() {
        return face_shape;
    }

    public String getGender() {
        return gender;
    }

    public String getRace() {
        return race;
    }

    public Integer getEye_status() {
        return eye_status;
    }

    public Integer getFace_probability() {
        return face_probability;
    }

    public String getGlasses() {
        return glasses;
    }

    public String getEmotion() {
        return emotion;
    }

    public String getFace_type() {
        return face_type;
    }

}
