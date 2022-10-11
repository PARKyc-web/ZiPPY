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
