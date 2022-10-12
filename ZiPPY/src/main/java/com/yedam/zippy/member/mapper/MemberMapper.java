package com.yedam.zippy.member.mapper;

import java.util.List;

import com.yedam.zippy.member.service.BusinessVO;
import com.yedam.zippy.member.service.GeneralUserVO;
import com.yedam.zippy.member.service.LoginVO;

public interface MemberMapper {
    
    public List<LoginVO> getAllMember();
    public void insertLoginInfo(LoginVO vo);
    public void insertGeneralUser(GeneralUserVO vo);    
    
    public int emailRedundacyCheck(String email);    
    
    public LoginVO getLoginInfo(String email);
    public GeneralUserVO getGeneralUser(String email);
    public BusinessVO getBusinessUser(String email);
    
}
