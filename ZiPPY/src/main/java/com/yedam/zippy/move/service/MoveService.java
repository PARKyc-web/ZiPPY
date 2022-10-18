package com.yedam.zippy.move.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
  public List<MoveEstimateVO> getEstimateList(MoveEstimateVO vo);

  //전체조회 - 유저
  public List<MoveEstimateVO> getEstimateResult(MoveEstimateVO vo);
  
  //견적서 인서트 - 업체
  public int makeEstimate(MoveResponseVO vo);
  
  //견적서 조회 - 업체
  public List<MoveCompanyEstimateVO> companyEstimate(MoveCompanyEstimateVO vo);
}
