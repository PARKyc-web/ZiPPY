package com.yedam.zippy.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.zippy.common.mapper.CommonMapper;
import com.yedam.zippy.common.service.BookmarkVO;
import com.yedam.zippy.common.service.CommonService;

@Service
public class CommonServiceIml implements CommonService {
  
  @Autowired
  CommonMapper mapper;

  @Override
  public String addWish(BookmarkVO bookmark) {
    return mapper.addWish(bookmark);
  }
}
