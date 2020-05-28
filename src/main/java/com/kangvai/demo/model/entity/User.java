package com.kangvai.demo.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import javax.persistence.*;

@Table(name = "user")
public class User {
    /**
     * ID
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 帐号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    private String username;

    /**
     * 公钥
     */
    @Column(name = "public_key")
    private String publicKey;

    /**
     * 注册时间
     */
    @Column(name = "reg_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date regTime;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", publicKey='" + publicKey + '\'' +
                ", regTime=" + regTime +
                ", isLocked=" + isLocked +
                '}';
    }

    /**
     * 0-未删除 1-已删除
     */
    @Column(name = "is_locked")
    private Byte isLocked;

    /**
     * 获取ID
     *
     * @return id - ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置ID
     *
     * @param id ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取帐号
     *
     * @return account - 帐号
     */
    public String getAccount() {
        return account;
    }

    /**
     * 设置帐号
     *
     * @param account 帐号
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取昵称
     *
     * @return username - 昵称
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置昵称
     *
     * @param username 昵称
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取公钥
     *
     * @return public_key - 公钥
     */
    public String getPublicKey() {
        return publicKey;
    }

    /**
     * 设置公钥
     *
     * @param publicKey 公钥
     */
    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    /**
     * 获取注册时间
     *
     * @return reg_time - 注册时间
     */
    public Date getRegTime() {
        return regTime;
    }

    /**
     * 设置注册时间
     *
     * @param regTime 注册时间
     */
    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    /**
     * 获取0-未删除 1-已删除
     *
     * @return is_locked - 0-未删除 1-已删除
     */
    public Byte getIsLocked() {
        return isLocked;
    }

    /**
     * 设置0-未删除 1-已删除
     *
     * @param isLocked 0-未删除 1-已删除
     */
    public void setIsLocked(Byte isLocked) {
        this.isLocked = isLocked;
    }
}