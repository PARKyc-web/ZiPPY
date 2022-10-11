package com.yedam.zippy.member.web;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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
       public String generalSignUp(@RequestBody Map<String, String> userInfo) {
           System.out.println("General-Sign-Up RUN!");
          
           System.out.println(userInfo);
           System.out.println(userInfo.get("info"));
           
           Gson gson = new Gson();
           Map<String, String> data = gson.fromJson(userInfo.get("info"), Map.class);
                      
           for(String x : data.keySet()) {
             System.out.println(x + " : " + data.get(x));
           }           
           
          String edcodedPwd = "";
          
          try {
            edcodedPwd = URLEncoder.encode(data.get("user_password"), "UTF-8");
          } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
            System.out.println(e1);
          }
           
           LoginVO login = new LoginVO();
           login.setEmail(data.get("user_email"));
           login.setPassword(edcodedPwd);           
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
	   
	   @PostMapping("bSignUp")
	   public String businessSignUp(@RequestBody Map<String, String> userInfo) {
	     
	     System.out.println(userInfo);
         Gson gson = new Gson();
         Map<String, String> data = gson.fromJson(userInfo.get("info"), Map.class);
                    
         for(String x : data.keySet()) {
           System.out.println(x + " : " + data.get(x));
         } 
	     
         String edcodedPwd = "";
         
         try {
           edcodedPwd = URLEncoder.encode(data.get("user_password"), "UTF-8");
         } catch (UnsupportedEncodingException e1) {
           e1.printStackTrace();
           System.out.println(e1);
         }
         
//         LoginVO login = new LoginVO();
//         login.setEmail(data.get("user_email"));
//         login.setPassword(edcodedPwd);           
//         login.setIsSocial(0);
//         login.setMemberType(0);
//	     
//         BusinessVO bVO = new BusinessVO();
//         bVO.setBusinessEmail(data.get("user_email"));
//         bVO.setCompName(data.get("company_name"));
//         bVO.setCompIntro(data.get("company_intro"));
//         bVO.setCeoName(data.get("ceo_name"));
//         bVO.setCompAddress(data.get("user_addr"));
//         
//         bVO.setBusinessId(Integer.parseInt(data.get("business_number")));
//         
//         bVO.setPhone(data.get("user_phone"));
//         bVO.setProfilePic("Default.png");
//         
//         bVO.setBrokerId(Integer.parseInt(data.get("broker_id")));
//         bVO.setBrokerImg("BrokerImg");
//         
//         bVO.setZipCode(data.get("addr_postzone"));
         
	     return "Congratulations To Sign-Up SITE";
	   }
	
}
