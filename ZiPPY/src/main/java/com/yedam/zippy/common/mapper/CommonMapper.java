package com.yedam.zippy.common.mapper;

import com.yedam.zippy.common.service.BookmarkVO;

public interface CommonMapper {
  // 찜 추가
  public String addWish(BookmarkVO bookmark);

  // 후기 등록
  public String addReview();

  // 후기 불러오기
  public String showReview();

}
