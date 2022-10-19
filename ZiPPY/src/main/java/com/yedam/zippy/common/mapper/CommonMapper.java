package com.yedam.zippy.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yedam.zippy.common.service.BookmarkVO;
import com.yedam.zippy.common.service.ReportVO;
import com.yedam.zippy.common.service.ReviewBoardVO;


public interface CommonMapper {
  // 찜 추가
  public int addWish(BookmarkVO bookmark);

  // 찜 삭제
  public int delWish(@Param("bNo") int bNo);

  // 해당 상품 찜 출력
  public BookmarkVO getWishOne(@Param("sId") int sId, @Param("email") String email);

  // 찜 전체 출력
  public List<BookmarkVO> getWishAll(@Param("email")String email);
  


  // 후기 등록
  public int addReview(ReviewBoardVO rv);

  // 후기 불러오기
  public List<BookmarkVO> showReview(ReviewBoardVO rv);

  // 신고하기
  public int addReport(ReportVO vo);
}
