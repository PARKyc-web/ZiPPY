package com.yedam.zippy.admin.mapper;

import com.github.pagehelper.Page;
import com.yedam.zippy.member.service.BusinessVO;
import com.yedam.zippy.member.service.GeneralUserVO;

public interface AdminMapper {
  
  public Page<GeneralUserVO> getAllGeneralUser();
  public Page<BusinessVO> getAllBusinessUser();
  
}
