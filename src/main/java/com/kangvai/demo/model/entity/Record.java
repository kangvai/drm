package com.kangvai.demo.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import javax.persistence.*;

@Table(name = "record")
public class Record {
    /**
     * 记录主键
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 作者id
     */
    @Column(name = "author_id")
    private Integer authorId;

    /**
     *  作者姓名
     */
    @Column(name = "author_name")
    private String authorName;

    /**
     *  文章标题
     */
    @Column(name = "work_title")
    private String workTitle;

    /**
     *  检测时间
     */
    @Column(name = "check_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date checkTime;

    /**
     *  检测状态
     */
    @Column(name = "result_status")
    private Byte resultStatus;

    /**
     *  反馈内容
     */
    private String feedback;

    /**
     * 0-未删除 1-已删除
     */
    private Byte isdeleted;

    /**
     * 获取记录主键
     *
     * @return id - 记录主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置记录主键
     *
     * @param id 记录主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取作者id
     *
     * @return author_id - 作者id
     */
    public Integer getAuthorId() {
        return authorId;
    }

    /**
     * 设置作者id
     *
     * @param authorId 作者id
     */
    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    /**
     * 获取 作者姓名
     *
     * @return author_name -  作者姓名
     */
    public String getAuthorName() {
        return authorName;
    }

    /**
     * 设置 作者姓名
     *
     * @param authorName  作者姓名
     */
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    /**
     * 获取 文章标题
     *
     * @return work_title -  文章标题
     */
    public String getWorkTitle() {
        return workTitle;
    }

    /**
     * 设置 文章标题
     *
     * @param workTitle  文章标题
     */
    public void setWorkTitle(String workTitle) {
        this.workTitle = workTitle;
    }

    /**
     * 获取 检测时间
     *
     * @return check_time -  检测时间
     */
    public Date getCheckTime() {
        return checkTime;
    }

    /**
     * 设置 检测时间
     *
     * @param checkTime  检测时间
     */
    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    /**
     * 获取 检测状态
     *
     * @return result_status -  检测状态
     */
    public Byte getResultStatus() {
        return resultStatus;
    }

    /**
     * 设置 检测状态
     *
     * @param resultStatus  检测状态
     */
    public void setResultStatus(Byte resultStatus) {
        this.resultStatus = resultStatus;
    }

    /**
     * 获取 反馈内容
     *
     * @return feedback -  反馈内容
     */
    public String getFeedback() {
        return feedback;
    }

    /**
     * 设置 反馈内容
     *
     * @param feedback  反馈内容
     */
    public void setFeedback(String feedback) {
        this.feedback = feedback;
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
}