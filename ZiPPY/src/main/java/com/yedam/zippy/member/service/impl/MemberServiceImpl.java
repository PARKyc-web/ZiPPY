package com.yedam.zippy.member.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yedam.zippy.member.mapper.MemberMapper;
import com.yedam.zippy.member.service.BusinessVO;
import com.yedam.zippy.member.service.GeneralUserVO;
import com.yedam.zippy.member.service.LoginVO;
import com.yedam.zippy.member.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService{
    
    @Autowired
    MemberMapper mapper;
      
    
    @Override
    public Object login(LoginVO login) {      
      // login table에서 정보 가져오기
      LoginVO loginInfo = mapper.getLoginInfo(login.getEmail());
      
      if(loginInfo == null) {
        return null;
      }
      // 비밀번호 DECODE
//      String password = "";
//      try {
//        password = URLDecoder.decode(loginInfo.getPassword(), "UTF-8");
//      } catch (UnsupportedEncodingException e) {
//        e.printStackTrace();
//      } 
      
      // 로그인정보에서 비밀번호가 같다면? > 멤버타입을 확인
      if(loginInfo.getPassword().equals(login.getPassword())) {
        
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
    public void insertGeneralMember(LoginVO loginVO, GeneralUserVO gVO) {      
        insertLoginInfo(loginVO);
        mapper.insertGeneralUser(gVO);
    }
    
    @Transactional
    @Override
    public void insertBusinessMember(LoginVO loginVO, BusinessVO bVO) {
      insertLoginInfo(loginVO);      
      
    }   
    
    @Override
    public int emailRedundancy(String email) {
      return mapper.emailRedundacyCheck(email);
    }
}
