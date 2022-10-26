package com.yedam.zippy.member.service.impl;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.yedam.zippy.common.service.CommonService;
import com.yedam.zippy.member.mapper.MemberMapper;
import com.yedam.zippy.member.service.BusinessVO;
import com.yedam.zippy.member.service.GeneralUserVO;
import com.yedam.zippy.member.service.LoginVO;
import com.yedam.zippy.member.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService{
    
    private final String imageFolder = "D:/businessImage";  
  
    @Autowired
    MemberMapper mapper;    
    
    @Autowired
    CommonService commonService;
    
    @Override
    public Object login(LoginVO login) {      
      // login table에서 정보 가져오기
      LoginVO loginInfo = mapper.getLoginInfo(login.getEmail());
      
      if(loginInfo == null) {
        return null;
      }
      
      // 들어온 비밀번호를 Encode >> 같은가?
      String password = encodingPassword(login.getPassword());     
      
      // 로그인정보에서 비밀번호가 같다면? > 멤버타입을 확인
      if(loginInfo.getPassword().equals(password)) {
        
        // 멤버타입이 0이라면 generalUser table에 email로 가져오고 return
        if(loginInfo.getMemberType() == 0) {          
          return mapper.getGeneralUser(login.getEmail());
          
        }else if(loginInfo.getMemberType() == 1){
          // 멤버타입이 1이라면 business table에서 email로 가져오고 return    
          return mapper.getBusinessUser(login.getEmail());          
          
        }else {
          return null;
        }
        
      }else {        
        return null;
      }
    }    
    
    @Override    
    public List<LoginVO> getAllMember() {        
        return mapper.getAllMember();
    }
    
    @Override
    public Object getMember(String email, int memberType) {      
      if(memberType == 0) {
        return mapper.getGeneralUser(email);
        
      }else if(memberType != 0) {
        return mapper.getBusinessUser(email);
        
      }   
      return null;
    }
    
    @Override
    public void insertLoginInfo(LoginVO vo) {
        vo.setPassword(encodingPassword(vo.getPassword()));   
        mapper.insertLoginInfo(vo);        
    }
    
    @Transactional
    @Override
    public void signGeneralMember(LoginVO loginVO, GeneralUserVO gVO) {       
        insertLoginInfo(loginVO);
        mapper.insertGeneralMember(gVO);
    }
    
    @Transactional
    @Override
    public void signBusinessMember(LoginVO loginVO, BusinessVO bVO, List<MultipartFile> images) {
        insertLoginInfo(loginVO);        
        String businessIdImage = commonService.saveImage(images.get(0), "member");
        String brokerIdImage = null;
        
        if(bVO.getMemberType() == 0) {          
          brokerIdImage = commonService.saveImage(images.get(1), "member");
        }
        
        System.out.println("=== 사진의 경로!! ");
        System.out.println("사업자등록증 >> " + businessIdImage);
        System.out.println("중개등록사업자 사진 >> " + brokerIdImage);        
        
        // 경로 세팅
        bVO.setBusinessImg(businessIdImage);
        bVO.setBrokerImg(brokerIdImage);        
        
        // 비지니스 테이블에 데이터 저장        
        mapper.insertBusinessMember(bVO);
    }   
    
    @Override
    public int emailRedundancy(String email) {
      return mapper.emailRedundacyCheck(email);
    }    
    
    @Override
    public Object findUserByEmail(String email) {  
        return mapper.getLoginInfo(email);
    }
    
    
    @Override
    public void takeNewPassword(LoginVO login) {
        System.out.println("===== 새로운 비밀번호는 이거다!! >> " + login.getPassword());
        login.setPassword(encodingPassword(login.getPassword()));
        mapper.changePassword(login);
    }       
    
    
    private String encodingPassword(String password) {        
      StringBuffer sb = new StringBuffer();
      
      try {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes());        
        byte[] date = md.digest();
        
        for(byte b : date) {
          sb.append(String.format("%02x", b));
        }
        
      }catch (Exception e) {
        e.getStackTrace();
      }
      
      return sb.toString();       
    }
    
    
    @Override
    public String findUserEmail(String userName, String phoneNumber) {      
      String userEmail ="";      
      userEmail = mapper.findGeneralEmail(userName, phoneNumber);
      
      if(userEmail == null || userEmail.length() == 0) {
        userEmail = mapper.findBusinessEmail(userName, phoneNumber);
      }
      
      return userEmail;
    }
    
    
    @Override
    public boolean changePassword(LoginVO info, String password) {
      
      LoginVO lVO = mapper.getLoginInfo(info.getEmail());      
      // 기존 패스워드가 같다면
      
      if(lVO.getPassword().equals(encodingPassword(info.getPassword()))) {        
        info.setPassword(encodingPassword(password));
        mapper.changePassword(info);
        return true;
      }            
      
      return false;
    }
    
    @Override
    public void updateUserInfo(GeneralUserVO info) {      
         mapper.updateUserInfo(info);
    }
    
    @Override
    public void updateBusinessInfo(BusinessVO info) {
        mapper.updateBusinessInfo(info);
    }
    
    @Override
    public String changeProfileImage(MultipartFile image, String email) {
      String name = commonService.saveImage(image, "member");
      mapper.updateProfileImage(name, email);      
      return name;
    }
}
