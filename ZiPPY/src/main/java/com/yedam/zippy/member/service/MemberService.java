package com.yedam.zippy.member.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface MemberService {
  
  public List<LoginVO> getAllMember();  
  
  public void insertLoginInfo(LoginVO vo);
  
  public void signGeneralMember(LoginVO loginVO, GeneralUserVO generalVO);
  
  public void signBusinessMember(LoginVO loginVO, BusinessVO businessVO, List<MultipartFile> images) ;
  
  public int emailRedundancy(String email);
  
  public Object login(LoginVO login);
  
  public Object findUserByEmail(String email);
  
  public void changePassword(LoginVO info);
}
