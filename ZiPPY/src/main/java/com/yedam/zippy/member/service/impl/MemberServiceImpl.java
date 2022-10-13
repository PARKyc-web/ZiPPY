package com.yedam.zippy.member.service.impl;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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
      
    
    @Override
    public Object login(LoginVO login) {      
      // login table에서 정보 가져오기
      LoginVO loginInfo = mapper.getLoginInfo(login.getEmail());
      
      if(loginInfo == null) {
        return null;
      }
//       비밀번호 DECODE
      String password = "";
      try {
        password = URLDecoder.decode(loginInfo.getPassword(), "UTF-8");
      } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
      } 
      
      // 로그인정보에서 비밀번호가 같다면? > 멤버타입을 확인
      if(password.equals(login.getPassword())) {
        
        // 멤버타입이 0이라면 generalUser table에 email로 가져오고 return
        if(loginInfo.getMemberType() == 0) {          
          return mapper.getGeneralUser(login.getEmail());
          
        }else {
          // 멤버타입이 1이라면 business table에서 email로 가져오고 return    
          return mapper.getBusinessUser(login.getEmail());
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
    public void insertLoginInfo(LoginVO vo) {
        mapper.insertLoginInfo(vo);        
    }
    
    @Transactional
    @Override
    public void signGeneralMember(LoginVO loginVO, GeneralUserVO gVO) {
        String encodeingPassword = "";
        
        try {
          encodeingPassword = URLEncoder.encode(loginVO.getPassword(), "UTF-8");
          loginVO.setPassword(encodeingPassword);          
          System.out.println(encodeingPassword);
        } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
        }
        
        insertLoginInfo(loginVO);
        mapper.insertGeneralMember(gVO);
    }
    
    @Transactional
    @Override
    public void signBusinessMember(LoginVO loginVO, BusinessVO bVO, List<MultipartFile> images) {
        insertLoginInfo(loginVO);        
        String businessIdImage = storeImages(bVO, images.get(0));
        String brokerIdImage = null;
        
        if(bVO.getMemberType() == 0) {          
          brokerIdImage = storeImages(bVO, images.get(1));
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
    
    
    
    private String storeImages(BusinessVO bVO, MultipartFile image) {
      
      File folder = new File(imageFolder);      
      if(!folder.exists()) {        
         try {
           folder.mkdir();
           
         }catch (Exception e) {
           e.getStackTrace();
        }
      }
      
      long now = System.currentTimeMillis();
      Random rand = new Random();
      
      String imagePath = bVO.getBusinessId() + "_" + now + rand.nextInt(10) + image.getOriginalFilename();     
      
      File write = new File(imageFolder + File.separator + imagePath);      
      System.out.println("저장할 위치 :: " + write.toString());
      try {
        image.transferTo(write);
      } catch (Exception e) {
        e.printStackTrace();
      }
      
      return imagePath;      
    }
}
