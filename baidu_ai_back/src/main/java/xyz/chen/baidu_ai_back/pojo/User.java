package xyz.chen.baidu_ai_back.pojo;

public class User implements IUser{
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

    public User() {

    }
    public void setPropty(Integer age, Double beauty, String expression, String face_shape, String gender, String race, Integer eye_status, Integer face_probability, String glasses, String emotion, String face_type) {
        this.age = age;
        this.beauty = beauty;
        this.expression = expression;
        this.face_shape = face_shape;
        this.gender = gender;
        this.race = race;
        this.eye_status = eye_status;
        this.face_probability = face_probability;
        this.glasses = glasses;
        this.emotion = emotion;
        this.face_type = face_type;
    }



    public Integer getAge() {
        return age;
    }

    public Double getBeauty() {
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

    @Override
    public String toString() {
        return "User{" +
                "age=" + age +
                ", beauty=" + beauty +
                ", expression='" + expression + '\'' +
                ", face_shape='" + face_shape + '\'' +
                ", gender='" + gender + '\'' +
                ", race='" + race + '\'' +
                ", eye_status=" + eye_status +
                ", face_probability=" + face_probability +
                ", glasses='" + glasses + '\'' +
                ", emotion='" + emotion + '\'' +
                ", face_type='" + face_type + '\'' +
                '}';
    }
}
