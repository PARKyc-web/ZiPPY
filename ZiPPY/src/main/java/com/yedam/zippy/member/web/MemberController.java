package com.yedam.zippy.member.web;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
  
    @PostMapping("/login")
    public Object login(@RequestBody LoginVO login) {
      System.out.println(login);
      return service.login(login);
    }
  
    @PostMapping("/gSignUp")
    public String generalSignUp(LoginVO login, GeneralUserVO gVO) {
      service.signGeneralMember(login, gVO);
      
      return "Congratulations";
    }

    @PostMapping("/bSignUp")
    public String businessSignUp(LoginVO login, BusinessVO bVO, List<MultipartFile> images) {  
      System.out.println(login);
      System.out.println(bVO);
      System.out.println(images);
      
      service.signBusinessMember(login, bVO, images);      
  
      return "Congratulations";
    }
    
    @GetMapping("mypage")
    public Object getUserInfo(String email, int memberType) {
      return service.getMember(email, memberType);
    }

}
