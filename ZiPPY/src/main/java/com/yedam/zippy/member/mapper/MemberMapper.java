package com.yedam.zippy.member.mapper;

import java.util.List;

import com.yedam.zippy.member.service.LoginVO;

public interface MemberMapper {
    
    public List<LoginVO> getAllMember();
}
