package com.yedam.zippy.admin.service;

import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.yedam.zippy.member.service.BusinessVO;
import com.yedam.zippy.member.service.GeneralUserVO;

@Service
public interface AdminService {
  
    public Page<GeneralUserVO> getAllGeneral();
    
    public Page<BusinessVO> getAllBusiness();
  
}
