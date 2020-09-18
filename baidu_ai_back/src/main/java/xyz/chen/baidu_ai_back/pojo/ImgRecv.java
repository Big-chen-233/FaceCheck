package xyz.chen.baidu_ai_back.pojo;

public class ImgRecv {
    String nickName;
    String imgBase64;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getImgBase64() {
        return imgBase64;
    }

    public void setImgBase64(String imgBase64) {
        this.imgBase64 = imgBase64;
    }

    public ImgRecv(String nickName, String imgBase64) {
        this.nickName = nickName;
        this.imgBase64 = imgBase64;
    }
}
