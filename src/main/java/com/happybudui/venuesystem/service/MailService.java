package com.happybudui.venuesystem.service;

import com.happybudui.venuesystem.mapper.UserMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Random;

//CopyRight © 2018-2018 Happybudui All Rights Reserved.
//Written by Happybudui

@Service
public class MailService {
    private final UserMapper userMapper;
    private final RedisService redisService;

    private static Logger logger= LoggerFactory.getLogger(UserService.class);

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${mysite.url}")
    private String mySiteUrl;

    @Value("${mysite.mailAddr}")
    public String myMailAddr;

    @Autowired
    public MailService(UserMapper userMapper,RedisService redisService){
        this.userMapper=userMapper;
        this.redisService=redisService;
    }

    //向用户发送激活邮件
    public void sendActiveMail(String userId,String userMail) {
        String maskId = getRandomString(6);
        String verCode =getRandomString(10);

        redisService.set(maskId,userId);
        redisService.setValidTime(maskId,3600*24);
        redisService.set(userId,verCode);
        redisService.setValidTime(userId,3600*24);

        String userName= userMapper.getUserInfoById(userId).getUserName();

        //发送邮件
        Context context = new Context();
        context.setVariable("userName", userName);
        context.setVariable("maskId",maskId);
        context.setVariable("verCode",verCode);

        String emailContent = templateEngine.process("mailvertemplate",context);
        logger.info(emailContent);

        sendHtmlMail(userMail,"请验证您的邮箱",emailContent);
    }

    //验证激活邮件是否有效
    public boolean verMail(String maskId,String verCode){
        String userId =redisService.get(maskId);
        if(userId==null)
            return false;
        String realVerCode = redisService.get(userId);

        if(realVerCode.equals(verCode)) {
            redisService.setValidTime(maskId,1);
            redisService.setValidTime(userId,1);
            userMapper.updateUserMailActiveStatus(userId);
            return true;
        }
        else
            return false;
    }

    // 向用户发送修改密码的验证码
    public void sendVerCodeMail(String userMail) {
        String verCode = getRandomString(6);

        redisService.set(userMail, verCode);
        redisService.setValidTime(userMail, 3600*24);

        Context context = new Context();
        context.setVariable("VerificationCode", verCode);
        String emailContent = templateEngine.process("mailfindpasswordtemplate", context);
        logger.info(emailContent);

        sendHtmlMail(userMail, "验证码已发送至您的邮箱", emailContent);
    }

    // 验证是否验证码输入正确
    public boolean verCodeMail(String userMail,String enterCode){
        String verCode = redisService.get(userMail);
        if(verCode.equals(enterCode)){
            redisService.setValidTime(userMail,1);
            return true;
        } else{
            return false;
        }
    }

    //发送邮件的接口
    @Async("asyncServiceExecutor")
    public void sendHtmlMail(String to, String subject, String content) {
        MimeMessage message = mailSender.createMimeMessage();

        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(myMailAddr);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            mailSender.send(message);
            logger.info("html邮件发送成功");
        } catch (MessagingException e) {
            logger.error("发送html邮件时发生异常！", e);
        }
    }

    //生成随机字符串
    @org.jetbrains.annotations.NotNull
    public static String getRandomString(int length){
        String str="zxcvbnmlkjhgfdsaqwertyuiopQWERTYUIOPASDFGHJKLZXCVBNM1234567890";

        Random random=new Random();
        StringBuffer sb=new StringBuffer();

        for(int i=0; i<length; ++i){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }

        return sb.toString();
    }
}
