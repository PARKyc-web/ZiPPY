package com.yedam.zippy.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.zippy.common.mapper.CommonMapper;
import com.yedam.zippy.common.service.BookmarkVO;
import com.yedam.zippy.common.service.CommonService;
import com.yedam.zippy.common.service.ReportVO;

import com.yedam.zippy.common.service.ReviewBoardVO;

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
  public BookmarkVO getWishOne(int sId, String email, int serviceType) {
    return mapper.getWishOne(sId, email, serviceType);
  }

  @Override
  public List<BookmarkVO> getWishAll(String email, int serviceType) {
    return mapper.getWishAll(email,serviceType);
  }

  @Override
  public int addReview( ReviewBoardVO rv) {
    return mapper.addReview(rv);
  }

  @Override
  public List<ReviewBoardVO> showReview(ReviewBoardVO rv) {
    return showReview(rv);
  }
  
  @Override
  public int addReport(ReportVO vo) {
    return mapper.addReport(vo);
  }

}
