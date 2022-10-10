package com.yedam.zippy.member.service.impl;

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
    public List<LoginVO> getAllMember() {        
        return mapper.getAllMember();
    }
    
    @Override
    public String insertLoginInfo(LoginVO vo) {
        
        return null;
    }
    
    @Transactional
    @Override
    public String insertGeneralMember(LoginVO loginVO, GeneralUserVO gVO) {      
        insertLoginInfo(loginVO);
      
        return null;
    }
    
    @Transactional
    @Override
    public String insertBusinessMember(LoginVO loginVO, BusinessVO bVO) {
      insertLoginInfo(loginVO);
      
      return null;
    }   
    
    
}
