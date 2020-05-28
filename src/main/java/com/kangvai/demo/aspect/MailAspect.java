package com.kangvai.demo.aspect;

import com.kangvai.demo.model.vo.MailVo;
import com.kangvai.demo.service.MailService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;

/**
 * @author kangvai
 * @date 2020/5/28 18:23
 */
@Aspect
@Component
public class MailAspect {
    @Autowired
    private MailService mailService;

    /**
     * 这里我们使用注解的形式
     * 当然，我们也可以通过切点表达式直接指定需要拦截的package,需要拦截的class 以及 method
     * 切点表达式:   execution(...)
     */
    @Pointcut("@annotation(com.kangvai.demo.anno.MailAnno)")
    public void mailPointCut() {}

    /**
     * 后置通知 @Around  ， 当然也可以使用 @Before (前置通知)  @After (后置通知)
     * @param point
     * @return
     * @throws Throwable
     */
    @After("mailPointCut()")
    public void afterMethod(JoinPoint point) throws Throwable {
        Object[] args = point.getArgs();
        String[] argNames =  ((MethodSignature)point.getSignature()).getParameterNames();
        String account = "";
        for(int i=0; i<argNames.length; i++) {
            if(argNames[i].equals("account")) {
                account = (String) args[i];
                break;
            }
        }
        sendEmail(account);
    }

    public void sendEmail(String to) {
        MailVo mail = new MailVo();
        String filePath = "src/main/resources/static/user/dist/img/welcome.gif";
        File file = new File(filePath);
        String from = mailService.getMailSendFrom();
        ArrayList<File> files = new ArrayList<>();
        files.add(file);
        mail.setFrom(from);
        mail.setTo(to);
        mail.setSubject("个人版权系统注册");
        mail.setText("您已成功注册个人版权系统，请牢记个人账号密码，切勿透露给第三方使用，由此造成的损失个人承担");
        mail.setFiles(files);
        mailService.sendMail(mail);
    }

}
