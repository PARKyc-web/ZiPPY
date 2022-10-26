package com.yedam.zippy.move.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.Page;
import com.yedam.zippy.common.service.BookmarkVO;
import com.yedam.zippy.move.service.MoveCompanyEstimateVO;
import com.yedam.zippy.move.service.MoveEstimateVO;
import com.yedam.zippy.move.service.MoveImageVO;
import com.yedam.zippy.move.service.MoveMyListVO;
import com.yedam.zippy.move.service.MoveRequestVO;
import com.yedam.zippy.move.service.MoveResponseVO;
import com.yedam.zippy.move.service.MoveReviewVO;

public interface MoveMapper {
  
  //비대면 정보입력
  public int moveUntactCheck(MoveRequestVO moveReq);
  
  //대면 정보입력
  public int moveContactCheck(MoveRequestVO moveReq);
  
  
  //
  public void insertContactEstimate(MoveRequestVO moveReq);
  
  public void insertImagePath(String path);
  
  public void insertPhoto(MoveImageVO img);


  //전체조회 -업체가 견적 요청 조회
  public List<MoveRequestVO> getEstimateList(MoveEstimateVO vo);
  
  //전체조회 - 유저가 자기 견적 히스토리 조회
  public Page<MoveEstimateVO> getEstimateResult(MoveEstimateVO vo);
  
  //비대면 사진조회
  public List<MoveImageVO> selectAllPhoto(MoveImageVO vo);
  
  //견적서 인서트 - 업체(1차견적까지)
  public int makeEstimate(MoveResponseVO vo);
  
//견적상태 업데이트 (견적요청후, 상태 0으로 변경)
  public int moveStatusUpdateZero(MoveRequestVO vo);
  
  //견적상태 업데이트 (1차 견적서 발송후, 상태 1로 변경)
//  public int moveStatusUpdate(MoveResponseVO vo);
  public int moveStatusUpdate(@Param("estimateNo")Integer estimateNo, @Param("email")String email);
    
  
  //견적서 수정 - 업체
  public int moveEstimateUpdate(MoveResponseVO vo);
  
  //견적상태 업데이트 (2차 견적서 발송후, 상태 2로 변경)
  public int moveStatusSecondUpdate(MoveResponseVO vo);
  
  //견적상태 업데이트 (사용자가 예약요청 후, 상태 3으로 변경)
  public int moveStatusThirdUpdate(MoveResponseVO vo);
  
  //견적상태 업데이트 (업체가 예약확정 후, 상태 4으로 변경)
  public int moveStatusFourthUpdate(MoveResponseVO vo);
  
  //견적상태 업데이트 (이사완료 후, 상태 5으로 변경)
  public int moveStatusFifthUpdate(MoveResponseVO vo);
  
  //견적상태 업데이트 (예약취소하면, 상태 9으로 변경)
  public int moveStatusCancleUpdate(MoveResponseVO vo);
  
  //견적서 조회 - 업체
  public List<MoveResponseVO> companyEstimate(MoveCompanyEstimateVO vo);
  
  //받은 견적 조회 - 사용자
  public List<MoveMyListVO> getMyEstimateList(MoveMyListVO vo);
  
  //사용자가 받은 견적 리스트 확인 - 단건
  public MoveMyListVO getMyEstimateListOne(@Param("movingResponseNo")Integer movingResponseNo, @Param("userEmail")String userEmail);
  
  //사용자의 예약내역 확인 (요청:3, 예약확정:4, 이사완료:5)
  public List<MoveMyListVO> moveReserve(MoveMyListVO vo);
  
  //업체조회 페이지
  public List<MoveMyListVO> getCompanyList(MoveMyListVO vo);
  
//업체조회 페이지 - 단건
  public List<MoveMyListVO> moveCompanyOne(MoveMyListVO vo);
  
    //wish
  //해당찜조회
  public List<BookmarkVO> getWishOneList(@RequestParam int sId, @RequestParam String email, @RequestParam int serviceType); 
    

  // 후기 출력
  public List<MoveReviewVO> showReview(@Param("serviceId")String serviceId);
  
//후기 개수
 public List<MoveReviewVO> moveRvCount(@Param("serviceId")String serviceId);
 
} 

 