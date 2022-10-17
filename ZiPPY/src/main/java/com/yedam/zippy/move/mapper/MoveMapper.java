package com.yedam.zippy.move.mapper;

import java.util.List;

import com.yedam.zippy.move.service.MoveEstimateVO;
import com.yedam.zippy.move.service.MoveImageVO;
import com.yedam.zippy.move.service.MoveRequestVO;

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
  public List<MoveEstimateVO> getEstimateList(MoveEstimateVO vo);
  
  //전체조회 - 유저가 자기 견적 히스토리 조회
  public List<MoveEstimateVO> getEstimateResult(MoveEstimateVO vo);
}

 