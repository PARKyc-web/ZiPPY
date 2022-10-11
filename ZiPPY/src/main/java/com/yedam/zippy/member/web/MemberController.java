package com.yedam.zippy.member.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
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
       public String businessLoginPPost(@RequestBody Map<String, String> userInfo) {
           System.out.println("General-Sign-Up RUN!");
           
//          service 실행 => db 넣는 부분
           System.out.println(userInfo);
           System.out.println(userInfo.get("info"));
           
           Gson gson = new Gson();
           Map<String, String> data = gson.fromJson(userInfo.get("info"), Map.class);
                      
           for(String x : data.keySet()) {
             System.out.println(x + " : " + data.get(x));
           }           
           
           LoginVO login = new LoginVO();
           login.setEmail(data.get("user_email"));
           login.setPassword(data.get("user_password"));           
           login.setIsSocial(0);
           login.setMemberType(0);
           
           GeneralUserVO gVO = new GeneralUserVO();
           gVO.setUserEmail(data.get("user_email"));
           gVO.setUserName(data.get("user_name"));
           gVO.setNickName(data.get("user_nick"));
           gVO.setPhoneNumber(data.get("user_phone"));
           
           try {
             gVO.setUserBirth(new SimpleDateFormat("yyyy-MM-dd").parse(data.get("user_birth")));
          } catch (ParseException e) {
            e.printStackTrace();
          }  
//           ;
           gVO.setUserGender(data.get("user_gender"));
           gVO.setUserAddress(data.get("user_addr"));           
           gVO.setAddressDetail(data.get("addr_detail"));           
           gVO.setZipCode(data.get("addr_postzone"));
           gVO.setUserState(0);           
           gVO.setProfileImage("default.png");                      
           
           System.out.println("LoginVO info > " + login);
           System.out.println("GeneralUserVO info > " + gVO);           
           service.insertGeneralMember(login, gVO);
           
           return "Congratulations To Sign-Up SITE";
       }  
	
}
