package com.yedam.zippy.common.service;

import org.springframework.stereotype.Service;

@Service
public interface CommonService {
  // 찜 추가
  public String addWish(BookmarkVO bookmark);

  // 후기 등록
  public String addReview();

  // 후기 불러오기
  public String showReview();

}
