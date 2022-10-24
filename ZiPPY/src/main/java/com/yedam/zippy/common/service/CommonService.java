package com.yedam.zippy.common.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.Page;

@Service
public interface CommonService {
  // 찜 추가
  public int addWish(BookmarkVO bookmark);

  //해당 상품 찜 출력
  public BookmarkVO getWishOne(int sId, String email, int serviceType);

  // 찜 전체 출력
  public Page<BookmarkVO> getWishAll(String email, int serviceType);

  // 찜 삭제
  public int delWish(int bNo);

  // 후기 등록
  public int addReview( ReviewBoardVO rv);
  
  // 신고하기
  public int addReport(ReportVO vo);
 
  // 후기 불러오기
  public List<ReviewBoardVO> showReview(ReviewBoardVO rv);


  //신고내역보기
  public List<ReportVO> showReportList();

  
  // 상품 후기 불러오기
  // public List<ReviewBoardVO> showProReview(int serviceType, int serviceId);
  //상품 후기 불러오기
 public Page<ReviewBoardVO> showProReview(int serviceType, int serviceId);
 
 
 public String saveImage(MultipartFile image, String type);
 public void getImage(HttpServletResponse response, String imageName, String type) throws Exception;
}
