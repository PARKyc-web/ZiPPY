package com.yedam.zippy.member.service;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface MemberService {
  
  public List<LoginVO> getAllMember();  
  
  public String insertLoginInfo(LoginVO vo);
  
  public String insertGeneralMember(LoginVO loginVO, GeneralUserVO generalVO);
  
  public String insertBusinessMember(LoginVO loginVO, BusinessVO businessVO);
}
