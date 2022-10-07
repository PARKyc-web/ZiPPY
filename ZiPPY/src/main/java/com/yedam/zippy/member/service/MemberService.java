package com.yedam.zippy.member.service;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface MemberService {
  
  public List<LoginVO> getAllMember();
}
