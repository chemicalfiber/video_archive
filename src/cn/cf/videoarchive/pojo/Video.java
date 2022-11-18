package cn.cf.videoarchive.pojo;

import java.sql.Date;

public class Video {
    private Integer v_id;   // 视频ID
    private Integer v_creator_id;   // 创作者ID
    private String u_nick_name;  // 创作者昵称
    private String v_title; // 视频标题
    private String v_introduction;  // 视频简介
    private String v_type;  // 视频所属分区
    private String v_thumbnail; // 缩略图地址（图床链接）
    private String v_play_link; // 视频可直接播放地址（网络或本地）
    private Date v_publication_date;    // 视频发布时间
    private String v_bili_link; // 哔哩哔哩视频链接
    private String v_yt_link;   // YouTube视频链接

    @Override
    public String toString() {
        return "Video{" +
                "v_id=" + v_id +
                ", v_creator_id=" + v_creator_id +
                ", u_nickname='" + u_nick_name + '\'' +
                ", v_title='" + v_title + '\'' +
                ", v_introduction='" + v_introduction + '\'' +
                ", v_type='" + v_type + '\'' +
                ", v_thumbnail='" + v_thumbnail + '\'' +
                ", v_play_link='" + v_play_link + '\'' +
                ", v_publication_date=" + v_publication_date +
                ", v_bili_link='" + v_bili_link + '\'' +
                ", v_yt_link='" + v_yt_link + '\'' +
                '}';
    }

    public String getU_nick_name() {
        return u_nick_name;
    }

    public void setU_nick_name(String u_nick_name) {
        this.u_nick_name = u_nick_name;
    }

    public Integer getV_id() {
        return v_id;
    }

    public void setV_id(Integer v_id) {
        this.v_id = v_id;
    }

    public Integer getV_creator_id() {
        return v_creator_id;
    }

    public void setV_creator_id(Integer v_creator_id) {
        this.v_creator_id = v_creator_id;
    }

    public String getV_title() {
        return v_title;
    }

    public void setV_title(String v_title) {
        this.v_title = v_title;
    }

    public String getV_introduction() {
        return v_introduction;
    }

    public void setV_introduction(String v_introduction) {
        this.v_introduction = v_introduction;
    }

    public String getV_type() {
        return v_type;
    }

    public void setV_type(String v_type) {
        this.v_type = v_type;
    }

    public String getV_thumbnail() {
        return v_thumbnail;
    }

    public void setV_thumbnail(String v_thumbnail) {
        this.v_thumbnail = v_thumbnail;
    }

    public String getV_play_link() {
        return v_play_link;
    }

    public void setV_play_link(String v_play_link) {
        this.v_play_link = v_play_link;
    }

    public Date getV_publication_date() {
        return v_publication_date;
    }

    public void setV_publication_date(Date v_publication_date) {
        this.v_publication_date = v_publication_date;
    }

    public String getV_bili_link() {
        return v_bili_link;
    }

    public void setV_bili_link(String v_bili_link) {
        this.v_bili_link = v_bili_link;
    }

    public String getV_yt_link() {
        return v_yt_link;
    }

    public void setV_yt_link(String v_yt_link) {
        this.v_yt_link = v_yt_link;
    }

    public Video(Integer v_id, Integer v_creator_id, String u_nick_name, String v_title, String v_introduction, String v_type, String v_thumbnail, String v_play_link, Date v_publication_date, String v_bili_link, String v_yt_link) {
        this.v_id = v_id;
        this.v_creator_id = v_creator_id;
        this.u_nick_name = u_nick_name;
        this.v_title = v_title;
        this.v_introduction = v_introduction;
        this.v_type = v_type;
        this.v_thumbnail = v_thumbnail;
        this.v_play_link = v_play_link;
        this.v_publication_date = v_publication_date;
        this.v_bili_link = v_bili_link;
        this.v_yt_link = v_yt_link;
    }

    public Video() {
    }
}
