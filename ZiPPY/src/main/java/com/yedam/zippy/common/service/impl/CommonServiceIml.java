package com.yedam.zippy.common.service.impl;

import java.util.List;

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
  public int addWish(BookmarkVO bookmark) {
    return mapper.addWish(bookmark);
  }
  
  @Override
  public int delWish(int bNo) {
    return mapper.delWish(bNo);
  }
  
  @Override
  public BookmarkVO getWishOne(int sId, String email) {
    return mapper.getWishOne(sId, email);
  }

  @Override
  public List<BookmarkVO> getWishAll(String email) {
    return mapper.getWishAll(email);
  }
  
  @Override
  public String addReview() {
    return mapper.addReview();
  }

  @Override
  public String showReview() {
    return showReview();
  }

}
