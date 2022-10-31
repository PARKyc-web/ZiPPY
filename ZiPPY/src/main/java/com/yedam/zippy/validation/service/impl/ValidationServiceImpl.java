package com.yedam.zippy.validation.service.impl;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.yedam.zippy.member.service.MemberService;
import com.yedam.zippy.validation.service.ValidationService;

import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;

@Service
public class ValidationServiceImpl implements ValidationService{

  @Autowired
  MailSender mailSender;
      
  @Autowired
  MemberService mService;  
  
  private final DefaultMessageService smsService = 
                  NurigoApp.INSTANCE.initialize("NCSWH6V59IRNEKGQ", 
                                                "YA5INLWRXXQOSOFAPHTO6KEJXCZKQNXN", 
                                                "https://api.coolsms.co.kr");
  
  @Override
  public void sendEmail(String email, String subject, String content) {
    SimpleMailMessage msg = new SimpleMailMessage();
    
    msg.setTo(email);
    msg.setSubject(subject);       
    msg.setText(content);
    msg.setFrom("zippynotice@naver.com");    
    mailSender.send(msg);
  }
 
  
  @Override
  public void sendSMS(String phoneNumber, String content) {
    Message sms = new Message();
    
    sms.setFrom("01054068096");
    sms.setTo(phoneNumber);
    sms.setText(content);
    
    SingleMessageSentResponse res = smsService.sendOne(new SingleMessageSendingRequest(sms));    
  }
  
  @Override
  public String getNewPassword() {
    Random rand = new Random();
    
    String key = "";              
    for(int i=0; i<5; i++) {
       key += (char)(rand.nextInt(26)+65);
    }
    
    key += "@";
    key += rand.nextInt(100);
    for(int i=0; i<5; i++) {
      key += (char)(rand.nextInt(26)+97);
   }
    
    return key;
  }
  
}
