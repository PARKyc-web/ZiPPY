package com.yedam.zippy.common.service;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface CommonService {
  // 찜 추가
  public int addWish(BookmarkVO bookmark);

  //해당 상품 찜 출력
  public BookmarkVO getWishOne(int sId, String email);

  // 찜 전체 출력
  public List<BookmarkVO> getWishAll(String email);

  // 찜 삭제
  public int delWish(int bNo);

  // 후기 등록
  public String addReview();

  // 후기 불러오기
  public String showReview();

}
