package cn.cf.videoarchive.pojo;

public class User {
    private Integer u_id;   // 用户ID
    private String u_name;  // 用户名
    private String u_nick_name; // 用户昵称
    private String u_password;  // 用户密码
    private Integer u_grant;    // 用户角色身份标识
    private String g_name;    // 用户角色身份

    public User() {
    }

    public User(Integer u_id, String u_name, String u_nick_name, String u_password, Integer u_grant) {
        this.u_id = u_id;
        this.u_name = u_name;
        this.u_nick_name = u_nick_name;
        this.u_password = u_password;
        this.u_grant = u_grant;
    }

    @Override
    public String toString() {
        return "User{" +
                "u_id=" + u_id +
                ", u_name='" + u_name + '\'' +
                ", u_nick_name='" + u_nick_name + '\'' +
                ", u_password='" + u_password + '\'' +
                ", u_grant=" + u_grant +
                ", g_name='" + g_name + '\'' +
                '}';
    }

    public Integer getU_id() {
        return u_id;
    }

    public void setU_id(Integer u_id) {
        this.u_id = u_id;
    }

    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public String getU_nick_name() {
        return u_nick_name;
    }

    public void setU_nick_name(String u_nick_name) {
        this.u_nick_name = u_nick_name;
    }

    public String getU_password() {
        return u_password;
    }

    public void setU_password(String u_password) {
        this.u_password = u_password;
    }

    public Integer getU_grant() {
        return u_grant;
    }

    public void setU_grant(Integer u_grant) {
        this.u_grant = u_grant;
    }

    public String getG_name() {
        return g_name;
    }

    public void setG_name(String g_name) {
        this.g_name = g_name;
    }
}
