package xyz.chen.baidu_ai_back.pojo;

public interface IUser {
    void setPropty(int age,
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
                   String faceToken);
    void setNickname(String nickname);

    double getBeauty();
    String getFaceToken();
}
