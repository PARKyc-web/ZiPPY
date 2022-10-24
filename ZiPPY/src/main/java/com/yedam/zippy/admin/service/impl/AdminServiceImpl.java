package com.yedam.zippy.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.yedam.zippy.admin.mapper.AdminMapper;
import com.yedam.zippy.admin.service.AdminService;
import com.yedam.zippy.member.service.BusinessVO;
import com.yedam.zippy.member.service.GeneralUserVO;

@Service
public class AdminServiceImpl implements AdminService{
   
  @Autowired
  AdminMapper mapper;
  
  @Override
  public Page<GeneralUserVO> getAllGeneral() {    
    return mapper.getAllGeneralUser();
  }
  
  @Override
  public Page<BusinessVO> getAllBusiness() {
    return mapper.getAllBusinessUser();
  }
  
  
}
