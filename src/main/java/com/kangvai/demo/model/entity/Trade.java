package com.kangvai.demo.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import javax.persistence.*;

@Table(name = "trade")
public class Trade {
    /**
     * 序号
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 作者序号
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
     * 购买者序号
     */
    @Column(name = "reader_id")
    private Integer readerId;

    /**
     * 购买者姓名
     */
    @Column(name = "reader_name")
    private String readerName;

    /**
     * 购买时间
     */
    @Column(name = "buy_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date buyTime;

    /**
     * 交易哈希值
     */
    @Column(name = "tx_hash")
    private String txHash;

    /**
     * 作品哈希值
     */
    @Column(name = "work_hash")
    private String workHash;

    /**
     * 0-未删除 1-已删除
     */
    private Byte isdeleted;

    /**
     * 获取序号
     *
     * @return id - 序号
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置序号
     *
     * @param id 序号
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取作者序号
     *
     * @return author_id - 作者序号
     */
    public Integer getAuthorId() {
        return authorId;
    }

    /**
     * 设置作者序号
     *
     * @param authorId 作者序号
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
     * 获取购买者序号
     *
     * @return reader_id - 购买者序号
     */
    public Integer getReaderId() {
        return readerId;
    }

    /**
     * 设置购买者序号
     *
     * @param readerId 购买者序号
     */
    public void setReaderId(Integer readerId) {
        this.readerId = readerId;
    }

    /**
     * 获取购买者姓名
     *
     * @return reader_name - 购买者姓名
     */
    public String getReaderName() {
        return readerName;
    }

    /**
     * 设置购买者姓名
     *
     * @param readerName 购买者姓名
     */
    public void setReaderName(String readerName) {
        this.readerName = readerName;
    }

    /**
     * 获取购买时间
     *
     * @return buy_time - 购买时间
     */
    public Date getBuyTime() {
        return buyTime;
    }

    /**
     * 设置购买时间
     *
     * @param buyTime 购买时间
     */
    public void setBuyTime(Date buyTime) {
        this.buyTime = buyTime;
    }

    /**
     * 获取交易哈希值
     *
     * @return tx_hash - 交易哈希值
     */
    public String getTxHash() {
        return txHash;
    }

    /**
     * 设置交易哈希值
     *
     * @param txHash 交易哈希值
     */
    public void setTxHash(String txHash) {
        this.txHash = txHash;
    }

    /**
     * 获取作品哈希值
     *
     * @return work_hash - 作品哈希值
     */
    public String getWorkHash() {
        return workHash;
    }

    /**
     * 设置作品哈希值
     *
     * @param workHash 作品哈希值
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