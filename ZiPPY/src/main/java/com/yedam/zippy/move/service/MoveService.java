package com.yedam.zippy.move.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.yedam.zippy.move.mapper.MoveMapper;

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
  public List<MoveEstimateVO> getEstimateResult(MoveEstimateVO vo);
  
  //비대면 사진조회
  public List<MoveImageVO> selectAllPhoto(MoveImageVO vo);
  
  //견적서 인서트 - 업체(1차)
  public int makeEstimate(MoveResponseVO vo);
  
  //견적상태 업데이트 (1차 견적서 발송후, 상태 1로 변경)
  public int moveStatusUpdate(MoveResponseVO vo);
  
  //견적서 수정 - 업체 (2차견적)
  public int moveEstimateUpdate(MoveResponseVO vo);
  
  //견적상태 업데이트 (2차 견적서 발송후, 상태 2로 변경)
  public int moveStatusSecondUpdate(MoveResponseVO vo);
  
  //견적서 조회 - 업체
  public List<MoveResponseVO> companyEstimate(MoveCompanyEstimateVO vo);
  
  //받은 견적 조회 - 사용자
  public List<MoveMyListVO> getMyEstimateList(MoveMyListVO vo);
  
  //업체조회 페이지
  public List<MoveMyListVO> getCompanyList(MoveMyListVO vo);
  
}
