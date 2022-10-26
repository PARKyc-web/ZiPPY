package com.yedam.zippy.validation.service;

import org.springframework.stereotype.Service;

@Service
public interface ValidationService {

  
    public void sendEmail(String email, String subject, String content);
    public void sendSMS(String phoneNumber, String content);
  
}
