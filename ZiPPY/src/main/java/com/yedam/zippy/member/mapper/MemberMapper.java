package com.yedam.zippy.member.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yedam.zippy.member.service.BusinessVO;
import com.yedam.zippy.member.service.GeneralUserVO;
import com.yedam.zippy.member.service.LoginVO;

public interface MemberMapper {
    
    public List<LoginVO> getAllMember();
    
//  회원가입 관련된 Function
    public int emailRedundacyCheck(String email);    
    public void insertLoginInfo(LoginVO vo);
    public void insertGeneralMember(GeneralUserVO vo);    
    public void insertBusinessMember(BusinessVO vo);       
    
//  로그인 관련된 Function
    public LoginVO getLoginInfo(String email);
    public GeneralUserVO getGeneralUser(String email);
    public BusinessVO getBusinessUser(String email);
    
        
//  비밀번호 변경
    public void changePassword(LoginVO login);
    
//  이메일 찾기
    public String findGeneralEmail(@Param("userName") String userName, 
                                   @Param("phoneNumber") String phoneNumber);
    
    public String findBusinessEmail(@Param("userName") String userName, 
                                    @Param("phoneNumber") String phoneNumber);    
    
    
    public void updateUserInfo(GeneralUserVO info);
    
    public void updateBusinessInfo(BusinessVO info);
    
    public void updateProfileImage(@Param("image") String image, @Param("login") LoginVO vo);
    
}
