package com.yedam.zippy.move.mapper;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;

import com.yedam.zippy.move.service.MoveCompanyEstimateVO;
import com.yedam.zippy.move.service.MoveEstimateVO;
import com.yedam.zippy.move.service.MoveImageVO;
import com.yedam.zippy.move.service.MoveMyListVO;
import com.yedam.zippy.move.service.MoveRequestVO;
import com.yedam.zippy.move.service.MoveResponseVO;

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
  public List<MoveEstimateVO> getEstimateResult(MoveEstimateVO vo);
  
  //견적서 인서트 - 업체(1차견적까지)
  public int makeEstimate(MoveResponseVO vo);
  
  //견적상태 업데이트 (1차 견적서 발송후, 상태 1로 변경)
  public int moveStatusUpdate(MoveResponseVO vo);
  
  //견적서 수정 - 업체
  public int moveEstimateUpdate(MoveResponseVO vo);
  
  //견적서 조회 - 업체
  public List<MoveResponseVO> companyEstimate(MoveCompanyEstimateVO vo);
  
  //받은 견적 조회 - 사용자
  public List<MoveMyListVO> getMyEstimateList(MoveMyListVO vo);
}

 