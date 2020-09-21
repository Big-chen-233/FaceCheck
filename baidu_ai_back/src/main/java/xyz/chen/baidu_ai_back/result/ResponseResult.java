package xyz.chen.baidu_ai_back.result;

import xyz.chen.baidu_ai_back.pojo.User;

public class ResponseResult {
    private int code;
    User user = null;

    public ResponseResult(int code, User user) {
        this.code = code;
        this.user = user;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
