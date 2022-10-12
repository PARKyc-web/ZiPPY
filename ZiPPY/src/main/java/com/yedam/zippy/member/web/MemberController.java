package com.yedam.zippy.member.web;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yedam.zippy.member.service.BusinessVO;
import com.yedam.zippy.member.service.GeneralUserVO;
import com.yedam.zippy.member.service.LoginVO;
import com.yedam.zippy.member.service.MemberService;

@CrossOrigin
@RestController
@RequestMapping("/member")
public class MemberController {

  @Autowired
  MemberService service;

  @PostMapping("gSignUp")
  public String generalSignUp(LoginVO login, GeneralUserVO gVO) {    
    System.out.println(login);
    System.out.println(gVO);
    
    return "Congratulations";
  }

  @PostMapping("bSignUp")
  public String businessSignUp(LoginVO login,  BusinessVO bVO, List<MultipartFile> images) {
        
    System.out.println(login);
    System.out.println(bVO);    
    System.out.println(images);
    
    File file = new File("D:/imageTest.jpg");
    try {
      images.get(0).transferTo(file);      
    } catch (Exception e) {
      e.printStackTrace();
    }     
    
    return "";
  }
  
  

  
  

}
