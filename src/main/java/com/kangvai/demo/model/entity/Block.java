package com.kangvai.demo.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import javax.persistence.*;

@Table(name = "block")
public class Block {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 交易哈希值
     */
    private String txhash;

    /**
     * 发送者哈希
     */
    private String sender;

    /**
     * 交易创建时间
     */
    @Column(name = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 作者id
     */
    @Column(name = "author_id")
    private Integer authorId;

    /**
     * 作者姓名
     */
    @Column(name = "author_name")
    private String authorName;

    /**
     * 作品标题
     */
    @Column(name = "work_title")
    private String workTitle;

    /**
     * 作品哈希
     */
    @Column(name = "work_hash")
    private String workHash;

    /**
     * 0-未删除 1-已删除
     */
    private Byte isdeleted;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取交易哈希值
     *
     * @return txhash - 交易哈希值
     */
    public String getTxhash() {
        return txhash;
    }

    /**
     * 设置交易哈希值
     *
     * @param txhash 交易哈希值
     */
    public void setTxhash(String txhash) {
        this.txhash = txhash;
    }

    /**
     * 获取发送者哈希
     *
     * @return sender - 发送者哈希
     */
    public String getSender() {
        return sender;
    }

    /**
     * 设置发送者哈希
     *
     * @param sender 发送者哈希
     */
    public void setSender(String sender) {
        this.sender = sender;
    }

    /**
     * 获取交易创建时间
     *
     * @return create_time - 交易创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置交易创建时间
     *
     * @param createTime 交易创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
     * 获取作者姓名
     *
     * @return author_name - 作者姓名
     */
    public String getAuthorName() {
        return authorName;
    }

    /**
     * 设置作者姓名
     *
     * @param authorName 作者姓名
     */
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    /**
     * 获取作品标题
     *
     * @return work_title - 作品标题
     */
    public String getWorkTitle() {
        return workTitle;
    }

    /**
     * 设置作品标题
     *
     * @param workTitle 作品标题
     */
    public void setWorkTitle(String workTitle) {
        this.workTitle = workTitle;
    }

    /**
     * 获取作品哈希
     *
     * @return work_hash - 作品哈希
     */
    public String getWorkHash() {
        return workHash;
    }

    /**
     * 设置作品哈希
     *
     * @param workHash 作品哈希
     */
    public void setWorkHash(String workHash) {
        this.workHash = workHash;
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