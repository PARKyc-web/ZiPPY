package com.yedam.zippy.member.service;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface MemberService {
  
  public List<LoginVO> getAllMember();  
  
  public void insertLoginInfo(LoginVO vo);
  
  public void insertGeneralMember(LoginVO loginVO, GeneralUserVO generalVO);
  
  public void insertBusinessMember(LoginVO loginVO, BusinessVO businessVO);
  
  public int emailRedundancy(String email);
  
  public Object login(LoginVO login);
}
