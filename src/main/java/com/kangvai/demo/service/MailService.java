package com.kangvai.demo.service;

import com.kangvai.demo.model.vo.MailVo;

/**
 * @author kangvai
 * @date 2020/5/28 18:14
 */
public interface MailService {

    public MailVo sendMail(MailVo mailVo);

    public String getMailSendFrom();
}
