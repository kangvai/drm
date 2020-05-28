package com.kangvai.demo.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import javax.persistence.*;

@Table(name = "news")
public class News {
    /**
     * 新闻序号
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 新闻作者
     */
    @Column(name = "news_author")
    private String newsAuthor;

    /**
     * 新闻标题
     */
    @Column(name = "news_title")
    private String newsTitle;

    /**
     * 新闻出版社
     */
    @Column(name = "news_press")
    private String newsPress;

    /**
     * 出版时间
     */
    @Column(name = "publish_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date publishTime;

    /**
     * 0-未删除 1-已删除
     */
    private Byte isdeleted;

    /**
     * 新闻内容
     */
    @Column(name = "news_content")
    private String newsContent;

    /**
     * 获取新闻序号
     *
     * @return id - 新闻序号
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置新闻序号
     *
     * @param id 新闻序号
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取新闻作者
     *
     * @return news_author - 新闻作者
     */
    public String getNewsAuthor() {
        return newsAuthor;
    }

    /**
     * 设置新闻作者
     *
     * @param newsAuthor 新闻作者
     */
    public void setNewsAuthor(String newsAuthor) {
        this.newsAuthor = newsAuthor;
    }

    /**
     * 获取新闻标题
     *
     * @return news_title - 新闻标题
     */
    public String getNewsTitle() {
        return newsTitle;
    }

    /**
     * 设置新闻标题
     *
     * @param newsTitle 新闻标题
     */
    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    /**
     * 获取新闻出版社
     *
     * @return news_press - 新闻出版社
     */
    public String getNewsPress() {
        return newsPress;
    }

    /**
     * 设置新闻出版社
     *
     * @param newsPress 新闻出版社
     */
    public void setNewsPress(String newsPress) {
        this.newsPress = newsPress;
    }

    /**
     * 获取出版时间
     *
     * @return publish_time - 出版时间
     */
    public Date getPublishTime() {
        return publishTime;
    }

    /**
     * 设置出版时间
     *
     * @param publishTime 出版时间
     */
    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    /**
     * 获取0-未删除 1-已删除
     *
     * @return isdeleted - 0-未删除 1-已删除
     */
    public Byte getIsdeleted() {
        return isdeleted;
    }

    /**
     * 设置0-未删除 1-已删除
     *
     * @param isdeleted 0-未删除 1-已删除
     */
    public void setIsdeleted(Byte isdeleted) {
        this.isdeleted = isdeleted;
    }

    /**
     * 获取新闻内容
     *
     * @return news_content - 新闻内容
     */
    public String getNewsContent() {
        return newsContent;
    }

    /**
     * 设置新闻内容
     *
     * @param newsContent 新闻内容
     */
    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }
}