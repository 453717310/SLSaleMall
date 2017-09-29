package org.slsale.pojo;


import java.sql.Timestamp;

/**
 * Created by dll on 2017/9/19.
 * 公告类
 */
public class Affiche extends Base{
    private String code;//编码
    private String title;//标题
    private String content;//内容
    private String publisher;//发布人
    private Timestamp publisherTime;//发布时间
    private Timestamp startTime;//开始时间
    private Timestamp endTime;//结束时间

    public Affiche( String code, String title, String content, String publisher, Timestamp publisherTime, Timestamp startTime, Timestamp endTime) {
        this.code = code;
        this.title = title;
        this.content = content;
        this.publisher = publisher;
        this.publisherTime = publisherTime;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Affiche() {
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Timestamp getPublisherTime() {
        return publisherTime;
    }

    public void setPublisherTime(Timestamp publisherTime) {
        this.publisherTime = publisherTime;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Affiche{" +
                ", code='" + code + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", publisher='" + publisher + '\'' +
                ", publisherTime=" + publisherTime +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
