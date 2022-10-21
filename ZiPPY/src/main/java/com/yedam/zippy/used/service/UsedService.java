package com.yedam.zippy.used.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.Page;

@Service
public interface UsedService {
  // 전체조회
  public Page<UsedProductVO> usedList(String location, String keyword, String category, String checked, String dropbox);

  // 유저페이지 전체조회
  public Page<UsedProductVO> userMain(@Param("email") String email);

  // 단건조회
  public UsedProductVO usedOne(int pNo);

  // 등록
  public void insertUsed(UsedProductVO vo);

  // 수정
  public int updateUsed(UsedProductVO vo);

  // 삭제
  public int deleteUsed(int pNo);

  // 조회수 증가
  public void viewCnt(int pNo);

  // 이미지 등록
  public int insertImg(UsedImagesVO images);

  // 이미지 삭제
  public int deleteImg(UsedProductVO product);

  // 이미지 수정
  public int updateImg(UsedImagesVO images);

  // 키워드, 키워드 지역 입력
  public int addKeyword(UsedKeywordVO keyword);

  // 키워드, 키워드 지역 삭제
  public int delKeyword(int kNo);

  // 키워드 출력
  public List<UsedKeywordVO> showKeyword(String email);

  //키워드 조회
  public List<UsedKeywordVO> findKeyword(UsedProductVO product );

  // 이미지 포함 등록
  public void insertUsedProduct(UsedProductVO product, List<MultipartFile> images);

  // 이미지 포함 수정
  public void updateUsedProduct(UsedProductVO product, List<MultipartFile> images);

  // 이미지 출력
  public List<UsedImagesVO> getImg(int pNo);
}
