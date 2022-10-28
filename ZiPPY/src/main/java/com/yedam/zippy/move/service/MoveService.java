package com.yedam.zippy.move.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.Page;
import com.yedam.zippy.common.service.BookmarkVO;

@Service
public interface MoveService {

  //
  public int moveUntactCheck(MoveRequestVO moveReq, List<MultipartFile> images1, List<MultipartFile> images2, List<MultipartFile> images3);
  
  public int moveContactCheck(MoveRequestVO moveReq); 
  
  public void insertUntactEstimate(MoveRequestVO moveReq, List<MultipartFile> images1, List<MultipartFile> images2, List<MultipartFile> images3);
  
  public void insertContactEstimate(MoveRequestVO moveReq);
  
  //전체조회 - 업체
  public List<MoveRequestVO> getEstimateList(MoveEstimateVO vo);

  //전체조회 - 유저
//  public Page<MoveEstimateVO> getEstimateResult(MoveEstimateVO vo);
  public List<MoveEstimateVO> getEstimateResult(MoveEstimateVO vo);
  
  
  //비대면 사진조회
  public List<MoveImageVO> selectAllPhoto(MoveImageVO vo);
  
  //받은 견적에서 견적전, 견적후 구분 - 업체
  public List<MoveCompanyEstimateVO> moveWhether(MoveCompanyEstimateVO vo);
  
  
  
  //견적서 인서트 - 업체(1차)
  public int makeEstimate(MoveResponseVO vo);
  
//견적상태 업데이트 (견적요청후, 상태 0으로 변경)
  public int moveStatusUpdateZero(MoveRequestVO vo);
  
  //견적상태 업데이트 (1차 견적서 발송후, 상태 1로 변경)
//  public int moveStatusUpdate(MoveResponseVO vo);
  public int moveStatusUpdate(@Param("estimateNo")Integer estimateNo, @Param("email")String email);
  
  //견적서 수정 - 업체 (2차견적)
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
  
  //해당찜조회
  public List<BookmarkVO> getWishOneList(@RequestParam int sId, @RequestParam String email, @RequestParam int serviceType); 
   
  //후기 출력
   public List<MoveReviewVO> showReview(MoveReviewVO vo);
   
 //후기 개수
   public List<MoveReviewVO> moveRvCount(@Param("serviceId")String serviceId);
   
}
