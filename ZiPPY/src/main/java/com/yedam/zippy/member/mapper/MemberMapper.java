package com.yedam.zippy.member.mapper;

import java.util.List;

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
    
}
