package com.kangvai.demo.model;

import com.kangvai.demo.model.entity.User;

import javax.persistence.Table;

/**
 * User实体的子类
 * @author kangvai
 * @date 2020/4/30 16:21
 */
@Table(name = "user")
public class UserDto extends User {

    @Override
    public String toString() {
        return super.toString();
    }
}
