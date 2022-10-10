package com.yedam.zippy.member.web;

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
           
//           LoginVO login = new LoginVO();
//           login.setEmail(data.get("user_email"));
//           login.setPassword(data.get("user_password"));
//           login.setMemberType(0);
//           login.setIsSocial(0);        
//           
//           
//           GeneralUserVO gVO = new GeneralUserVO();
//           gVO.setUserEmail(data.get("user_email"));
//           gVO.setUserName(data.get("user_name"));
//           gVO.setNickName(null);
//           gVO.setPhoneNumber(null);
//           gVO.setUserBirth(null);
//           gVO.setGender(null);
//           gVO.setUserAddress(null);           
//           gVO.setAddressDetail(null);           
//           gVO.setUserState(null);
//           gVO.setZipCode(null);
//           gVO.setProfileImage(null);                      
//           
//           service.insertGeneralMember(login, gVO);
           
           return "Congratulations To Sign-Up SITE";
       }  
	
}
