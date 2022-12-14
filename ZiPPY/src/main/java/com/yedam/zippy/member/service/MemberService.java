package com.yedam.zippy.member.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface MemberService {
  
  public List<LoginVO> getAllMember();  
  
  public Object getMember(String email, int memberType);
  
  public void insertLoginInfo(LoginVO vo);
  
  public void signGeneralMember(LoginVO loginVO, GeneralUserVO generalVO);
  
  public void signBusinessMember(LoginVO loginVO, BusinessVO businessVO, List<MultipartFile> images) ;
  
  public int emailRedundancy(String email);
  
  public Object login(LoginVO login);
  
  public Object findUserByEmail(String email);
  
  public void takeNewPassword(LoginVO info);
  
  public String findUserEmail(String userName, String phoneNumber);
  //사용자의 휴대폰과 이름이 일치하는 내용을 찾아서 리턴해준다
  
  public boolean changePassword(LoginVO info, String password);
  
  public void updateUserInfo(GeneralUserVO info);
  
  public void updateBusinessInfo(BusinessVO info);
  
  public String changeProfileImage(MultipartFile image, String email);
}
